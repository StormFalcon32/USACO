import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graphs {
	
	static int[] dirR = {0, 0, 1, -1};
	static int[] dirC = {1, -1, 0, 0};
	static int V;
	static int R;
	static int C;

	// In range of an matrix
	static boolean inBounds(int r, int c) {
		if (r < R && r >= 0 && c < C && c >= 0) {
			return true;
		}
		return false;
	}
	
	static void primFromEdgeList(PriorityQueue<Edge> edges) {
		boolean[][] used = new boolean[R][C];
		used[0][0] = true;
		int numEdgesIncluded = 0;
		while (numEdgesIncluded < R * C - 1) {
			Edge curr = edges.poll();
			Vertex v1 = curr.v1;
			Vertex v2 = curr.v2;
			if (used[v1.row][v1.col] && used[v2.row][v2.col]) {
				continue;
			}
			Vertex newVertex = v1;
			if (used[v1.row][v1.col]) {
				newVertex = v2;
			}
			used[newVertex.row][newVertex.col] = true;
			numEdgesIncluded++;
			// Pseudo code to add all neighboring edges from new vertex
			// Code
			// Code
			// Code
		}
	}
	
	static int[] dijkstraAdjMat(int[][] adj, int root) {
		int[] dist = new int[V];
		Arrays.fill(dist, 1 << 20);
		boolean[] inSet = new boolean[V];
		dist[root] = 0;
		
		for (int k = 0; k < V - 1; k++) {
			
			int u = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < V; i++) {
				if (!inSet[i] && dist[i] < min) {
					u = i;
					min = dist[i];
				}
			}
			inSet[u] = true;
			
			for (int v = 0; v < V; v++) {
				int distThroughU = dist[u] + adj[u][v];
				if (!inSet[v] && adj[u][v] != 0 && distThroughU < dist[v]) {
					dist[v] = distThroughU;
				}
			}
		}
		return dist;
	}
	
	static void floodFill(LinkedList<Vertex>[][] adjList, Vertex root, boolean[][] visited) {
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		q.add(root);
		while (!q.isEmpty()) {
			Vertex curr = q.pollFirst();
			visited[curr.row][curr.col] = true;
			LinkedList<Vertex> adj = adjList[curr.row][curr.col];
			for (int i = 0; i < adj.size(); i++) {
				Vertex target = adj.get(i);
				if (!visited[target.row][target.col]) {
					visited[target.row][target.col] = true;
					q.add(target);
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		Vertex v1;
		Vertex v2;
		long weight;

		public Edge(Vertex a, Vertex b) {
			v1 = a;
			v2 = b;

			weight = Math.abs(v1.weight - v2.weight);
		}

		@Override
		public int compareTo(Edge other) {
			if (this.weight == other.weight) {
				return 1;
			}
			return Long.compare(this.weight, other.weight);
		}
	}

	static class Vertex {
		int row;
		int col;
		long weight;

		public Vertex(int a, int b, long c) {
			row = a;
			col = b;
			weight = c;
		}
	}
}
