import java.util.HashSet;
import java.util.LinkedList;

public class Misc {

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

	// Bitwise sieve to check for primes

	static int ifnotPrime(int prime[], int x) {
		return (prime[x / 64] & (1 << ((x >> 1) & 31)));
	}

	static void makeComposite(int prime[], int x) {
		prime[x / 64] |= (1 << ((x >> 1) & 31));
	}

	static HashSet<Integer> bitwiseSieve(int n) {
		// All primes <= n
		int prime[] = new int[n / 64 + 1];
		for (int i = 3; i * i <= n; i += 2) {
			if (ifnotPrime(prime, i) == 0) {
				for (int j = i * i, k = i << 1; j < n; j += k) {
					makeComposite(prime, j);
				}
			}
		}

		HashSet<Integer> ret = new HashSet<Integer>();

		ret.add(2);
		for (int i = 3; i <= n; i += 2) {
			if (ifnotPrime(prime, i) == 0) {
				ret.add(i);
			}
		}
		return ret;
	}
}
