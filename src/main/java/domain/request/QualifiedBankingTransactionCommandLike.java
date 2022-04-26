package domain.request;

import domain.Bank;
import domain.Borrower;

public abstract class QualifiedBankingTransactionCommandLike {

    protected BankingCommand command;
    protected Bank bank;
    protected Borrower borrower;

    public BankingCommand getCommand() {
        return command;
    }

    public Bank getBank() {
        return bank;
    }

    public Borrower getBorrower() {
        return borrower;
    }
}
