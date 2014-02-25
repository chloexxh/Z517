import java.util.Random;

public class L3_ArrayFunctions {

	/*
	 * This method creates an array of random integers. The random numbers are
	 * created by a Java random number generator: given 100, the numbers are
	 * uniformly distributed between 0 and 99. Parameter: sizearray specifies
	 * the number of elements of the array Result: returns an array of random
	 * integers
	 */
	private static double average(int sizearray) {
		// create the array of the specified size
		int[] numberarray = new int[sizearray];
		// create the Java random number generator
		Random randomGenerator = new Random();
		// create sum and average
		double sum = 0;
		double average = 0;
		// for each element of the array, get the next random number
		for (int index = 0; index < sizearray; index++) {
			numberarray[index] = randomGenerator.nextInt(100);
			System.out.println(numberarray[index]);
			sum += numberarray[index];
		}
		average = sum / sizearray;
		return average;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// print average
		System.out.println(average(10));
	}
}
