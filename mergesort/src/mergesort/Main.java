package mergesort;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random random = new Random();
		int[] numbers = new int[10];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = random.nextInt(10);
		}
		printArray(numbers);

		mergeSort(numbers);
		printArray(numbers);
	}

	private static void mergeSort(int[] numbers) {

		if (numbers.length <= 1)
			return;

		int midIndex = numbers.length / 2;

		int[] leftArray = new int[midIndex];
		int[] rightArray = new int[numbers.length - midIndex];

		for (int i = 0; i < midIndex; i++) {
			leftArray[i] = numbers[i];
		}

		for (int i = midIndex; i < numbers.length; i++) {
			rightArray[i - midIndex] = numbers[i];
		}

		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(numbers, leftArray, rightArray);
	}

	private static void merge(int[] numbers, int[] leftArray, int[] rightArray) {
		int leftPointer = 0;
		int rightPointer = 0;
		int mainPointer = 0;

		while (leftPointer < leftArray.length && rightPointer < rightArray.length) {
			if (leftArray[leftPointer] < rightArray[rightPointer]) {
				numbers[mainPointer] = leftArray[leftPointer];
				leftPointer++;
			} else {
				numbers[mainPointer] = rightArray[rightPointer];
				rightPointer++;
			}
			mainPointer++;
		}
		// Cleanup the remaining
		while (leftPointer < leftArray.length) {
			numbers[mainPointer] = leftArray[leftPointer];
			leftPointer++;
			mainPointer++;
		}
		while (rightPointer < rightArray.length) {
			numbers[mainPointer] = rightArray[rightPointer];
			rightPointer++;
			mainPointer++;
		}

	}

	private static void printArray(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
}
