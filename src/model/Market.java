package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.UnderageException;
import exceptions.WrongDayException;

public class Market {

	private List<Person> registeredPeople;
	private int registerAttempts;
	
	public Market() {
		setRegisteredPeople(new ArrayList<Person>());
		setRegisterAttempts(0);
	}

	public List<Person> getRegisteredPeople() {
		return registeredPeople;
	}

	public void setRegisteredPeople(List<Person> registeredPeople) {
		this.registeredPeople = registeredPeople;
	}
	
	public int getRegisterAttempts() {
		return registerAttempts;
	}

	public void setRegisterAttempts(int registerAttempts) {
		this.registerAttempts = registerAttempts;
	}

	public void registerPerson(int ID, IDType type, LocalDateTime date) throws WrongDayException, UnderageException {
		registerAttempts++;
		int day = date.getDayOfMonth();
		int digitID = (ID/10)%10;
		if(type != IDType.TI) {
			if((day%2 == 0 && digitID%2 != 0) || (day%2 != 0 && digitID%2 == 0)) {
				Person p = new Person(ID, type, date);
				registeredPeople.add(p);
			}else {
				throw new WrongDayException();
			}
		}else {
			throw new UnderageException();
		}
	}
	
	public String showInfo() {
		String text = "";
		for(Person p : registeredPeople) {
			text += p.toString() + "\n";
		}
		return text;
	}
	
}
