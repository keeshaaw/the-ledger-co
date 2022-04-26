import domain.Bank;
import domain.Borrower;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import domain.request.BalanceCommand;
import domain.request.LoanCommand;
import domain.request.PaymentCommand;
import domain.response.BalanceResponse;
import model.LoanRecord;
import org.junit.jupiter.api.Test;
import repository.InMemoryLoanRepository;
import service.command_processor.BalanceCommandProcessor;
import service.command_processor.LoanCommandProcessor;
import service.command_processor.PaymentCommandProcessor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanRecordTest {

    public LoanCommand getRandomLoanCommand() {
        Bank bank = new Bank(UUID.randomUUID().toString());
        Borrower borrower = new Borrower(UUID.randomUUID().toString());
        double principle = 10000;
        int loanTermInYears = 2;
        float rateOfInterest = 5;
        return new LoanCommand(bank, borrower, principle, loanTermInYears, rateOfInterest);
    }

    @Test
    public void testLoanProcessor() throws DuplicateLoanRecordException, LoanRecordNotFoundException {

        LoanCommand loanCommand = getRandomLoanCommand();

        LoanCommandProcessor loanCommandProcessor = new LoanCommandProcessor(InMemoryLoanRepository.getInstance(), loanCommand);
        loanCommandProcessor.processCommand();

        LoanRecord loanRecord = InMemoryLoanRepository.getInstance().getLoanRecord(loanCommand.getBank(), loanCommand.getBorrower());

        assertEquals(loanCommand.getBank(), loanRecord.getBank());
        assertEquals(loanCommand.getBorrower(), loanRecord.getBorrower());
        assertEquals(loanCommand.getPrinciple(), loanRecord.getPrinciple());
        assertEquals(loanCommand.getRateOfInterest(), loanRecord.getRateOfInterest());
        assertEquals(loanCommand.getLoanTermInYears(), loanRecord.getLoanTermInYears());
    }

    @Test
    public void testPaymentProcessor() throws DuplicateLoanRecordException, LoanRecordNotFoundException {
        LoanCommand loanCommand = getRandomLoanCommand();

        LoanCommandProcessor loanCommandProcessor = new LoanCommandProcessor(InMemoryLoanRepository.getInstance(), loanCommand);
        loanCommandProcessor.processCommand();

        PaymentCommand paymentCommand = new PaymentCommand(loanCommand.getBank(), loanCommand.getBorrower(), 1000, 5);
        PaymentCommandProcessor paymentCommandProcessor = new PaymentCommandProcessor(InMemoryLoanRepository.getInstance(), paymentCommand);
        paymentCommandProcessor.processCommand();


        BalanceCommand balanceCommand = new BalanceCommand(loanCommand.getBank(), loanCommand.getBorrower(), 6);

        BalanceCommandProcessor balanceCommandProcessor = new BalanceCommandProcessor(InMemoryLoanRepository.getInstance(), balanceCommand);
        BalanceResponse balanceResponse = balanceCommandProcessor.processCommand();

        assertEquals(balanceResponse.getBank(), balanceCommand.getBank());
        assertEquals(balanceResponse.getBorrower(), balanceCommand.getBorrower());
        assertEquals(balanceResponse.getAmountPaid(), 3754);
        assertEquals(balanceResponse.getNumberOfRemainingEMIs(), 16);
    }
}
