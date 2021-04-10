package ui;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.UnderageException;
import exceptions.WrongDayException;
import model.IDType;
import model.Market;

public class UserInterface {

	private static final int REGISTER_PERSON = 0;
	private static final int VIEW_REGISTER_ATTEMPTS = 1;
	private static final int VIEW_REGISTER_DATA = 2;
	private static final int EXIT = 3;

	private Market market;
	private Scanner scanner;

	public UserInterface() {
		setMarket(new Market());
		scanner = new Scanner(System.in);
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public void startProgram() {
		int option = -1;
		do {
			showInterface();
			try {
				option = readOption();
				doOperation(option);
			} catch (NumberFormatException e) {
				System.err.println("Please enter a valid option (NumberFormatException).");
			} catch (NoSuchElementException e) {
				System.err.println("NoSuchElementException.");
			}
		}while(option != EXIT);
		System.out.println("\nApplication closed.");
	}

	private void showInterface() {
		String text = "\n0. Register a person.\n";
		text += "1. View register attempts.\n";
		text += "2. View registered information.\n";
		text += "3. Close the application.\n";
		System.out.println(text);
	}

	private int readOption() {
		int option;
		System.out.println("Enter a value according to what you want to do.");
		option = Integer.parseInt(scanner.nextLine());
		return option;
	}

	private void doOperation(int option) {
		switch(option) {
		case REGISTER_PERSON:
			System.out.println("\nEnter your ID");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.println("\nEnter your ID's type (TI = 1, CC = 2, PP = 3, CE = 4)");
			int typeAsString = Integer.parseInt(scanner.nextLine());
			IDType type = IDType.TI;
			switch(typeAsString) {
			case 1:
				type = IDType.TI;
				break;
			case 2:
				type = IDType.CC;
				break;
			case 3:
				type = IDType.PP;
				break;
			case 4:
				type = IDType.CE;
				break;
			}
			try {
				LocalDateTime date = LocalDateTime.now();
				market.registerPerson(id, type, date);
			} catch (WrongDayException e) {
				System.err.println(e.getMessage());
			} catch (UnderageException e) {
				System.err.println(e.getMessage());
			}
			break;
		case VIEW_REGISTER_ATTEMPTS:
			System.out.println("\nRegister attempts: " + market.getRegisterAttempts());
			System.out.println("\nPress any key to continue.");
			scanner.nextLine();
			break;
		case VIEW_REGISTER_DATA:
			System.out.println("\n" + market.showInfo());
			System.out.println("Press any key to continue.");
			scanner.nextLine();
			break;
		}
	}

}
