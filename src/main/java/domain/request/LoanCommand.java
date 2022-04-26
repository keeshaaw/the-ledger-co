package domain.request;

import domain.Bank;
import domain.Borrower;

public class LoanCommand extends QualifiedBankingTransactionCommandLike {

    protected double principle;
    protected int loanTermInYears;
    protected float rateOfInterest;

    public LoanCommand(Bank bank, Borrower borrower, double principle, int loanTermInYears,
                       float rateOfInterest) {
        this.command = BankingCommand.LOAN;
        this.bank = bank;
        this.borrower = borrower;
        this.principle = principle;
        this.loanTermInYears = loanTermInYears;
        this.rateOfInterest = rateOfInterest;
    }

    public double getPrinciple() {
        return principle;
    }

    public int getLoanTermInYears() {
        return loanTermInYears;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }
}
