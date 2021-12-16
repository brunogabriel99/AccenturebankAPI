package bank.accenture.accenture.bank.interfaces;

import bank.accenture.accenture.bank.DTO.DepositVoucherDTO;
import bank.accenture.accenture.bank.DTO.TransferVoucherDTO;
import bank.accenture.accenture.bank.DTO.WithdrawVoucherDTO;

public interface AccountTransactions {

	TransferVoucherDTO transfer (Long idSender, Long idRecipient, Double value);
	WithdrawVoucherDTO withdraw (Long id, Double value);
	DepositVoucherDTO deposit (Long id, Double value);
}
