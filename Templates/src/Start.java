import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Start {
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Input");
		frame.setAlwaysOnTop(true);
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		UIManager.put("OptionPane.messageFont", font);
		UIManager.put("OptionPane.buttonFont", font);
		String type = JOptionPane.showInputDialog(frame, "Type of problem?").toLowerCase();
		boolean gold = type.equals("gold");
		boolean training = type.equals("training");
		String progName = JOptionPane.showInputDialog(frame, "Program name?");
		String inputOutputFileName = gold ? JOptionPane.showInputDialog(frame, "File name?") : progName;
		String varType = JOptionPane.showInputDialog(frame, "Variable type?");
		String varName = JOptionPane.showInputDialog(frame, "Variable name?");
		boolean isInt = varType.equals("int");
		boolean isString = varType.equals("String");
		String fileDirectory = "";
		String goldDir = "D:\\\\Java\\\\USACO-Gold\\\\Gold\\\\";
		String trainingDir = "D:\\\\Java\\\\USACO-Training\\\\Training\\\\";
		String workspace = "D:\\\\Java\\\\";
		if (type != null && progName != null && inputOutputFileName != null && varName != null && varType != null) {
			if (gold) {
				fileDirectory = goldDir;
			} else {
				fileDirectory = trainingDir;
			}
			File prog = new File(fileDirectory + "src\\" + progName + ".java");
			File inputFile = new File(workspace + inputOutputFileName + ".in");
			File outputFile = new File(workspace + inputOutputFileName + ".out");
			inputFile.createNewFile();
			outputFile.createNewFile();
			prog.createNewFile();
			FileWriter out = new FileWriter(prog);
			String progContent = "";
			if (gold) {
				progContent = "import java.io.BufferedReader;\r\n" + "import java.io.BufferedWriter;\r\n"
						+ "import java.io.FileReader;\r\n" + "import java.io.FileWriter;\r\n"
						+ "import java.io.IOException;\r\n" + "import java.io.PrintWriter;\r\n"
						+ "import java.util.StringTokenizer;\r\n" + "\r\n" + "public class " + progName + " {\r\n"
						+ "\r\n" + "	public static void main(String[] args) throws IOException {\r\n"
						+ "		// BufferedReader in = new BufferedReader(new FileReader(\"" + goldDir + "" + progName
						+ "\\\\1.in\"));\r\n" + "		BufferedReader in = new BufferedReader(new FileReader(\""
						+ inputOutputFileName + ".in\"));\r\n"
						+ "		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\""
						+ inputOutputFileName + ".out\")));\r\n"
						+ "		StringTokenizer tk = new StringTokenizer(in.readLine());\r\n" + "		" + varType
						+ " " + varName + " = "
						+ (isInt ? "Integer.parseInt(tk.nextToken())" : (isString ? "in.readLine()" : "")) + ";\r\n"
						+ (isInt ? "		for (int i = 0; i < " + varName + "; i++) {\r\n" + "			\r\n"
								+ "		}\r\n" : "")
						+ "		out.close();\r\n" + "		in.close();\r\n" + "	}\r\n" + "}";
			} else if (training) {
				progContent = "/*\r\n" + "ID: benchen1\r\n" + "LANG: JAVA\r\n" + "TASK: " + progName + "\r\n" + "*/\r\n"
						+ "\r\n" + "import java.io.BufferedReader;\r\n" + "import java.io.BufferedWriter;\r\n"
						+ "import java.io.FileReader;\r\n" + "import java.io.FileWriter;\r\n"
						+ "import java.io.IOException;\r\n" + "import java.io.PrintWriter;\r\n"
						+ "import java.util.StringTokenizer;\r\n" + "\r\n" + "public class " + progName + " {\r\n"
						+ "\r\n" + "	public static void main(String[] args) throws IOException {\r\n"
						+ "		BufferedReader in = new BufferedReader(new FileReader(\"" + progName + ".in\"));\r\n"
						+ "		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"" + progName
						+ ".out\")));\r\n" + "		StringTokenizer tk = new StringTokenizer(in.readLine());\r\n"
						+ "		" + varType + " " + varName + " = "
						+ (isInt ? "Integer.parseInt(tk.nextToken())" : (isString ? "in.readLine()" : "")) + ";\r\n"
						+ (isInt ? "		for (int i = 0; i < " + varName + "; i++) {\r\n" + "			\r\n"
								+ "		}\r\n" : "")
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
