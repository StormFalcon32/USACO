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
		int N;

		public SegmentTree(int[] arr, int N) {
			this.arr = arr;
			this.N = N;
			int height = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
			int length = (int) Math.pow(2, height);
			tree = new int[length];
			build(1, 0, N - 1);
		}

		void merge(int node) {
			tree[node] = tree[2 * node] + tree[2 * node + 1];
		}

		void build(int node, int start, int end) {
			if (start == end) {
				tree[node] = arr[start];
			} else {
				int mid = (start + end) / 2;
				build(2 * node, start, mid);
				build(2 * node + 1, mid + 1, end);
				merge(node);
			}
		}

		void update(int ind, int val) {
			updateUtil(1, 0, N - 1, ind, val);
		}

		void updateUtil(int node, int start, int end, int ind, int val) {
			if (start == end) {
				arr[ind] = val;
				tree[node] = val;
			} else {
				int mid = (start + end) / 2;
				if (start <= ind && ind <= mid) {
					updateUtil(2 * node, start, mid, ind, val);
				} else {
					updateUtil(2 * node + 1, mid + 1, end, ind, val);
				}
				merge(node);
			}
		}

		int query(int l, int r) {
			return queryUtil(1, 0, N - 1, l, r);
		}

		int queryUtil(int node, int start, int end, int l, int r) {
			if (r < start || end < l) {
				return 0;
			}
			if (l <= start && end <= r) {
				return tree[node];
			}
			int mid = (start + end) / 2;
			int p1 = queryUtil(2 * node, start, mid, l, r);
			int p2 = queryUtil(2 * node + 1, mid + 1, end, l, r);
			return p1 + p2;
		}
	}
}
