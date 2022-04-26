package domain.exceptions;

import domain.Bank;
import domain.Borrower;

public class DuplicateLoanRecordException extends Exception {
    public DuplicateLoanRecordException(Bank bank, Borrower borrower) {
        super("Trying to store duplicate loan for Bank: " + bank.getName() + " and Borrower: " + borrower.getName());
    }
}
