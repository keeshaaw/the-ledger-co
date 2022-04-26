package repository;

import domain.Bank;
import domain.Borrower;
import domain.exceptions.DuplicateLoanRecordException;
import domain.exceptions.LoanRecordNotFoundException;
import model.LoanRecord;
import model.PaymentRecord;

public interface LoanRepositoryLike {

    public LoanRecord getLoanRecord(Bank bank, Borrower borrower) throws LoanRecordNotFoundException;
    public void storeLoanRecord(LoanRecord loanRecord) throws DuplicateLoanRecordException;
    public void storePaymentsRecord(Bank bank, Borrower borrower, PaymentRecord paymentRecord) throws LoanRecordNotFoundException;
}
