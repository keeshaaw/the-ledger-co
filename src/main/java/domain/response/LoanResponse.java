package domain.response;

import domain.commands.BankingCommand;

public class LoanResponse extends CommandResponseLike {
    public LoanResponse() {
        this.command = BankingCommand.LOAN;
    }
}
