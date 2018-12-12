import java.io.File;
import java.util.Scanner;

public class MainApplication {
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the path of the file: ");
		String fileName = in.nextLine();
		File parseFile= new File(fileName);										//create file from specified file path
		if(parseFile.exists()) {												//check if tokenFile exists
			if(parseFile.isFile() && parseFile.canRead()) {						//check if tokenFile is a file that can be read
				Interpreter inp = new Interpreter(parseFile);										//pass file to lexer
			}else {
				System.out.println("File cannot be read.");						//print error message
			}
		}else {
			System.out.println("File does not exist at specified path.");		//print error message
		}
		in.close();
	}

}
