package service.command_processor;

import domain.request.BalanceCommand;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.BalanceResponse;
import model.LoanRecord;
import repository.LoanRepositoryLike;

public class BalanceCommandProcessor extends CommandProcessorLike<BalanceCommand, BalanceResponse> {

    public BalanceCommandProcessor(LoanRepositoryLike loanRepo, BalanceCommand command) {
        this.loanRepo = loanRepo;
        this.command = command;
    }

    @Override
    public BalanceResponse processCommand() throws LoanRecordNotFoundException {
        LoanRecord loanRecord = loanRepo.getLoanRecord(command.getBank(), command.getBorrower());

        long totalAmountPaidTillThisEMINumber = (long) loanRecord.getTotalAmountPaidTillEMINumber(command.getEmiNumber());
        int remainingEMIs = loanRecord.numberOfEMIsLeftAfterEMINumber(command.getEmiNumber());

        return new BalanceResponse(command.getBank(), command.getBorrower(), totalAmountPaidTillThisEMINumber,
                remainingEMIs);
    }
}
