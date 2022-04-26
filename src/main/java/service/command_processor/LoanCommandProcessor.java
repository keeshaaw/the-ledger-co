package service.command_processor;

import domain.commands.LoanCommand;
import domain.exceptions.DuplicateLoanRecordException;
import domain.response.LoanResponse;
import model.LoanRecord;
import repository.LoanRepositoryLike;

public class LoanCommandProcessor extends CommandProcessorLike<LoanCommand, LoanResponse> {

    public LoanCommandProcessor(LoanRepositoryLike loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Override
    public LoanResponse processCommand(LoanCommand command) throws DuplicateLoanRecordException {
        LoanRecord loanRecord = new LoanRecord(command.getBank(), command.getBorrower(), command.getPrinciple(),
                command.getRateOfInterest(), command.getLoanPeriod());
        this.loanRepo.storeLoanRecord(loanRecord);
        return new LoanResponse();
    }
}
