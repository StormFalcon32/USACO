import java.util.LinkedList;

public class Misc {
	
	static int ROWS;
	static int COLS;

	// Checking for palindromes and changing bases

	static LinkedList<Integer> changeBase(int num, int base) {
		LinkedList<Integer> newDigits = calcDigits(num, base);
		return newDigits;
	}

	static boolean isPalindrome(LinkedList<Integer> digits) {
		int numDigits = digits.size();
		for (int i = 0; i < numDigits / 2; i++) {
			if (digits.get(i) != digits.get(numDigits - i - 1)) {
				return false;
			}
		}
		return true;
	}

	static LinkedList<Integer> calcDigits(int num, int base) {
		LinkedList<Integer> digits = new LinkedList<Integer>();
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
	
//	Simple primality test
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
	
//	In range of an matrix
	static boolean inBounds(int r, int c) {
		if (r < ROWS && r >= 0 && c < COLS && c >= 0) {
			return true;
		}
		return false;
	}
	
	static void binaryToBools(int x, int numDigs) {
		for (int i = 0; i < numDigs; i++) {
			if ((x & (1 << i)) == 1 << i) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}
}
