package service.command_processor;

import domain.commands.PaymentCommand;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.CommandResponseLike;
import domain.response.PaymentResponse;
import model.LoanRecord;
import model.PaymentRecord;
import repository.LoanRepositoryLike;

public class PaymentCommandProcessor extends CommandProcessorLike<PaymentCommand, PaymentResponse> {

    public PaymentCommandProcessor(LoanRepositoryLike loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Override
    public PaymentResponse processCommand(PaymentCommand command) throws LoanRecordNotFoundException {
        LoanRecord loanRecord = loanRepo.getLoanRecord(command.getBank(), command.getBorrower());
        PaymentRecord paymentRecord = new PaymentRecord(command.getLumpSumAmountPaid(), command.getEmiNumber());
        loanRecord.receivePayment(paymentRecord);
        return new PaymentResponse();
    }
}
