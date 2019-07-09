import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graphs {
	
	static final int[] dirR = { 0, 0, 1, -1 };
	static final int[] dirC = { 1, -1, 0, 0 };
	public int N;
	public int R;
	public int C;
	static final long INF = Long.MAX_VALUE;
	
	// In range of an matrix
	public boolean inBounds(int r, int c) {
		if (r < R && r >= 0 && c < C && c >= 0) {
			return true;
		}
		return false;
	}
	
	public int[] primAdjMat(int adjMat[][]) {
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
	
	public long[] dijkstraAdjMat(int[][] adjMat, int root) {
		long[] dists = new long[N];
		Arrays.fill(dists, INF);
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
	
	public long[] dijkstraAdjList(LinkedList<Edge>[] adjList, int root) {
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		long[] dists = new long[N];
		Arrays.fill(dists, INF);
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
}

class Node implements Comparable<Node> {
	int num;
	long dist;
	
	public Node(int num, long dist) {
		this.num = num;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Node other) {
		// TODO Auto-generated method stub
		if (this.dist == other.dist) {
			return Integer.compare(this.num, other.num);
		}
		return Long.compare(this.dist, other.dist);
	}
}

class Edge {
	int other;
	int weight;
	
	public Edge(int o, int w) {
		other = o;
		weight = w;
	}
}