import java.util.Arrays;

public class Graphs {
	
	static int[] dirR = { 0, 0, 1, -1 };
	static int[] dirC = { 1, -1, 0, 0 };
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
	
	static void primAdjMat(int adj[][]) {
		// Array to store constructed MST
		int parent[] = new int[V];
		
		int key[] = new int[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		
		boolean mstSet[] = new boolean[V];
		
		key[0] = 0;
		parent[0] = -1;
		
		for (int count = 0; count < V - 1; count++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			
			for (int v = 0; v < V; v++) {
				if (mstSet[v] == false && key[v] < min) {
					min = key[v];
					minIndex = v;
				}
			}
			int u = minIndex;
			mstSet[u] = true;
			for (int v = 0; v < V; v++) {
				if (adj[u][v] != 0 && mstSet[v] == false && adj[u][v] < key[v]) {
					parent[v] = u;
					key[v] = adj[u][v];
				}
			}
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
}
