package domain.response;

import domain.commands.BankingCommand;

public class PaymentResponse extends CommandResponseLike {
    public PaymentResponse() {
        this.command = BankingCommand.PAYMENT;
    }
}
