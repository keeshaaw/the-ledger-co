package service.command_processor;

import domain.request.QualifiedBankingTransactionCommandLike;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.CommandResponseLike;
import repository.LoanRepositoryLike;

public abstract class CommandProcessorLike<C extends QualifiedBankingTransactionCommandLike,
        R extends CommandResponseLike> {

    protected LoanRepositoryLike loanRepo;
    protected C command;

    public abstract R processCommand() throws DuplicateLoanRecordException, LoanRecordNotFoundException;
}
