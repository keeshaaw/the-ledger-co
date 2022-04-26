package domain.request;

import domain.Bank;
import domain.Borrower;

public class PaymentCommand extends QualifiedBankingTransactionCommandLike {

    protected double lumpSumAmountPaid;
    protected int emiNumber;

    public PaymentCommand(Bank bank, Borrower borrower, double lumpSumAmountPaid, int emiNumber) {
        this.command = BankingCommand.PAYMENT;
        this.bank = bank;
        this.borrower = borrower;
        this.lumpSumAmountPaid = lumpSumAmountPaid;
        this.emiNumber = emiNumber;
    }

    public double getLumpSumAmountPaid() {
        return lumpSumAmountPaid;
    }

    public int getEmiNumber() {
        return emiNumber;
    }
}
