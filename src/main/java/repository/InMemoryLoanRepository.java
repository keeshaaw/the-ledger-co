package repository;

import domain.Bank;
import domain.Borrower;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import model.LoanRecord;
import model.PaymentRecord;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryLoanRepository implements LoanRepositoryLike {

    private Map<SyntheticLoanId, LoanRecord> underlyingLoanRepo = new HashMap<>();

    @Override
    public LoanRecord getLoanRecord(Bank bank, Borrower borrower) throws LoanRecordNotFoundException {
        SyntheticLoanId loanId = new SyntheticLoanId(bank, borrower);
        if (underlyingLoanRepo.containsKey(loanId)) {
            return underlyingLoanRepo.get(loanId);
        } else {
            throw new LoanRecordNotFoundException(bank, borrower);
        }
    }

    @Override
    public void storeLoanRecord(LoanRecord loanRecord) throws DuplicateLoanRecordException {
        SyntheticLoanId loanId = new SyntheticLoanId(loanRecord.getBank(), loanRecord.getBorrower());
        if (underlyingLoanRepo.containsKey(loanId)) {
            throw new DuplicateLoanRecordException(loanRecord.getBank(), loanRecord.getBorrower());
        } else {
            underlyingLoanRepo.put(loanId, loanRecord);
        }
    }

    @Override
    public void storePaymentsRecord(Bank bank, Borrower borrower, PaymentRecord paymentRecord)
            throws LoanRecordNotFoundException {

        SyntheticLoanId loanId = new SyntheticLoanId(bank, borrower);
        if (!underlyingLoanRepo.containsKey(loanId)) {
            throw new LoanRecordNotFoundException(bank, borrower);
        } else {
            LoanRecord loanRecord = underlyingLoanRepo.get(loanId);
            loanRecord.receivePayment(paymentRecord);
        }
    }
}

class SyntheticLoanId implements Serializable {

    private Bank bank;
    private Borrower borrower;

    public SyntheticLoanId(Bank bank, Borrower borrower) {
        this.bank = bank;
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return this.borrower.toString() + "_" + this.borrower.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyntheticLoanId)) return false;
        SyntheticLoanId that = (SyntheticLoanId) o;
        return Objects.equals(bank, that.bank) && Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, borrower);
    }
}
