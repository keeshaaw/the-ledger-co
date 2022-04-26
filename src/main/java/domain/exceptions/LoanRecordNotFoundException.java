package domain.exceptions;

import domain.Bank;
import domain.Borrower;

public class LoanRecordNotFoundException extends Exception {
    public LoanRecordNotFoundException(Bank bank, Borrower borrower) {
        super("Could not find any loan record for Bank: " + bank.getName() + " and Borrower: " + borrower.getName() + " combination");
    }
}
