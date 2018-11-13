import java.util.PriorityQueue;

public class Graphs {

	static void PrimFromEdgeList(int ROW, int COL, PriorityQueue<Edge> edges) {
		boolean[][] used = new boolean[ROW][COL];
		used[0][0] = true;
		int numEdgesIncluded = 0;
		while (numEdgesIncluded < ROW * COL - 1) {
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

	static class Edge implements Comparable<Edge> {
		Vertex v1;
		Vertex v2;
		long weight;

		public Edge(Vertex a, Vertex b) {
			v1 = a;
			v2 = b;

			weight = Math.abs(v1.height - v2.height);
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
		long height;

		public Vertex(int a, int b, long c) {
			row = a;
			col = b;
			height = c;
		}
	}
}
