package model;

import java.util.Objects;

public class PaymentRecord {
    private double lumpSumAmount;
    private int emiNumber;

    public PaymentRecord(double lumpSumAmount, int emiNumber) {
        this.lumpSumAmount = lumpSumAmount;
        this.emiNumber = emiNumber;
    }

    public double getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(double lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public int getEmiNumber() {
        return emiNumber;
    }

    public void setEmiNumber(int emiNumber) {
        this.emiNumber = emiNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRecord)) return false;
        PaymentRecord that = (PaymentRecord) o;
        return Double.compare(that.lumpSumAmount, lumpSumAmount) == 0 && emiNumber == that.emiNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lumpSumAmount, emiNumber);
    }
}
