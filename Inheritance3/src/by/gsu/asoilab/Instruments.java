package by.gsu.asoilab;

public class Instruments {

	public static void sort(Trial[] trials, Sorter compar) {
		if (trials.length == 0) {
			System.out.println("Length of array = 0\n");
		} else {
			qSort(trials, 0, trials.length - 1, compar);
		}
	}

	private static void qSort(Trial[] inputArray, final int first, final int last, Sorter compar) {
		int varI;
		int varJ;
		Trial varX;
		Trial varY;
		varI = first;
		varJ = last;
		varX = inputArray[(first + last) >>> 1];

		do {
			while (compar.compare(inputArray[varI], varX) < 0) {
				varI++;
			}
			while (compar.compare(varX, inputArray[varJ]) < 0) {
				varJ--;
			}
			if (varI <= varJ) {
				varY = inputArray[varI];
				inputArray[varI] = inputArray[varJ];
				inputArray[varJ] = varY;
				varI++;
				varJ--;
			}
		} while (varI <= varJ);
		if (first < varJ) {
			qSort(inputArray, first, varJ, compar);
		}
		if (varI < last) {
			qSort(inputArray, varI, last, compar);
		}
	}
	

//	public static void sort(Trial[] trials, Sorter compar) {
//	Trial variable;
//	for (int i = 0; i < trials.length; i++)
//		for (int j = i + 1; j < trials.length; j++)
//			if (compar.compare(trials[j], trials[i]) < 0) {
//				variable = trials[i];
//				trials[i] = trials[j];
//				trials[j] = variable;
//			}
//}

	public static int linearSearch(Trial[] trials, Trial trialSearch, Sorter sorter) {
		int i = 0;
		int result;
		
		do{
			result = sorter.compare(trials[i], trialSearch);
		}while((result < 0) && (++i < trials.length));
		
		return (result == 0) ? i : -i-1;
	}

}
