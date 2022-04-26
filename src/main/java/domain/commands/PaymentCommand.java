package domain.commands;

import domain.Bank;
import domain.Borrower;

public class PaymentCommand extends QualifiedBankingTransactionCommandLike {

    protected Double lumpSumAmountPaid;
    protected int emiNumber;

    public PaymentCommand(Bank bank, Borrower borrower, Double lumpSumAmountPaid, int emiNumber) {
        this.command = BankingCommand.PAYMENT;
        this.bank = bank;
        this.borrower = borrower;
        this.lumpSumAmountPaid = lumpSumAmountPaid;
        this.emiNumber = emiNumber;
    }

    public Double getLumpSumAmountPaid() {
        return lumpSumAmountPaid;
    }

    public int getEmiNumber() {
        return emiNumber;
    }
}
