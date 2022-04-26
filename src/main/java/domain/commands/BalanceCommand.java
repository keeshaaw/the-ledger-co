package domain.commands;

import domain.Bank;
import domain.Borrower;

public class BalanceCommand extends QualifiedBankingTransactionCommandLike {
    protected final int emiNumber;

    public BalanceCommand(Bank bank, Borrower borrower, int emiNumber) {
        this.command = BankingCommand.PAYMENT;
        this.bank = bank;
        this.borrower = borrower;
        this.emiNumber = emiNumber;
    }

    public int getEmiNumber() {
        return emiNumber;
    }
}
