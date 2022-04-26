package service.command_processor;

import domain.request.PaymentCommand;
import domain.exceptions.LoanRecordNotFoundException;
import domain.response.PaymentResponse;
import model.LoanRecord;
import model.PaymentRecord;
import repository.LoanRepositoryLike;

public class PaymentCommandProcessor extends CommandProcessorLike<PaymentCommand, PaymentResponse> {

    public PaymentCommandProcessor(LoanRepositoryLike loanRepo, PaymentCommand command) {
        this.loanRepo = loanRepo;
        this.command = command;
    }

    @Override
    public PaymentResponse processCommand() throws LoanRecordNotFoundException {
        LoanRecord loanRecord = loanRepo.getLoanRecord(command.getBank(), command.getBorrower());
        PaymentRecord paymentRecord = new PaymentRecord(command.getLumpSumAmountPaid(), command.getEmiNumber());
        loanRecord.receivePayment(paymentRecord);
        return new PaymentResponse();
    }
}
