package Starting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GoldTemplate {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\Workspace\\Gold\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("PLACEHOLD.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("PLACEHOLD.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < n; i++) {
			
		}
		out.close();
		in.close();
	}
}
