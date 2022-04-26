package domain.commands;

import domain.Bank;
import domain.Borrower;

public class LoanCommand extends QualifiedBankingTransactionCommandLike {

    protected Double principle;
    protected int loanPeriod;
    protected float rateOfInterest;

    public LoanCommand(Bank bank, Borrower borrower, Double principle, int loanPeriod,
                       float rateOfInterest) {
        this.command = BankingCommand.LOAN;
        this.bank = bank;
        this.borrower = borrower;
        this.principle = principle;
        this.loanPeriod = loanPeriod;
        this.rateOfInterest = rateOfInterest;
    }

    public Double getPrinciple() {
        return principle;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }
}
