package by.gsu.asoilab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BusinessTrip implements Comparable<BusinessTrip>{

	public static final String FILE_PATH = "src/in.txt";
	public static int RATE;
	String employer;
	int transportExpenses;
	int numberOfDays;

	static {
		try {
			Scanner sc = new Scanner(new FileReader(FILE_PATH));
			RATE = Integer.valueOf(sc.nextLine().split("=")[1].trim());
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}

	public BusinessTrip() {

	}

	public BusinessTrip(String employer, int transportationExpenses, int numberOfDays) {
		this.employer = employer;
		this.transportExpenses = transportationExpenses;
		this.numberOfDays = numberOfDays;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployee(String employer) {
		this.employer = employer;
	}

	public int getTransportationExpenses() {
		return transportExpenses;
	}

	public void setTransportationExpenses(int transportationExpenses) {
		this.transportExpenses = transportationExpenses;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public int getTotal() {
		return transportExpenses + RATE * numberOfDays;
	}

	public void show() {
		System.out.println("rate = " + RATE);
		System.out.println("account = " + this.employer);
		System.out.println("transport = " + this.transportExpenses);
		System.out.println("days = " + this.numberOfDays);
		System.out.println("total = " + getTotal());
		System.out.println();
	}

	@Override
	public String toString() {
		return RATE + ";" + this.employer + ";" + this.transportExpenses + ";" + this.numberOfDays + ";" + getTotal();
	}

	@Override
	public int compareTo(BusinessTrip trip) {
		return this.getTotal()-trip.getTotal();
	}
}
