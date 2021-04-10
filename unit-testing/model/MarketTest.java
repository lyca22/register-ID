package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import exceptions.UnderageException;
import exceptions.WrongDayException;

class MarketTest {

	private Market market;
	
	public void setupScenary1() {
		market = new Market();
		try {
			market.registerPerson(1010101224, IDType.CC, LocalDateTime.of(2021, 12, 3, 10, 15, 30));
			market.registerPerson(1010101214, IDType.CE, LocalDateTime.of(2021, 11, 4, 10, 15, 30));
			market.registerPerson(1012101264, IDType.PP, LocalDateTime.of(2021, 10, 5, 10, 15, 30));
		} catch (WrongDayException e) {
			e.printStackTrace();
		} catch (UnderageException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSuccessfulRegister() {
		setupScenary1();
		try {
			market.registerPerson(10540962, IDType.CC, LocalDateTime.of(2021, 04, 11, 0, 0));
		} catch (WrongDayException e) {
			System.err.println("WrongDayException.");
		} catch (UnderageException e) {
			System.err.println("UnderageException.");
		}
		assertTrue(market.getRegisteredPeople().size() == 4);
		assertTrue(market.getRegisterAttempts() == 4);
	}
	
	@Test
	public void testUnsuccessfulRegisterDueToWrongDay() {
		setupScenary1();
		try {
			market.registerPerson(10540962, IDType.CC, LocalDateTime.of(2021, 04, 10, 0, 0));
		} catch (WrongDayException e) {
			System.err.println("WrongDayException.");
		} catch (UnderageException e) {
			System.err.println("UnderageException.");
		}
		assertTrue(market.getRegisteredPeople().size() == 3);
		assertTrue(market.getRegisterAttempts() == 4);
	}
	
	@Test
	public void testUnsuccessfulRegisterDueToUnderage() {
		setupScenary1();
		try {
			market.registerPerson(86647992, IDType.TI, LocalDateTime.now());
		} catch (WrongDayException e) {
			System.err.println("WrongDayException.");
		} catch (UnderageException e) {
			System.err.println("UnderageException.");
		}
		assertTrue(market.getRegisteredPeople().size() == 3);
		assertTrue(market.getRegisterAttempts() == 4);
	}
	
}
