import java.util.ArrayList;
import java.util.HashMap;

public class DP {
	
	static HashMap<State, Long> states = new HashMap<State, Long>();
	
	static long make(ArrayList<Integer> elements, int currInd, int currSum) {
		// Base cases
		State curr = new State(currInd, currSum);
		if (states.containsKey(curr)) {
			return states.get(curr);
		}
		if (currInd < 0 && currSum > 0) {
			return 0;
		}
		if (currSum < 0) {
			return 0;
		}
		if (currSum == 0) {
			return 1;
		}
		// Return make with current element and without
		
		long result = 0;
		
		states.put(curr, result);
		return result;
	}
	
	static class State {
		int numInd;
		int sum;

		public State(int a, int b) {
			numInd = a;
			sum = b;
		}

		@Override
		public boolean equals(Object o) {
			State other = (State) o;
			if (this.numInd == other.numInd && this.sum == other.sum) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 31 * hash + numInd;
			hash = 31 * sum;
			return hash;
		}
	}
}
