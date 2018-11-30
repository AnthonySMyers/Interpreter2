import java.io.File;
import java.util.LinkedList;

public class Interpreter {
	private Parser llp;
	private int scopeNumber = 0;
	
	public Interpreter(File parseFile) throws Exception{
		llp = new Parser();
		llp.readFile(parseFile);
		llp.resetRoot();
		//llp.printPST(llp.getRoot());
	}
	
	public void interpretSemantics(){
		
	}
	
	public void runProgram(){
		
	}
	
	public void NumberChecker(String currentStr, int lineCount){
		if(!currentStr.contains(".")){
			try{
				int intCheck = Integer.parseInt(currentStr);
			}
			catch(NumberFormatException e){ }
		}
		if(currentStr.contains(".")){
			try{
				double doubleCheck = Double.parseDouble(currentStr);
			}
			catch(NumberFormatException e){	}
		}
	}
	
	public void TreeWalk(Node curNode){
		BS: if(curNode == null){
			return;
		}
		POP: if(curNode.getType().equals(Type.TERMINAL)){
			
		}
		else if(curNode.getType().equals(Type.NONTERMINAL)){
			if(curNode.getChildren().size() != 0){
				
			}
			for(int i = 0; i < curNode.getChildren().size(); i++){

				if(curNode.getChildren().get(i).getType().equals(Type.TERMINAL)){
				}
				else if(curNode.getChildren().get(i).getType().equals(Type.NONTERMINAL)){
					if(curNode.getChildren().get(i).getChildren().size() != 0){
					}
				}
			}
			if(curNode.getChildren().size() != 0){
				System.out.println(")");
			}
		}
		RECURSION: for(int i = 0; i < curNode.getChildren().size(); i++){
			curNode.getChildren().get(i).height = curNode.height+1;
			llp.parensCounter = curNode.height+1;
			llp.recursiveCount++;
			TreeWalk(curNode.getChildren().get(i));
		}
	}
}
