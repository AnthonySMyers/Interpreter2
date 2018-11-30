import java.io.File;
import java.util.Scanner;

public class MainApplication {
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		Parser llp = new Parser();
		System.out.print("Enter the path of the file: ");
		String fileName = in.nextLine();
		File parseFile= new File(fileName);										//create file from specified file path
		if(parseFile.exists()) {												//check if tokenFile exists
			if(parseFile.isFile() && parseFile.canRead()) {						//check if tokenFile is a file that can be read
				llp.readFile(parseFile);										//pass file to lexer
				llp.resetRoot();
				llp.printPST(llp.getRoot());
			}else {
				System.out.println("File cannot be read.");						//print error message
			}
		}else {
			System.out.println("File does not exist at specified path.");		//print error message
		}
		in.close();
	}

}
