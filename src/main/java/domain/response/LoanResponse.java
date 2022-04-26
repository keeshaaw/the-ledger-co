package domain.response;

import domain.request.BankingCommand;

public class LoanResponse extends CommandResponseLike {
    public LoanResponse() {
        this.command = BankingCommand.LOAN;
    }

    @Override
    public void displayResult() {
        //Nothing to display for this command
    }
}
