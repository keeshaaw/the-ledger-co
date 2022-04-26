package service.command_processor;

import domain.request.LoanCommand;
import domain.exceptions.DuplicateLoanRecordException;
import domain.response.LoanResponse;
import model.LoanRecord;
import repository.LoanRepositoryLike;

public class LoanCommandProcessor extends CommandProcessorLike<LoanCommand, LoanResponse> {

    public LoanCommandProcessor(LoanRepositoryLike loanRepo, LoanCommand command) {
        this.loanRepo = loanRepo;
        this.command = command;
    }

    @Override
    public LoanResponse processCommand() throws DuplicateLoanRecordException {
        LoanRecord loanRecord = new LoanRecord(command.getBank(), command.getBorrower(), command.getPrinciple(),
                command.getRateOfInterest(), command.getLoanTermInYears());
        this.loanRepo.storeLoanRecord(loanRecord);
        return new LoanResponse();
    }
}
