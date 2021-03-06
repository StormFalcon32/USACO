import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graphs {

	static final int[] dirR = { 0, 0, 1, -1 };
	static final int[] dirC = { 1, -1, 0, 0 };

	// In range of an matrix
	static boolean inBounds(int r, int c, int R, int C) {
		if (r < R && r >= 0 && c < C && c >= 0) {
			return true;
		}
		return false;
	}

	// MST Algs
	static int[] primAdjMat(int adjMat[][], int N) {
		// Array to store constructed MST
		int parent[] = new int[N];

		int key[] = new int[N];
		Arrays.fill(key, Integer.MAX_VALUE);

		boolean inSet[] = new boolean[N];

		key[0] = 0;
		parent[0] = -1;

		for (int count = 0; count < N - 1; count++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int v = 0; v < N; v++) {
				if (!inSet[v] && key[v] < min) {
					min = key[v];
					minIndex = v;
				}
			}
			int u = minIndex;
			inSet[u] = true;
			for (int v = 0; v < N; v++) {
				if (adjMat[u][v] != 0 && !inSet[v] && adjMat[u][v] < key[v]) {
					parent[v] = u;
					key[v] = adjMat[u][v];
				}
			}
		}
		return parent;
	}

	static KEdge[] kruskalEdgeList(KEdge[] edges, int E) {
		PriorityQueue<KEdge> pq = new PriorityQueue<KEdge>();
		for (int i = 0; i < E; i++) {
			pq.add(edges[i]);
		}
		KEdge[] mst = new KEdge[E];
		// DisjointSet ds = new DisjointSet(N);
		// int index = 0;
		// while (index < N - 1) {
		// KEdge curr = pq.poll();
		// if (!ds.find(curr.source, curr.dest)) {
		// mst[index] = curr;
		// ds.union(curr.source, curr.dest);
		// }
		// }
		return mst;
	}

	static class KEdge implements Comparable<KEdge> {
		int source;
		int dest;
		int weight;

		public KEdge(int s, int d, int w) {
			source = s;
			dest = d;
			weight = w;
		}

		@Override
		public int compareTo(KEdge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	// Shortest Path Algs
	static long[] dijkstraAdjMat(int[][] adjMat, int root, int N) {
		long[] dists = new long[N];
		Arrays.fill(dists, Long.MAX_VALUE);
		boolean[] inSet = new boolean[N];
		dists[root] = 0;

		for (int k = 0; k < N - 1; k++) {

			int smallest = -1;
			long min = Long.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				if (!inSet[i] && dists[i] < min) {
					smallest = i;
					min = dists[i];
				}
			}
			inSet[smallest] = true;

			for (int v = 0; v < N; v++) {
				long distsThroughU = dists[smallest] + adjMat[smallest][v];
				if (!inSet[v] && adjMat[smallest][v] != 0) {
					dists[v] = Math.min(dists[v], distsThroughU);
				}
			}
		}
		return dists;
	}

	static long[] dijkstraAdjList(LinkedList<Edge>[] adjList, int root, int N) {
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		long[] dists = new long[N];
		Arrays.fill(dists, Long.MAX_VALUE);
		boolean[] inSet = new boolean[N];
		heap.add(new Node(root, 0));
		dists[root] = 0;

		while (!heap.isEmpty()) {
			int u = heap.poll().num;
			inSet[u] = true;
			LinkedList<Edge> adj = adjList[u];
			for (Edge currEdge : adj) {
				int v = currEdge.other;
				long distsThroughU = dists[u] + currEdge.weight;
				if (!inSet[v]) {
					if (distsThroughU < dists[v]) {
						dists[v] = distsThroughU;
						heap.add(new Node(v, distsThroughU));
					}
				}
			}
		}
		return dists;
	}

	static class Node implements Comparable<Node> {
		int num;
		long dist;

		public Node(int num, long dist) {
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dist == other.dist) {
				return Integer.compare(this.num, other.num);
			}
			return Long.compare(this.dist, other.dist);
		}
	}

	static class Edge {
		int other;
		int weight;

		public Edge(int o, int w) {
			other = o;
			weight = w;
		}
	}

	// Tree Algs
	static void preorder(LinkedList<Integer>[] adjList, int N) {
		boolean[] visited = new boolean[N];
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(0);
		while (!s1.isEmpty()) {
			int curr = s1.pop();
			visited[curr] = true;
			System.out.println(curr);
			// reverse order to match up with recursive dfs
			Iterator<Integer> it = adjList[curr].descendingIterator();
			while (it.hasNext()) {
				int toTry = it.next();
				if (!visited[toTry]) {
					s1.push(toTry);
				}
			}
		}
	}

	static void postorder(LinkedList<Integer>[] adjList, int N) {
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		boolean[] visited = new boolean[N];
		s1.add(0);
		while (!s1.isEmpty()) {
			int curr = s1.pop();
			visited[curr] = true;
			s2.push(curr);
			for (int adj : adjList[curr]) {
				if (!visited[adj]) {
					s1.push(adj);
				}
			}
		}
		while (!s2.isEmpty()) {
			int curr = s2.pop();
			System.out.println(curr);
		}
	}

	static class LCA {
		int[] depth;
		int[][] parent;
		int level;
		LinkedList<Integer>[] adjList;
		int N;

		public LCA(LinkedList<Integer>[] adjList, int N) {
			this.adjList = adjList;
			this.N = N;
			level = (int) (Math.ceil(Math.log(N) / Math.log(2)));
			depth = new int[N];
			parent = new int[N][level];
			dfsLCA(0, -1);
			precomputeSparseMatrix();
		}

		public void dfsLCA(int curr, int prev) {
			if (curr != 0) {
				depth[curr] = depth[prev] + 1;
				parent[curr][0] = prev;
			}
			for (int adj : adjList[curr]) {
				if (adj != prev) {
					dfsLCA(adj, curr);
				}
			}
		}

		public void precomputeSparseMatrix() {
			for (int i = 1; i < level; i++) {
				for (int node = 0; node < N; node++) {
					if (parent[node][i - 1] != -1)
						parent[node][i] = parent[parent[node][i - 1]][i - 1];
				}
			}
		}

		public int lca(int u, int v) {
			if (depth[v] < depth[u]) {
				int temp = u;
				u = v;
				v = temp;
			}
			int diff = depth[v] - depth[u];
			for (int i = 0; i < level; i++) {
				if (((diff >> i) & 1) == 1) {
					v = parent[v][i];
				}
			}
			if (u == v) {
				return u;
			}
			for (int i = level - 1; i >= 0; i--) {
				if (parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			return parent[u][0];
		}
	}
}