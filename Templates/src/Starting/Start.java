package Starting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Start {
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Input");
		frame.setAlwaysOnTop(true);
		String type = JOptionPane.showInputDialog(frame, "Type of problem?");
		boolean gold = type.equals("Gold");
		String progName = JOptionPane.showInputDialog(frame, "Program name?");
		String inputOutputFileName = gold ? JOptionPane.showInputDialog(frame, "File name?") : null;
		String loc = JOptionPane.showInputDialog(frame, "Home (h), school (s), or other (o)?");
		String varName = JOptionPane.showInputDialog(frame, "Variable name?");
		String varType = JOptionPane.showInputDialog(frame, "Variable type?");
		boolean isInt = varType.equals("int");
		boolean isString = varType.equals("String");
		String fileDirectory = "";
		String homeGold = "C:\\\\Users\\\\bench\\\\git\\\\USACO-Gold\\\\Gold\\\\";
		String homeTraining = "C:\\\\Users\\\\bench\\\\git\\\\USACO-Training\\\\Training\\\\";
		String schoolGold = "H:\\\\git\\\\USACO-Gold\\\\Gold\\\\";
		String schoolTraining = "H:\\\\git\\\\USACO-Training\\\\Training\\\\";
		if (type != null && progName != null && inputOutputFileName != null && loc != null && varName != null && varType != null) {
			switch (loc) {
			case "s":
				fileDirectory = gold ? schoolGold : schoolTraining;
				break;
			case "h":
				fileDirectory = gold ? homeGold : homeTraining;
				break;
			case "o":
				fileDirectory = JOptionPane.showInputDialog(frame, "Directory?");
				break;
			default:
				System.out.println("Invalid location");
				System.exit(0);
				break;
			}
			File prog = new File(fileDirectory + "src\\" + progName + ".java");
			File inputFile = new File(fileDirectory + inputOutputFileName + ".in");
			File outputFile = new File(fileDirectory + inputOutputFileName + ".out");
			prog.createNewFile();
			inputFile.createNewFile();
			outputFile.createNewFile();
			FileWriter out = new FileWriter(prog);
			String progContent;
			if (gold) {
				progContent = "import java.io.BufferedReader;\r\n" + "import java.io.BufferedWriter;\r\n" + "import java.io.FileReader;\r\n"
						+ "import java.io.FileWriter;\r\n" + "import java.io.IOException;\r\n" + "import java.io.PrintWriter;\r\n"
						+ "import java.util.StringTokenizer;\r\n" + "\r\n" + "public class " + progName + " {\r\n" + "\r\n"
						+ "	public static void main(String[] args) throws IOException {\r\n"
						+ "		// BufferedReader in = new BufferedReader(new FileReader(\"" + homeGold + "" + progName + "\\\\1.in\"));\r\n"
						+ "		// BufferedReader in = new BufferedReader(new FileReader(\"" + schoolGold + "" + progName + "\\\\1.in\"));\\r\\n"
						+ "		BufferedReader in = new BufferedReader(new FileReader(\"" + inputOutputFileName + ".in\"));\r\n"
						+ "		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"" + inputOutputFileName + ".out\")));\r\n"
						+ "		StringTokenizer tk = new StringTokenizer(in.readLine());\r\n" + "		" + varType + " " + varName + " = "
						+ (isInt ? "Integer.parseInt(tk.nextToken())" : (isString ? "in.readLine()" : "")) + ";\r\n"
						+ (isInt ? "		for (int i = 0; i < " + varName + "; i++) {\r\n" + "			\r\n" + "		}\r\n" : "")
						+ "		out.close();\r\n" + "		in.close();\r\n" + "	}\r\n" + "}";
			} else {
				progContent = "/*\r\n" + "ID: benchen1\r\n" + "LANG: JAVA\r\n" + "TASK: " + progName + "\r\n" + "*/\r\n" + "\r\n"
						+ "import java.io.BufferedReader;\r\n" + "import java.io.BufferedWriter;\r\n" + "import java.io.FileReader;\r\n"
						+ "import java.io.FileWriter;\r\n" + "import java.io.IOException;\r\n" + "import java.io.PrintWriter;\r\n"
						+ "import java.util.StringTokenizer;\r\n" + "\r\n" + "public class " + progName + " {\r\n" + "\r\n"
						+ "	public static void main(String[] args) throws IOException {\r\n"
						+ "		BufferedReader in = new BufferedReader(new FileReader(\"" + progName + ".in\"));\r\n"
						+ "		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"" + progName + ".out\")));\r\n"
						+ "		StringTokenizer tk = new StringTokenizer(in.readLine());\r\n" + "		" + varType + " " + varName + " = "
						+ (isInt ? "Integer.parseInt(tk.nextToken())" : (isString ? "in.readLine()" : "")) + ";\r\n"
						+ (isInt ? "		for (int i = 0; i < " + varName + "; i++) {\r\n" + "			\r\n" + "		}\r\n" : "")
						+ "		out.close();\r\n" + "		in.close();\r\n" + "	}\r\n" + "}";
			}
			out.write(progContent);
			out.close();
		} else {
			System.out.println("Forgot to input name");
		}
		System.exit(0);
	}
}
