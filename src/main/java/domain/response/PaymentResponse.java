package domain.response;

import domain.request.BankingCommand;

public class PaymentResponse extends CommandResponseLike {
    public PaymentResponse() {
        this.command = BankingCommand.PAYMENT;
    }

    @Override
    public void displayResult() {
        //Nothing to display for this command
    }
}
