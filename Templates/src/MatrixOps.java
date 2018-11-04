public class MatrixOps {
	static boolean[][] rotateClockwise90(boolean[][] matrix, int ROWS, int COLS) {
//		Matrix guaranteed to be square
		boolean[][] ret = new boolean[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				ret[c][ROWS - r - 1] = matrix[r][c];
			}
		}
		return ret;
	}
	
	static boolean[][] reflectHorizontal(boolean[][] matrix, int ROWS, int COLS) {
//		Matrix guaranteed to be square
//		Can't set ret = matrix (pass by value)
		boolean[][] ret = new boolean[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				ret[r][c] = matrix[r][c];
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS / 2; c++) {
				ret[r][c] = matrix[r][COLS - c - 1];
				ret[r][COLS - c - 1] = matrix[r][c];
			}
		}
		return ret;
	}
}
