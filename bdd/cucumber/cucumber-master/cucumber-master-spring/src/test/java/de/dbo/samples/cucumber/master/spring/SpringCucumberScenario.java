package de.dbo.samples.cucumber.master.spring;

import de.dbo.samples.cucumber.master.model.ATM;
import de.dbo.samples.cucumber.master.model.Account;
import de.dbo.samples.cucumber.master.model.CreditCard;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SpringCucumberScenario {
	@Autowired private ATM atm;
	@Autowired private CreditCard creditCard;
	@Autowired private Account account;
	private int money;
	
	@Given("^the account balance is (\\d*)$")
    public void createAccount(int balance) {
		assertNotNull(account);
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
