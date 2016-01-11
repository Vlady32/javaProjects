import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import by.gsu.asoilab.BusinessTrip;

public class RunnerStrings2 {

	private static void printOut(BusinessTrip[] array) {
		for (BusinessTrip el : array)
			System.out.println(el);
	}

	private static int[] searchIndices(String line) {
		String transformedLine = line;
		final String SEMICOLON = ";";
		final String EMPTY_LINE = "";
		final String REGEX_VALUE = "[1-9]{1}\\d*";
		final String REGEX_BEGIN_SEMICOLON = "^;.*";
		final String REGEX_END_SEMICOLON = ".*;$";
		final String REGEX_SEMICOLON_PLUS_SPACE = "; *";
		final String REGEX_LETTERS_PLUS_SEMICOLON = ".*; *";
		final String REGEX_SEMICOLON_PLUS_LETTERS = " *;.*";
		Pattern patternNumber = Pattern.compile(REGEX_VALUE);
		Pattern patternBegin = Pattern.compile(REGEX_BEGIN_SEMICOLON);
		Pattern patternEnd = Pattern.compile(REGEX_END_SEMICOLON);

		if (!patternBegin.matcher(line).matches())
			transformedLine = SEMICOLON + transformedLine;
		if (!patternEnd.matcher(line).matches())
			transformedLine += SEMICOLON;

		String[] brokeLine = line.split(SEMICOLON);

		int[] indicesFirst = new int[brokeLine.length];
		int count = 0;
		for (int i = 0; i < brokeLine.length; i++) {
			String regexValue = REGEX_SEMICOLON_PLUS_SPACE + brokeLine[i];
			String regexEqualElement = REGEX_LETTERS_PLUS_SEMICOLON + brokeLine[i].trim()
					+ REGEX_SEMICOLON_PLUS_LETTERS;
			Pattern p = Pattern.compile(regexEqualElement);
			transformedLine = transformedLine.replaceFirst(regexValue, EMPTY_LINE);
			if (patternNumber.matcher(brokeLine[i].trim()).matches() && !p.matcher(transformedLine).matches()) {
				indicesFirst[count] = Integer.valueOf(brokeLine[i].trim());
				count++;
			}
		}
		int[] indices = new int[count];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = indicesFirst[i];
		}
		return indices;
	}

	public static void main(String[] args) {
		Scanner sc = null;
		final String FILE_PATH = "src/in.txt";
		final String PHRASE_END = "This program is ends";
		final String REGEX_INDICES = " *indices *=.*(;.*)*";
		final String REGEX_ACCOUNT = "^account";
		final String REGEX_TRANSPORT = "^transport";
		final String REGEX_DAYS = "^days";
		final String REGEX_NUMBERS = "\\d+";
		final String REGEX_LETTERS = "[a-zA-Z]+";
		final String REGEX_EQUAL_ANY_LETTERS = ".*=.*";
		final String EQUAL = "=";
		final String SPACES_EQUAL_ANY_LETTERS = " *=.*";
		Pattern patternIndices = Pattern.compile(REGEX_INDICES);
		Pattern patternNumbers = Pattern.compile(REGEX_NUMBERS);
		Pattern patternLetters = Pattern.compile(REGEX_LETTERS);
		Pattern patternEqual = Pattern.compile(REGEX_EQUAL_ANY_LETTERS);
		int[] indices = {};
		BusinessTrip[] businessTrips = {};

		try {
			sc = new Scanner(new FileReader(FILE_PATH));
			String stroke = sc.nextLine();
			stroke = sc.nextLine();

			if ((stroke.length() == 0) || (patternIndices.matcher(stroke).matches() == false)) {
				businessTrips = new BusinessTrip[0];
				System.out.println(PHRASE_END);
				System.exit(0);
			}

			if (patternIndices.matcher(stroke).matches()) {
				indices = searchIndices(stroke.split(EQUAL)[1].trim());
				businessTrips = new BusinessTrip[indices.length];
				for (int i = 0; i < businessTrips.length; i++)
					businessTrips[i] = new BusinessTrip();
			}

			firstWhile: while (sc.hasNextLine()) {
				stroke = sc.nextLine();
				if (!patternEqual.matcher(stroke).matches())
					continue;
				for (int i = 0; i < indices.length; i++) {
					Pattern patternAccount = Pattern.compile(REGEX_ACCOUNT + indices[i] + SPACES_EQUAL_ANY_LETTERS);
					Pattern patternTransport = Pattern.compile(REGEX_TRANSPORT + indices[i] + SPACES_EQUAL_ANY_LETTERS);
					Pattern patternDays = Pattern.compile(REGEX_DAYS + indices[i] + SPACES_EQUAL_ANY_LETTERS);
					String valueAfterEquals = stroke.split(EQUAL)[1].trim();
					if (patternAccount.matcher(stroke).matches()
							&& patternLetters.matcher(valueAfterEquals).matches()) {
						businessTrips[i].setEmployer(valueAfterEquals);
						continue firstWhile;
					}
					if (patternTransport.matcher(stroke).matches()
							&& patternNumbers.matcher(valueAfterEquals).matches()) {
						businessTrips[i].setTransportationExpenses(Integer.valueOf(valueAfterEquals));
						continue firstWhile;
					}
					if (patternDays.matcher(stroke).matches() && patternNumbers.matcher(valueAfterEquals).matches()) {
						businessTrips[i].setNumberOfDays(Integer.valueOf(valueAfterEquals));
						continue firstWhile;
					}
				}
			}
			printOut(businessTrips);
		} catch (FileNotFoundException e) {
			System.err.println("Not found");
		}
	}
}
