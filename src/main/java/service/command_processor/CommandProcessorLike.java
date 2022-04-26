package service.command_processor;

import domain.commands.QualifiedBankingTransactionCommandLike;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.CommandResponseLike;
import repository.LoanRepositoryLike;

public abstract class CommandProcessorLike<C extends QualifiedBankingTransactionCommandLike,
        R extends CommandResponseLike> {

    protected LoanRepositoryLike loanRepo;

    public abstract R processCommand(C command) throws DuplicateLoanRecordException, LoanRecordNotFoundException;
}
