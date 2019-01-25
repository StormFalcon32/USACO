import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {

	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			int random = (int) (Math.random() * 100);
			l.add(random);
		}
		Collections.sort(l);
		int index = bSearch(0, 999, 56, l);
		System.out.println(index);
		System.out.println(l.get(index));
	}

	// Returns index of value
	// Recursive
	// ArrayList version
	public static int bSearch(int l, int h, int val, ArrayList<Integer> list) {
		if (l <= h) {
			int mid = (h + l) / 2;
			int midVal = list.get(mid);
			if (midVal == val) {
				return mid;
			}
			if (val < midVal) {
				return bSearch(l, mid - 1, val, list);
			} else {
				return bSearch(mid + 1, h, val, list);
			}
		}
		return -1;
	}

	// Array version

	public static int bSearch(int l, int h, int val, int[] list) {
		if (l <= h) {
			int mid = (h + l) / 2;
			int midVal = list[mid];
			if (midVal == val) {
				return mid;
			}
			if (val < midVal) {
				return bSearch(l, mid - 1, val, list);
			} else {
				return bSearch(mid + 1, h, val, list);
			}
		}
		return -1;
	}
}
