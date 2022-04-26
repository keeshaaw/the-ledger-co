package service;

import domain.Bank;
import domain.Borrower;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import domain.request.*;
import repository.InMemoryLoanRepository;
import repository.LoanRepositoryLike;
import service.command_processor.BalanceCommandProcessor;
import service.command_processor.CommandProcessorLike;
import service.command_processor.LoanCommandProcessor;
import service.command_processor.PaymentCommandProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestProcessorService {
    final private LoanRepositoryLike loanRepo;
    final private String filePath;

    public RequestProcessorService(String filePath) {
        this.loanRepo = InMemoryLoanRepository.getInstance();
        this.filePath = filePath;
    }

    public void processRequest() throws IOException, DuplicateLoanRecordException, LoanRecordNotFoundException {
        for (CommandProcessorLike processor : resolveAllProcessor()) {
            processor.processCommand().displayResult();
        }
    }

    private List<CommandProcessorLike> resolveAllProcessor() throws IOException {
        List<String> allCommandsAsString = Files.readAllLines(Paths.get(filePath));
        List<CommandProcessorLike> commandProcessors = new ArrayList<>();
        for (String commandAsString : allCommandsAsString) {
            String trimmedCommandString = commandAsString.trim();
            if (trimmedCommandString.length() > 0) {
                commandProcessors.add(resolveProcessor(trimmedCommandString));
            }
        }
        return commandProcessors;
    }

    private CommandProcessorLike resolveProcessor(String commandAsString) {
        String[] commandParts = Arrays.stream(commandAsString.split(" ")).map(String::trim).toArray(String[]::new);

        CommandProcessorLike commandProcessor = null;

        BankingCommand command = BankingCommand.valueOf(commandParts[0]);
        Bank bank = new Bank(commandParts[1]);
        Borrower borrower = new Borrower(commandParts[2]);

        switch (command) {
            case LOAN:
                double principal = Double.parseDouble(commandParts[3]);
                int loanTermInYears = Integer.parseInt(commandParts[4]);
                float rateOfInterest = Float.parseFloat(commandParts[5]);
                LoanCommand loanCommand = new LoanCommand(bank, borrower, principal, loanTermInYears, rateOfInterest);
                commandProcessor = new LoanCommandProcessor(loanRepo, loanCommand);
                break;
            case PAYMENT:
                double lumpSumAmount = Double.parseDouble(commandParts[3]);
                int emiNumberInPaymentCommand = Integer.parseInt(commandParts[4]);
                PaymentCommand paymentCommand = new PaymentCommand(bank, borrower, lumpSumAmount, emiNumberInPaymentCommand);
                commandProcessor = new PaymentCommandProcessor(loanRepo, paymentCommand);
                break;
            case BALANCE:
                int emiNumberInBalanceCommand = Integer.parseInt(commandParts[3]);
                BalanceCommand balanceCommand = new BalanceCommand(bank, borrower, emiNumberInBalanceCommand);
                commandProcessor = new BalanceCommandProcessor(loanRepo, balanceCommand);
                break;
        }

        return commandProcessor;
    }
}
