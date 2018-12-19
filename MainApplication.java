import java.io.File;
import java.util.Scanner;

/**
 * Main application to run Interpreter
 * Program prompts user to input location of file containing A6 language code
 * and outputs the execution of the A6 language code into the console.
 * Additional console input may be required depending on the code contained in the file passed.
 * @author Minhkhoa Vu	minhkhoavu954@gmail.com
 * @author Anthony Myers
 * @author Juan Espinoza
 * @author Miguel Castorena
 */

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
