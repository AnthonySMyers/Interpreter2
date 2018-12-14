import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * parses through the file/program, builds PST and converts it to an AST
 * @author Anthony Myers
 * @author Minhkhoa Vu	minhkhoavu954@gmail.com
 * @author Juan Espinoza
 * @author Miguel Castorena
 */
public class Parser {
	private LinkedList<Node> stack = new LinkedList<Node>();
	private LinkedList<Integer> indexNT = new LinkedList<Integer>();
	private LL_Table table = new LL_Table();
	public AllNodes nodes = new AllNodes();
	private A6_GrammarRules rules = new A6_GrammarRules();
	private Node root, ast;
	private boolean isNotComment = true, errorFlag = false, ntCheck = true;
	public int treeSize = 0, parensCounter = 0, recursiveCount = 0, quoteCount = 0;
	
	/**
	 * default constructor that sets up the PST mechanism method
	 */
	public Parser(){
		stack.add(nodes.eof);
		stack.add(nodes.Pgm);
		root = nodes.Pgm;
		ast = new Node(root.getType(), root.getIndex(), root.getName(), root.getToken());
		root.height = 0;
	}
	
	/**
	 * runs the LL Mechanism algorithm provided in class to create the PST
	 * @param inputStream - current string value in the file/program
	 */
	public void PSTmech(String inputStream){
		while(ntCheck){
			if(stack.getLast().getType().equals(Type.TERMINAL)){
				if(!nodes.terminals.containsKey(inputStream)){
					stack.getLast().setToken(inputStream);
					root.getChildren().set(root.currentChild, new Node(Type.TERMINAL, root.getChildren().get(root.currentChild).getIndex(), root.getChildren().get(root.currentChild).getName(), inputStream, root));
				}
				M1: if(inputStream.equals(stack.getLast().getToken())){
					if(stack.getLast().getToken().equals("$")){
						stack.removeLast();
						ntCheck = false;
						return;
					}
					else if(!stack.getLast().getToken().equals("$")){
						root.currentChild++;
						stack.removeLast();
						ntCheck = false;
						return;
					}
				}
				M2E: if(!inputStream.equals(stack.getLast().getToken())){
					if(stack.getLast().getToken() == null){
						root.setToken(inputStream);
						root = root.getMom();
						ntCheck = false;
						stack.removeLast();
					}
					else{
						errorFlag = true;
						ntCheck = false;
						System.out.println("M2E");
						System.out.println(stack.getLast().getToken()+" : "+inputStream);
						return;
					}
				}
			}
			else if(stack.getLast().getType().equals(Type.NONTERMINAL) && ntCheck){
				int cell = 0;
				if(nodes.terminals.containsKey(inputStream)){
					cell = table.getTable()[stack.getLast().getIndex()][nodes.terminals.get(inputStream)];
				}
				else{
					cell = table.getTable()[stack.getLast().getIndex()][nodes.terminals.get("id")];
				}
				M3E: if(cell == 0){
					System.out.println("["+stack.getLast().getIndex()+", "+nodes.terminals.get(inputStream)+"]");
					errorFlag = true;
					System.out.println("M3E");
					ntCheck = false;
					return;
				}
				M4: if(cell != 0){
					stack.removeLast();
					pushReverse(cell);
					if(root.currentChild < root.totalChildren){
						if(!root.getChildren().isEmpty()){ 
							root = root.getChildren().get(root.currentChild);
						}
					}
					addChildren(cell, root);
					while(root.currentChild >= root.totalChildren){
						root = root.getMom();
						root.currentChild++;
					}
				}
			}
		}
	}
	
	/**
	 * reads the program in a specified file
	 * @param fileName - name of file containing the program
	 * @throws Exception
	 */
	public void readFile(File fileName)throws Exception{
		Scanner scLines = new Scanner(fileName);						//Scanner to read string values line by line
		String currentStr, collectStr = "";								//String used to reference the most recently scanned string from source code text
		while(scLines.hasNextLine() && !errorFlag){
			String lineRead = scLines.nextLine();
			Scanner sc = new Scanner(lineRead);
			isNotComment = true;
			while(sc.hasNext() && !errorFlag){
				ntCheck = true;
				currentStr = sc.next();
				if(currentStr.equals("\"")){
					quoteCount++;
				}
				if(!currentStr.equals("//") && isNotComment){
					if(quoteCount % 2 == 0){
						if(collectStr.equals("")){
							PSTmech(currentStr);
						}
						else{
							collectStr += " "+currentStr;
							PSTmech(collectStr);
							collectStr = "";
						}
					}
					else{
						collectStr += " "+currentStr;
					}
				}
				else{
					isNotComment = false;
				}
			}
			sc.close();
		}
		scLines.close();
		if(!stack.isEmpty()){
			System.out.println("ERROR: Syntax Parse Conflict (Node in Stack): "+stack.getLast().getName());
		}
		if(quoteCount % 2 != 0){
			System.out.println("ERROR: Unbalanced Quotation Marks");
		}
		if(errorFlag){
			System.out.println("ERROR");
		}
	}
	/**
	 * displays the PST
	 * @param curNode - root of PST
	 */
	public void printPST(Node curNode){
		BS: if(curNode == null){
			return;
		}
		POP: if(curNode.getType().equals(Type.TERMINAL)){
			for(int i = 0; i < curNode.height; i++){
				System.out.print("\t");
			}
			System.out.print("(Node: ('"+curNode.getToken()+"')");
			if(treeSize > recursiveCount){
				System.out.println();
			}
			else{
				for(int i = 0; i < parensCounter; i++){
					System.out.print(")");
				}
			}
		}
		else if(curNode.getType().equals(Type.NONTERMINAL)){
			if(curNode.getChildren().size() != 0){
				for(int i = 0; i < curNode.height; i++){
					System.out.print("\t");
				}
				System.out.print("(Node: (Rule: "+curNode.getName()+" = ");
				if(curNode.getChildren().size() == 0){
					System.out.print("null");
				}
			}
			for(int i = 0; i < curNode.getChildren().size(); i++){

				if(curNode.getChildren().get(i).getType().equals(Type.TERMINAL)){
						System.out.print("'"+curNode.getChildren().get(i).getToken()+"' ");
					}
				else if(curNode.getChildren().get(i).getType().equals(Type.NONTERMINAL)){
					if(curNode.getChildren().get(i).getChildren().size() != 0){
						System.out.print(curNode.getChildren().get(i).getName()+" ");
					}
				}
			}
			if(curNode.getChildren().size() != 0){
				System.out.println(")");
			}
		}
		RECURSION: for(int i = 0; i < curNode.getChildren().size(); i++){
			curNode.getChildren().get(i).height = curNode.height+1;
			parensCounter = curNode.height+1;
			recursiveCount++;
			printPST(curNode.getChildren().get(i));
		}
	}
	/**
	 * add the RHS of a rule in reverse order to the stack
	 * @param ruleNumber - rule containing the RHS
	 */
	public void pushReverse(int ruleNumber){
		if(rules.getRules().get(ruleNumber-1)[0] != null)
		{
			for(int i = rules.getRules().get(ruleNumber-1).length-1; i >= 0; i--){
				stack.add(rules.getRules().get(ruleNumber-1)[i]);
			}
		}
	}
	/**
	 * add children nodes to a parent node based on a specified rule 
	 * @param ruleNumber - specific rule
	 * @param momNode - RHS of a rule for the parent node
	 */
	public void addChildren(int ruleNumber, Node momNode){
		if(rules.getRules().get(ruleNumber-1)[0] != null){
			for(int i = 0; i < rules.getRules().get(ruleNumber-1).length; i++){
				momNode.getChildren().add(new Node(rules.getRules().get(ruleNumber-1)[i].getType(), rules.getRules().get(ruleNumber-1)[i].getIndex(), rules.getRules().get(ruleNumber-1)[i].getName(), rules.getRules().get(ruleNumber-1)[i].getToken(), momNode));
				treeSize++;
				momNode.totalChildren++;
			}
		}
	}
	/**
	 * converts the PST to an AST by tree walking the PST
	 * @param curNode - root of PST
	 * @param ast - root of AST 
	 */
	public void PSTtoAST(Node curNode, Node ast){
		BS: if(curNode.getChildren().size() == 0){
			return;
		}
		POP: if(curNode.getType().equals(Type.NONTERMINAL)){
			for(int i = 0; i < curNode.getChildren().size(); i++){
				if(curNode.getChildren().get(i).getChildren().size() != 0 || curNode.getChildren().get(i).getType().equals(Type.TERMINAL)){
					Node newChild = new Node(curNode.getChildren().get(i).getType(), curNode.getChildren().get(i).getIndex(), curNode.getChildren().get(i).getName(), curNode.getChildren().get(i).getToken());
					ast.addChild(newChild);
					ast.getChildren().get(ast.getChildren().size()-1).setMom(ast);
					if(curNode.getChildren().get(i).getType().equals(Type.NONTERMINAL)){
						indexNT.add(ast.getChildren().size()-1);
					}
				}
			}
		}
		RECURSION: for(int i = 0; i < curNode.getChildren().size(); i++){
			if(curNode.getChildren().get(i).getType().equals(Type.NONTERMINAL) && curNode.getChildren().get(i).getChildren().size() != 0){
				int getRid = indexNT.getLast();
				indexNT.removeLast();
				PSTtoAST(curNode.getChildren().get(i), ast.getChildren().get(getRid));
			}
			else{
				PSTtoAST(curNode.getChildren().get(i), ast.getChildren().get(ast.getChildren().size()-1));
			}
		}
	}
	/**
	 * reset root object back to the root of the PST
	 */
	public void resetRoot(){
		if(root != null){
			while(root.getMom() != null){
				root = root.getMom();
			}
		}
	}
	/**
	 * reset root object back to the root of the AST
	 */
	public void resetAST(){
		if(ast != null){
			while(ast.getMom() != null){
				ast = ast.getMom();
			}
		}
	}
	/**
	 * get root of AST structure
	 * @return ast - root of AST
	 */
	public Node getAST(){
		return ast;
	}
	/**
	 * get root of PST structure
	 * @return root - root of PST
	 */
	public Node getRoot(){
		return root;
	}
	/**
	 * determines if an error has been raised
	 * @return errorFlag
	 */
	public boolean getErrorFlag(){
		return errorFlag;
	}
}
