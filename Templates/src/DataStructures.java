import java.util.Arrays;

public class DataStructures {
}

class BITree {
	int BITree[];
	int N;
	
	public BITree(int N) {
		this.N = N;
		BITree = new int[N + 1];
	}
	
	public int get(int index) {
		int sum = 0;
		index = index + 1;
		while (index > 0) {
			sum += BITree[index];
			index -= index & (-index);
		}
		return sum;
	}
	
	public void update(int index, int val) {
		index = index + 1;
		
		while (index <= N) {
			BITree[index] += val;
			index += index & (-index);
		}
	}
}

class DisjointSet {
	int[] arr;
	int[] size;
	int N;
	
	public DisjointSet(int N) {
		this.N = N;
		arr = new int[N];
		size = new int[N];
		Arrays.fill(size, 1);
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}
	}
	
	public int root(int curr) {
		while (arr[curr] != curr) {
			arr[curr] = arr[arr[curr]];
			curr = arr[curr];
		}
		return curr;
	}
	
	public boolean find(int a, int b) {
		if (root(a) == root(b)) {
			return true;
		}
		return false;
	}
	
	public void union(int a, int b) {
		int aRoot = root(a);
		int bRoot = root(b);
		if (size[aRoot] < size[bRoot]) {
			// Subset B is bigger than subset A, so B should be A's parent
			arr[aRoot] = arr[bRoot];
			size[bRoot] += size[aRoot];
		} else {
			arr[bRoot] = arr[aRoot];
			size[aRoot] += size[bRoot];
		}
	}
}
