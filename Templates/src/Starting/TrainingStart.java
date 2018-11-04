package Starting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TrainingStart {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Input");
		frame.setAlwaysOnTop(true);
		String progName = JOptionPane.showInputDialog(frame, "Program name?");
		String inputOutputFileName = JOptionPane.showInputDialog(frame, "File name?");
		if (progName != null && inputOutputFileName != null) {
			File prog = new File("D:\\bench\\eclipse\\Workspace\\Training\\src\\" + progName + ".java");
			File inputFile = new File("D:\\bench\\eclipse\\Workspace\\Training\\" + inputOutputFileName + ".in");
			File outputFile = new File("D:\\bench\\eclipse\\Workspace\\Training\\" + inputOutputFileName + ".out");
			prog.createNewFile();
			inputFile.createNewFile();
			outputFile.createNewFile();
			FileWriter out = new FileWriter(prog);
			String progContent = "/*\r\n" + "ID: benchen1\r\n" + "LANG: JAVA\r\n" + "TASK: " + progName + "\r\n" + "*/\r\n"
					+ "\r\n" + "import java.io.BufferedReader;\r\n" + "import java.io.BufferedWriter;\r\n"
					+ "import java.io.FileReader;\r\n" + "import java.io.FileWriter;\r\n"
					+ "import java.io.IOException;\r\n" + "import java.io.PrintWriter;\r\n"
					+ "import java.util.StringTokenizer;\r\n" + "\r\n" + "public class " + progName + " {\r\n" + "	\r\n"
					+ "	static int n;\r\n" + "\r\n" + "	public static void main(String[] args) throws IOException {\r\n"
					+ "//		BufferedReader in = new BufferedReader(new FileReader(\"D:\\\\bench\\\\eclipse\\\\Workspace\\\\Training\\\\"
					+ progName + "\\\\1.in\"));\r\n" + "		BufferedReader in = new BufferedReader(new FileReader(\""
					+ inputOutputFileName + ".in\"));\r\n"
					+ "		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"" + inputOutputFileName
					+ ".out\")));\r\n" + "		StringTokenizer ln = new StringTokenizer(in.readLine());\r\n"
					+ "		n = Integer.parseInt(ln.nextToken());\r\n" + "		for (int i = 0; i < n; i++) {\r\n"
					+ "			\r\n" + "		}\r\n" + "		out.close();\r\n" + "		in.close();\r\n" + "	}\r\n"
					+ "}";
			out.write(progContent);
			out.close();
		} else {
			System.out.println("Forgot to input name");
		}
		System.exit(0);
	}
}