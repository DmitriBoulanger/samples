package de.dbo.samples.cucumber.master.cdi;

import de.dbo.samples.cucumber.master.project.ATM;
import de.dbo.samples.cucumber.master.project.Account;
import de.dbo.samples.cucumber.master.project.CreditCard;

import javax.inject.Inject;
import javax.inject.Singleton;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


@Singleton
public class CDICucumberScenario {
	@Inject private ATM atm;
	@Inject private CreditCard creditCard;
	@Inject private Account account;
	private int money;
	
	@Given("^the account balance is (\\d*)$")
    public void createAccount(int balance) {
		//assertNotNull(account);
        account.setBalance(balance);
    } 
	
	@And("^the card is valid$")
    public void createCreditCard() {
		assertNotNull(creditCard);
		creditCard.setAccount(account);
    } 
	
	@And("^the machine contains (\\d*)$")
    public void createATM(int money) {
		assertNotNull(atm);
		atm.setMoney(money);
    } 
	
    @When("^the Account Holder requests (\\d*)$")
    public void requestMoney(int amount) {
        money = atm.requestMoney(creditCard, amount);
    }
    
    @Then("^the ATM should dispense (\\d*)$")
    public void checkMoney(int amount) {
        assertThat(money, is(amount));
    }
    
    @And("^the account balance should be (\\d*)$")
    public void checkBalance(int newBalance) {
        assertThat(newBalance, is(creditCard.getAccount().getBalance()));
    }
    
    @And("^the card should be returned$")
    public void cardShouldBeReturned() {
        assertFalse(creditCard.isInUse());
    }
}
