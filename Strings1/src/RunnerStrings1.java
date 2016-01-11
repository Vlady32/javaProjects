import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

import by.gsu.asoilab.BusinessTrip;

public class RunnerStrings1 {

	private static void printOut(BusinessTrip[] array) {
		for (BusinessTrip el : array)
			System.out.println(el);
	}

	private static boolean equals(String line, String searchLine, int index) {
		if (line.equals(searchLine + index) || line.equals(searchLine + "0" + index))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		Scanner sc = null;
		final String EQUAL = "=";
		final String ACCOUNT = "account";
		final String TRANSPORT = "transport";
		final String DAYS = "days";
		final String SEMICOLON = ";";
		BusinessTrip[] businessTrips = {};
		int[] indexesInt = {};
		int[] transportExpenses = {};
		int[] numDays = {};
		String[] employers = {};
		try {
			sc = new Scanner(new FileReader("src/in.txt"));
			String stroke = sc.nextLine();
			stroke = sc.nextLine();
			String[] brokeLines = stroke.split(EQUAL);

			if ((brokeLines.length > 1) && (brokeLines[1].split(";").length >= 1)) {
				String[] indexesString = brokeLines[1].split(SEMICOLON);
				indexesInt = new int[indexesString.length];
				for (int i = 0; i < indexesInt.length; i++) {
					indexesInt[i] = Integer.valueOf(indexesString[i].trim());
				}
				Arrays.sort(indexesInt);
				businessTrips = new BusinessTrip[indexesInt.length];
				employers = new String[indexesInt.length];
				transportExpenses = new int[indexesInt.length];
				numDays = new int[indexesInt.length];

				while (sc.hasNextLine()) {
					stroke = sc.nextLine();
					String lineSplitEqual = stroke.split(EQUAL)[1].trim();
					if (stroke.contains(ACCOUNT))
						stroke = stroke.replace(args[0], args[1]);
					String firstValueOfBrokeLine = stroke.split(EQUAL)[0].trim();
					for (int i = 0; i < indexesInt.length; i++) {
						if (equals(firstValueOfBrokeLine, ACCOUNT, indexesInt[i]))
							employers[i] = lineSplitEqual;
						if (equals(firstValueOfBrokeLine, TRANSPORT, indexesInt[i]))
							transportExpenses[i] = Integer.valueOf(lineSplitEqual);
						if (equals(firstValueOfBrokeLine, DAYS, indexesInt[i]))
							numDays[i] = Integer.valueOf(lineSplitEqual);
					}
				}
			}
			for (int i = 0; i < indexesInt.length; i++)
				businessTrips[i] = new BusinessTrip(employers[i], transportExpenses[i], numDays[i]);
			Arrays.sort(businessTrips);
			printOut(businessTrips);
		} catch (FileNotFoundException e) {
			System.err.println("Not found");
		}
	}

}
