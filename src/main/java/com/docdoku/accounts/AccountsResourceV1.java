package com.docdoku.accounts;

import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.docdoku.common.MakeSlow;
import com.docdoku.common.SimulateNetworkFailure;
import com.docdoku.gateway.SagaOrderModel;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Path("/accounts/v1")
public class AccountsResourceV1 {

	@Inject
	AccountsRepository accounts;

	@Inject @Channel("account-debited")
	Emitter<SagaOrderModel> accountEmitter;

	/**
	 * Called when a new order is passed, in order to update the credit of the user.
	 */
	@POST
	@Path("{accountId}/transactions")
	@SimulateNetworkFailure
	@MakeSlow
	public void createTransaction(@PathParam("accountId") int accountId, TransactionModel transaction) {
		AccountModel account = getAccountOrFail(accountId);
		double newCredit = account.credit - transaction.amount;
		if (newCredit < 0)
			throw new ClientErrorException("Credit would be negative", Response.Status.CONFLICT);
		account.credit = newCredit;
	}

	@GET
	@Path("{accountId}")
	public AccountModel get(@PathParam("accountId") int accountId) {
		return getAccountOrFail(accountId);
	}
	
	/**
	 * Used to update the credit of the user, for debug purpose.
	 */
	@PUT
	@Path("{accountId}")
	public AccountModel update(@PathParam("accountId") int accountId, AccountModel putAccount) {
		AccountModel account = getAccountOrFail(accountId);
		account.credit = putAccount.credit;
		return account;
	}

	private AccountModel getAccountOrFail(int accountId) {
		AccountModel account = accounts.getAccount(accountId);
		if (account == null)
			throw new NotFoundException();
		return account;
	}

	@Incoming("order-created")
	public void onOrderCreated(SagaOrderModel saga) {
		AccountModel account = getAccountOrFail(saga.accountId);
		account.credit -= saga.transactionAmount;
		this.accounts.saveAccount(account);
		System.out.println("--- 2. Account debited");
		accountEmitter.send(saga);
	}
}
