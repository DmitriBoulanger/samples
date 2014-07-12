package be.gallifreyan.cucumber.example.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountTest {
	private static final int BALANCE = 100;
	private Account testAccount;
	
	@Before
	public void startUp(){
		testAccount = new Account(BALANCE);
		assertNotNull(testAccount);
	}
	
	@After
	public void cleanUp(){
		testAccount = null;
	}
	
	@Test
	public void testAccountBalanceIsCorrect(){
		assertEquals(testAccount.getBalance(), BALANCE);
	}
	
	@Test
	public void testMoneyTransferWithValidAmount(){
		int newBalance = testAccount.getMoney(BALANCE-1);
		assertEquals(newBalance, BALANCE-1);
	}
	
	@Test
	public void testMoneyTransferWithInvalidAmount(){
		int newBalance = testAccount.getMoney(BALANCE+1);
		assertEquals(newBalance, 0);
	}
}
