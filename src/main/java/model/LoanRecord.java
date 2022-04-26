package model;

import domain.Bank;
import domain.Borrower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoanRecord {

    final private Bank bank;
    final private Borrower borrower;
    final private double principle;
    final private float rateOfInterest;
    final private int loanTermInYears;
    final private List<PaymentRecord> payments = new ArrayList<>();

    public LoanRecord(Bank bank, Borrower borrower, double principle, float rateOfInterest,
                      int loanTermInYears) {
        this.bank = bank;
        this.borrower = borrower;
        this.principle = principle;
        this.rateOfInterest = rateOfInterest;
        this.loanTermInYears = loanTermInYears;
    }

    public double getTotalAmountPaidTillEMINumber(int emiNumber) {
        double lumpSumAmountPaidTillThisEMI = getLumpSumAmountPaidTillEMINumber(emiNumber);
        double totalAmountPaidInEMI = this.getEMIAmount() * emiNumber;
        return lumpSumAmountPaidTillThisEMI + totalAmountPaidInEMI;
    }

    public int numberOfEMIsLeftAfterEMINumber(int emiNumber) {
        double totalAmountPaidTillThisEMINumber = getTotalAmountPaidTillEMINumber(emiNumber);
        double totalPayableAmount = getTotalPayableAmount();
        double remainAmountToBePaid = totalPayableAmount - totalAmountPaidTillThisEMINumber;
        return (int) Math.ceil(remainAmountToBePaid / getEMIAmount());
    }

    private double getLumpSumAmountPaidTillEMINumber(int emiNumber) {
        double lumpSumAmountPaidTillThisEMI = 0;
        for (PaymentRecord payment : payments) {
            if (payment.getEmiNumber() <= emiNumber) {
                lumpSumAmountPaidTillThisEMI += payment.getLumpSumAmount();
            }
        }
        return lumpSumAmountPaidTillThisEMI;
    }

    private double getEMIAmount() {
        double totalPayableAmount = this.getTotalPayableAmount();
        int loanTenureInMonths = this.loanTermInYears * 12;
        return Math.ceil(totalPayableAmount / loanTenureInMonths);
    }

    public double getTotalPayableAmount() {
        double totalInterest = principle * loanTermInYears * (rateOfInterest / 100);
        return principle + totalInterest;
    }

    public Bank getBank() {
        return bank;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public double getPrinciple() {
        return principle;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }

    public int getLoanTermInYears() {
        return loanTermInYears;
    }

    public List<PaymentRecord> getPayments() {
        return payments;
    }

    public void receivePayment(PaymentRecord payment) {
        this.payments.add(payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRecord)) return false;
        LoanRecord that = (LoanRecord) o;
        return Double.compare(that.principle, principle) == 0 && Float.compare(that.rateOfInterest, rateOfInterest) == 0
                && loanTermInYears == that.loanTermInYears && bank.equals(that.bank) && borrower.equals(that.borrower) &&
                payments.equals(that.payments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, borrower, principle, rateOfInterest, loanTermInYears, payments);
    }
}
