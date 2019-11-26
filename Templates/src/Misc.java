import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Misc {

	static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	// Checking for palindromes and changing bases

	static ArrayList<Integer> changeBase(int num, int base) {
		ArrayList<Integer> newDigits = calcDigits(num, base);
		return newDigits;
	}

	static boolean isPalindrome(ArrayList<Integer> digits) {
		int numDigits = digits.size();
		for (int i = 0; i < numDigits / 2; i++) {
			if (digits.get(i) != digits.get(numDigits - i - 1)) {
				return false;
			}
		}
		return true;
	}

	static ArrayList<Integer> calcDigits(int num, int base) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while (num > 0) {
			digits.add(num % base);
			num /= base;
		}
		return digits;
	}

	static int calcNumDigits(int num) {
		int ret = 0;
		while (num > 0) {
			ret++;
			num /= 10;
		}
		return ret;
	}

	// Simple primality test
	static boolean isPrime(int num) {
		if (num % 2 == 0) {
			return false;
		}
		int upperLim = (int) Math.sqrt(num);
		for (int i = 3; i <= upperLim; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	// Binary and bits

	static boolean[] toBool(int x, int numDigs) {
		boolean[] arr = new boolean[numDigs];
		for (int i = 0; i < numDigs; i++) {
			if ((x & (1 << i)) == 1 << i) {
				arr[i] = true;
			}
		}
		return arr;
	}

	static int toBin(boolean[] arr) {
		int binary = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i]) {
				binary |= (1 << 0);
			}
			if (i != 0) {
				binary <<= 1;
			}
		}
		return binary;
	}

	static int numSet(int bin) {
		int count = 0;
		while (bin > 0) {
			count += bin & 1;
			bin >>= 1;
		}
		return count;
	}

	static void binaryCombinatorics(int numDigs) {
		int max = 0;
		for (int i = 0; i < numDigs; i++) {
			max += 1 << i;
		}
		// 0 means all excluded and max means all included
		for (int x = 0; x <= max; x++) {
			for (int i = 0; i < numDigs; i++) {
				if ((x & (1 << i)) == 1 << i) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
			System.out.println();
		}
	}

	static void seedGen(int N) {
		long[] seeds = new long[N];

		for (int i = 0; i < N; i++) {
			// Fill seeds with random ints
			seeds[i] = BigInteger.probablePrime(31, new Random()).longValue();
		}
	}

	// binary search variants

	static int lowerBound(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (value <= arr[mid]) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	static int upperBound(int[] arr, int value) {
		int low = 0;
		int high = arr.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (value >= arr[mid]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
