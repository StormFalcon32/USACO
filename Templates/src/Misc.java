import java.util.LinkedList;

public class Misc {
	
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
}
