package quicksort;

import java.util.Random;

public class Main {
	public static void main(String[] args) {

		Random random = new Random();
		int[] numbers = new int[10];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(10);
		}

//		for (int i = 0; i < numbers.length; i++) {
//			System.out.print(numbers[i] + " ");
//		}

		quickSort(numbers);

		System.out.println();
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

	}

	public static void quickSort(int[] numbers) {
		quickSort(numbers, 0, numbers.length - 1);
	}

	public static void quickSort(int[] numbers, int lowIndex, int highIndex) {

		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();

		if (lowIndex >= highIndex) {
			return;
		}

		int lowerPointer = lowIndex;
		int higherPointer = highIndex - 1;
		int pivotElement = numbers[highIndex];

		while (lowerPointer < higherPointer) {

			while (lowerPointer < higherPointer && numbers[lowerPointer] <= pivotElement) {
				lowerPointer++;
			}

			while (lowerPointer < higherPointer && numbers[higherPointer] >= pivotElement) {
				higherPointer--;
			}

			swap(numbers, lowerPointer, higherPointer);

		}

		if (numbers[lowerPointer] > numbers[highIndex]) {
			swap(numbers, lowerPointer, highIndex);
		} else {
			lowerPointer = highIndex;
		}

		System.out.println("Left Partion" + lowIndex + " " + (lowerPointer - 1));
		quickSort(numbers, lowIndex, lowerPointer - 1);

		System.out.println("Right Partion " + (lowerPointer + 1) + " " + highIndex);
		quickSort(numbers, lowerPointer + 1, highIndex);

	}

	private static void swap(int[] numbers, int lowerPointer, int highIndex) {
		System.out.println("Swapping : " + lowerPointer + " " + highIndex);
		System.out.println();

		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();

		int temp = numbers[lowerPointer];
		numbers[lowerPointer] = numbers[highIndex];
		numbers[highIndex] = temp;

	}

}
