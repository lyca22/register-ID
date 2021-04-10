package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person {

	private int ID;
	private IDType type;
	private LocalDateTime date;
	
	public Person(int ID, IDType type, LocalDateTime date) {
		setID(ID);
		setType(type);
		setDate(date);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public IDType getType() {
		return type;
	}

	public void setType(IDType type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getTypeAsString() {
		String text = "";
		switch(type) {
		case TI:
			text = "T.I.";
			break;
		case CC:
			text = "C.C.";
			break;
		case PP:
			text = "P.P.";
			break;
		case CE:
			text = "C.E.";
			break;
		}
		return text;
	}
	
	@Override
	public String toString() {
		return getTypeAsString() + " | " + ID + " | " + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
}
