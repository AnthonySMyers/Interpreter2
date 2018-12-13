import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Interpreter {
	private Parser llp;
	private int scopeNumber = 0, curVal, opCount = 0, quoteCount = 0;
	private ScopeTreeNode rootSCT = new ScopeTreeNode();
	private boolean insidePrint = false, listChange = false, isAssignment = false, isPrint = false, isInput = false, errorFlag = false, isNotComment = true;
	private String curVar;
	private LinkedList<OpPair> addSub = new LinkedList<OpPair>() 
								, mulDiv = new LinkedList<OpPair>()
								, exp = new LinkedList<OpPair>()
								, group = new LinkedList<OpPair>();
	private LinkedList<Double> expVals = new LinkedList<Double>(); 
	
	public Interpreter(File parseFile) throws Exception{
		llp = new Parser();
		llp.readFile(parseFile);
		llp.resetRoot();
		//llp.PSTtoAST(llp.getRoot(), llp.getAST());
		//llp.resetAST();
		//llp.resetRoot();
		//llp.printPST(llp.getRoot());
		errorFlag = llp.getErrorFlag();
		RunProgram(parseFile);
	}
	
	public void interpretSemantics(){
		
	}
	
	public void buildSCT(){
		
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
	
	public boolean IntCheck(String currentStr){
		try{
			int intCheck = Integer.parseInt(currentStr);
			return true;
		}
		catch(NumberFormatException e){ return false; }
	}
	
	public boolean FloatCheck(String currentStr){
		try{
			double doubleCheck = Double.parseDouble(currentStr);
			return true;
		}
		catch(NumberFormatException e){	return false;}
	}
	
	/*public void TreeWalk(Node curNode){
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
			}
		}
		RECURSION: for(int i = 0; i < curNode.getChildren().size(); i++){
			curNode.getChildren().get(i).height = curNode.height+1;
			llp.parensCounter = curNode.height+1;
			llp.recursiveCount++;
			TreeWalk(curNode.getChildren().get(i));
		}
	}*/
	
	public void handleExponents(OpPair e){
		//System.out.println(expVals.get(e.index1)+"^"+expVals.get(e.index2));
		expVals.set(e.index1, Math.pow(expVals.get(e.index1), expVals.get(e.index2)));
		expVals.remove(e.index2);
		for(OpPair md : mulDiv){
			if(md.index1 >= e.index2){
				mulDiv.get(mulDiv.indexOf(md)).index1--;
				mulDiv.get(mulDiv.indexOf(md)).index2--;
			}
		}
		for(OpPair as : addSub){
			if(as.index1 >= e.index2){
				addSub.get(addSub.indexOf(as)).index1--;
				addSub.get(addSub.indexOf(as)).index2--;
			}
		}
		for(OpPair e2 : exp){
			if(e2.index1 >= e.index2){
				exp.get(exp.indexOf(e2)).index1--;
				exp.get(exp.indexOf(e2)).index2--;
			}
		}
		exp.remove(e);
		opCount--;
		listChange = true;
	}
	
	public void handleMulDiv(OpPair md){
		if(md.operation.equals("*")){
			expVals.set(md.index1, (expVals.get(md.index1)*expVals.get(md.index2)));
		}
		if(md.operation.equals("/")){
			expVals.set(md.index1, (expVals.get(md.index1)/expVals.get(md.index2)));
		}
		expVals.remove(md.index2);
		for(OpPair md2 : mulDiv){
			if(md2.index1 >= md.index2){
				mulDiv.get(mulDiv.indexOf(md2)).index1--;
				mulDiv.get(mulDiv.indexOf(md2)).index2--;
			}
		}
		for(OpPair as : addSub){
			if(as.index1 >= md.index2){
				addSub.get(addSub.indexOf(as)).index1--;
				addSub.get(addSub.indexOf(as)).index2--;
			}
		}
		for(OpPair e : exp){
			if(e.index1 >= md.index2){
				exp.get(exp.indexOf(e)).index1--;
				exp.get(exp.indexOf(e)).index2--;
			}
		}
		mulDiv.remove(md);
		opCount--;
		listChange = true;
	}
	
	public void handleAddSub(OpPair as){
		if(as.operation.equals("+")){
			expVals.set(as.index1, (expVals.get(as.index1)+expVals.get(as.index2)));
		}
		if(as.operation.equals("-")){
			expVals.set(as.index1, (expVals.get(as.index1)-expVals.get(as.index2)));
		}
		expVals.remove(as.index2);
		for(OpPair md : mulDiv){
			if(md.index1 >= as.index2){
				mulDiv.get(mulDiv.indexOf(md)).index1--;
				mulDiv.get(mulDiv.indexOf(md)).index2--;
			}
		}
		for(OpPair as2 : addSub){
			if(as2.index1 >= as.index2){
				addSub.get(addSub.indexOf(as2)).index1--;
				addSub.get(addSub.indexOf(as2)).index2--;
			}
		}
		for(OpPair e : exp){
			if(e.index1 >= as.index2){
				exp.get(exp.indexOf(e)).index1--;
				exp.get(exp.indexOf(e)).index2--;
			}
		}
		addSub.remove(as);
		opCount--;
		listChange = true;
	}
	
	public void performOperations(){
		while(opCount != 0){
			listChange = false;
			if(!group.isEmpty()){
				for(OpPair g : group){
					for(OpPair e : exp){
						if(g.index1 <= e.index1 && g.index2 >= e.index2){
							handleExponents(e);
							if(listChange){
								g.index2--;
								break;
							}
						}
					}
					for(OpPair md : mulDiv){
						if(g.index1 <= md.index1 && g.index2 >= md.index2){
							handleMulDiv(md);
							if(listChange){
								g.index2--;
								break;
							}
						}
					}
					for(OpPair as : addSub){
						if(g.index1 <= as.index1 && g.index2 >= as.index2){
							//System.out.println(expVals.get(as.index1)+" "+as.operation+" "+expVals.get(as.index2));
							handleAddSub(as);
							if(listChange){
								g.index2--;
								break;
							}
						}
					}
					if((g.index2-g.index1) < 1){
						group.remove(g);
					}
				}
			}
			else{
				for(OpPair e : exp){
					handleExponents(e);
					if(listChange){
						break;
					}
				}
				for(OpPair md : mulDiv){
					handleMulDiv(md);
					if(listChange){
						break;
					}
				}
				for(OpPair as : addSub){
					handleAddSub(as);
					if(listChange){
						break;
					}
				}
			}
		}
	}
	
	public void inputStatement(String type){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		if(type.equals("int")){
			if(IntCheck(input)){
				expVals.add(Double.parseDouble(input));
			}
			else{
				System.out.println("Unexpected input: Expected -> Int");
			}
		}
		if(type.equals("float")){
			if(FloatCheck(input)){
				expVals.add(Double.parseDouble(input));
			}
			else{
				System.out.println("Unexpected input: Expected -> Float");
			}
		}
	}
	
	public void StoreExpression(String currentStr){
		if(FloatCheck(currentStr)){
			expVals.add(Double.parseDouble(currentStr));
		}
		if(rootSCT.getSymTab().containsKey(currentStr)){
				expVals.add(rootSCT.getSymTab().get(currentStr));
		}
		if(currentStr.equals("(")){
			if(expVals.isEmpty()){
				OpPair newOp = new OpPair(0, -1, null);
				group.add(newOp);
			}
			else{
				OpPair newOp = new OpPair(expVals.size(), 0, null);
				group.add(newOp);
			}
		}
		if(currentStr.equals("+") || currentStr.equals("-")){
			OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
			addSub.add(newOp);
			opCount++;
		}
		if(currentStr.equals("*") || currentStr.equals("/")){
			OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
			mulDiv.add(newOp);
			opCount++;
		}
		if(currentStr.equals("^")){
			OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
			exp.add(newOp);
			opCount++;
		}
		if(currentStr.equals(")") && !group.isEmpty() && group.getLast().index2 == -1){
			group.getLast().index2 = expVals.size()-1;
			/*for(OpPair y : group){
				System.out.println("index1: "+y.index1+" index2: "+y.index2);
			}*/
		}
	}
	
	public void RunProgram(File fileName) throws Exception{
		Scanner scLines = new Scanner(fileName);						//Scanner to read string values line by line
		String currentStr, collectStr = "", printStr = "";				//String used to reference the most recently scanned string from source code text
		while(scLines.hasNextLine() && !errorFlag){
			String lineRead = scLines.nextLine();
			Scanner sc = new Scanner(lineRead);
			isNotComment = true;
			while(sc.hasNext() && !errorFlag){
				currentStr = sc.next();
				if(currentStr.equals("\"")){
					quoteCount++;
				}
				if(!currentStr.equals("//") && isNotComment){
					if(quoteCount % 2 == 0){
						if(!llp.nodes.terminals.containsKey(currentStr) && !isAssignment && !insidePrint){
							curVar = currentStr;
							rootSCT.getSymTab().put(currentStr, 0.0);
						}
						if(currentStr.equals("input")){
							isInput = true;
							isAssignment = false;
						}
						if(isInput && (currentStr.equals("int") || currentStr.equals("float"))){
							inputStatement(currentStr);
						}
						if(isAssignment){
							StoreExpression(currentStr);
						}
						if(insidePrint){
							StoreExpression(currentStr);
						}
						if(insidePrint && (currentStr.equals(",")||currentStr.equals(";")) && !expVals.isEmpty()){
							/*for(Double x : expVals)
								System.out.print(x+" ");
							*/performOperations();
							if(expVals.getLast() == Math.ceil(expVals.getLast())){
								String intValue = ""+expVals.getLast();
								intValue = intValue.substring(0, intValue.length() - 2);
								System.out.print(" "+intValue);
							}
							else{
								System.out.print(" "+expVals.getLast());
							}
							expVals.clear();
						}
						if(isPrint && currentStr.equals("(")){						
							insidePrint = true;
							isPrint = false;
						}
						if(currentStr.equals("=")){
							isAssignment = true;
						}
						if(currentStr.equals("print")){
							isPrint = true;
						}
						if(currentStr.equals(";")){
							if(isAssignment || isInput){
								performOperations();
								if(rootSCT.getSymTab().containsKey(curVar)){
									rootSCT.getSymTab().replace(curVar, expVals.getLast());
									expVals.clear();
								    //System.out.println(curVar+" = "+rootSCT.getSymTab().get(curVar));
									curVar = null;
								}
							}
							isAssignment = false;
							isPrint = false;
							isInput = false;
							if(insidePrint){
								System.out.println();
							}
							insidePrint = false;
						}
					}
					else{
						if(insidePrint && !currentStr.equals("\"")){
							System.out.print(" "+currentStr);
						}
					}
				}
				else{
					isNotComment = false;
				}
			}
			sc.close();
		}
		scLines.close();

		if(quoteCount % 2 != 0){
			System.out.println("ERROR: Unbalanced Quotation Marks");
		}
		if(errorFlag){
			System.out.println("ERROR");
		}
	}
}
