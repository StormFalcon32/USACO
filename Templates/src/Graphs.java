import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs {
	
	static final int[] dirR = { 0, 0, 1, -1 };
	static final int[] dirC = { 1, -1, 0, 0 };
	public int N;
	public int R;
	public int C;
	static final int INF = 1 << 30;
	
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
	
	public int[] dijkstraAdjMat(int[][] adjMat, int root) {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		boolean[] inSet = new boolean[N];
		dist[root] = 0;
		
		for (int k = 0; k < N - 1; k++) {
			
			int smallest = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				if (!inSet[i] && dist[i] < min) {
					smallest = i;
					min = dist[i];
				}
			}
			inSet[smallest] = true;
			
			for (int v = 0; v < N; v++) {
				int distThroughU = dist[smallest] + adjMat[smallest][v];
				if (!inSet[v] && adjMat[smallest][v] != 0) {
					dist[v] = Math.min(dist[v], distThroughU);
				}
			}
		}
		return dist;
	}
	
	public int[] dijkstraAdjList(LinkedList<Edge>[] adjList, int root) {
		Queue<Node> heap = new PriorityQueue<Node>();
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		boolean[] inSet = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (i == root) {
				continue;
			}
			heap.add(new Node(i, INF));
		}
		heap.add(new Node(root, 0));
		dist[root] = 0;
		
		for (int k = 0; k < N - 1; k++) {
			Node smallest = heap.poll();
			inSet[smallest.num] = true;
			LinkedList<Edge> adj = adjList[smallest.num];
			ListIterator<Edge> iterate = adj.listIterator();
			while (iterate.hasNext()) {
				Edge currEdge = iterate.next();
				Node toUpdate = currEdge.n1 == smallest ? currEdge.n2 : currEdge.n1;
				int distThroughU = smallest.dist + currEdge.weight;
				if (!inSet[toUpdate.num]) {
					toUpdate.dist = Math.min(toUpdate.dist, distThroughU);
					dist[toUpdate.num] = toUpdate.dist;
				}
			}
		}
		return dist;
	}
}

class Node implements Comparable<Node> {
	int num;
	int dist;
	
	public Node(int v, int d) {
		num = v;
		dist = d;
	}
	
	@Override
	public int compareTo(Node other) {
		// TODO Auto-generated method stub
		if (this.dist == other.dist) {
			return Integer.compare(this.num, other.num);
		}
		return Integer.compare(this.dist, other.dist);
	}
}

class Edge {
	Node n1;
	Node n2;
	int weight;
	
	public Edge(Node a, Node b, int w) {
		n1 = a;
		n2 = b;
		weight = w;
	}
}