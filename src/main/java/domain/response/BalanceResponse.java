package domain.response;

import domain.Bank;
import domain.Borrower;

import static domain.request.BankingCommand.BALANCE;

public class BalanceResponse extends CommandResponseLike {
    private final Bank bank;
    private final Borrower borrower;
    private final long amountPaid;
    private final int numberOfRemainingEMIs;

    public BalanceResponse(Bank bank, Borrower borrower, long amountPaid, int numberOfRemainingEMIs) {
        this.bank = bank;
        this.borrower = borrower;
        this.amountPaid = amountPaid;
        this.numberOfRemainingEMIs = numberOfRemainingEMIs;
        this.command = BALANCE;
    }

    public Bank getBank() {
        return bank;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public int getNumberOfRemainingEMIs() {
        return numberOfRemainingEMIs;
    }

    @Override
    public void displayResult() {
        System.out.println(bank.getName() + " " + borrower.getName() + " " + amountPaid + " " + numberOfRemainingEMIs);
    }
}
