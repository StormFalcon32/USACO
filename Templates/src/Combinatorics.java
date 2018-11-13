public class Combinatorics {

	static void CombWithRep(int[] chosen, int[] arr, int currLength, int length, int start, int end) {
		// Combinations (order doesn't matter 12 is the same as 21) with repeated digits
		// allowed
		if (currLength == length) {
			int num = 0;
			for (int i = 0; i < length; i++) {
				num += arr[chosen[i]] * Math.pow(10, length - i - 1);
			}
			System.out.println(num);
			return;
		}

		for (int i = start; i < end; i++) {
			chosen[currLength] = i;
			CombWithRep(chosen, arr, currLength + 1, length, i, end);
		}
		return;
	}

	static void MakeAll(int[] chosen, int[] arr, int currLength, int length) {
		// Repeated digits allowed, order matters, all combinations
		if (currLength == length) {
			int num = 0;
			for (int i = 0; i < length; i++) {
				num += arr[chosen[i]] * Math.pow(10, length - i - 1);
			}
			System.out.println(num);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			chosen[currLength] = i;
			MakeAll(chosen, arr, currLength + 1, length);
		}
		return;
	}

	static void CombWithoutRep(int[] chosen, int[] arr, int currLength, int length, int start, int end) {
		// Combinations (order doesn't matter 12 is the same as 21) with no repeated
		// digits allowed
		if (currLength == length) {
			int num = 0;
			for (int i = 0; i < length; i++) {
				num += arr[chosen[i]] * Math.pow(10, length - i - 1);
			}
			System.out.println(num);
			return;
		}
		for (int i = start; i < end && end - i + 1 > length - currLength; i++) {
			chosen[currLength] = i;
			CombWithoutRep(chosen, arr, currLength + 1, length, i + 1, end);
		}
		return;
	}
}
