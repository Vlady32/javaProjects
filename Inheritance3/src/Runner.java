
import java.util.Arrays;

import by.gsu.asoilab.*;

public class Runner {

	private static void printArray(Trial[] inputTrialArray) {
		for (Trial countTrial : inputTrialArray) {
			System.out.println(countTrial);
		}
	}

	public static void main(String[] args) {
		ComparatorMark1 comparatorMar1 = new ComparatorMark1();
		ComparatorMark2 comparatorMar2 = new ComparatorMark2();
		Trial objSearch = new Trial("Ruzhitsky", 6,45);
		Trial[] trials = { new Trial("Sakovich",  3,45), new Trial("Sakovich",6, 73 ),
				new Trial("Ruzhitsky", 2, 12), new Trial("Ruzhitsky", 4, 13), new Trial("Ruzhitsky", 5, 75)};
		printArray(trials);
		Instruments.sort(trials, comparatorMar1);
		System.out.println("---------------------");
		printArray(trials);
//		System.out.println("---------------------");
//		Instruments.sort(trials, comparatorMar2);
//		printArray(trials);
		System.out.println("---------------------");
		System.out.println(Instruments.linearSearch(trials, objSearch, comparatorMar1));
	}

}
