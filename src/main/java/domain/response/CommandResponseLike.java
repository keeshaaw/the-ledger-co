package domain.response;

import domain.request.BankingCommand;

public abstract class CommandResponseLike {
    //TODO: Check if this is required
    protected BankingCommand command;
    public abstract void displayResult();
}
