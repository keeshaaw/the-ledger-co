package service.command_processor;

import domain.commands.BalanceCommand;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.BalanceResponse;
import model.LoanRecord;
import repository.LoanRepositoryLike;

public class BalanceCommandProcessor extends CommandProcessorLike<BalanceCommand, BalanceResponse> {

    public BalanceCommandProcessor(LoanRepositoryLike loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Override
    public BalanceResponse processCommand(BalanceCommand command) throws LoanRecordNotFoundException {
        LoanRecord loanRecord = loanRepo.getLoanRecord(command.getBank(), command.getBorrower());

        double totalAmountPaidTillThisEMINumber = loanRecord.getTotalAmountPaidTillEMINumber(command.getEmiNumber());
        int remainingEMIs = loanRecord.numberOfEMIsLeftAfterEMINumber(command.getEmiNumber());

        return new BalanceResponse(command.getBank(), command.getBorrower(), totalAmountPaidTillThisEMINumber,
                remainingEMIs);
    }
}
