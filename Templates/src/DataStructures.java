import java.util.Arrays;

public class DataStructures {
	static class BIT {
		int BIT[];
		int N;

		public BIT(int N) {
			this.N = N;
			BIT = new int[N + 1];
		}

		public int get(int index) {
			int sum = 0;
			index++;
			while (index > 0) {
				sum += BIT[index];
				index -= index & (-index);
			}
			return sum;
		}

		public void update(int index, int val) {
			index++;
			while (index <= N) {
				BIT[index] += val;
				index += index & (-index);
			}
		}
	}

	static class DisjointSet {
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

	static class SegmentTree {
		int[] tree;
		int[] arr;

		public SegmentTree(int[] arr) {
			this.arr = arr;
			int height = (int) (Math.ceil(Math.log(arr.length) / Math.log(2)));
			int length = 2 * (int) Math.pow(2, height) - 1;
			tree = new int[length];
			build(1, 0, arr.length - 1);
		}

		void build(int node, int start, int end) {
			if (start == end) {
				// Leaf node will have a single element
				tree[node] = arr[start];
			} else {
				int mid = (start + end) / 2;
				// Recurse on the left child
				build(2 * node, start, mid);
				// Recurse on the right child
				build(2 * node + 1, mid + 1, end);
				// Internal node will have the sum of both of its children
				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		void update(int node, int start, int end, int ind, int val) {
			if (start == end) {
				// Leaf node
				arr[ind] += val;
				tree[node] += val;
			} else {
				int mid = (start + end) / 2;
				if (start <= ind && ind <= mid) {
					// If idx is in the left child, recurse on the left child
					update(2 * node, start, mid, ind, val);
				} else {
					// if idx is in the right child, recurse on the right child
					update(2 * node + 1, mid + 1, end, ind, val);
				}
				// Internal node will have the sum of both of its children
				tree[node] = tree[2 * node] + tree[2 * node + 1];
			}
		}

		int query(int node, int start, int end, int l, int r) {
			if (r < start || end < l) {
				// range represented by a node is completely outside the given range
				return 0;
			}
			if (l <= start && end <= r) {
				// range represented by a node is completely inside the given range
				return tree[node];
			}
			// range represented by a node is partially inside and partially outside the
			// given range
			int mid = (start + end) / 2;
			int p1 = query(2 * node, start, mid, l, r);
			int p2 = query(2 * node + 1, mid + 1, end, l, r);
			return (p1 + p2);
		}
	}
}
