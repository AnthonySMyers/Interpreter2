import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {
	private Parser llp;
	private int scopeNumber = 0, curVal, opCount = 0, quoteCount = 0;
	private ScopeTreeNode rootSCT = new ScopeTreeNode();
	private boolean errorCheck = false, insideParens = false, firstParens = false, alreadyInput = false, listChange = false, isAssignment = false, isPrint = false, isInput = false, errorFlag = false, isNotComment = true;
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
							if(listChange){
								break;
							}
							handleExponents(e);
						}
					}
					for(OpPair md : mulDiv){
						if(g.index1 <= md.index1 && g.index2 >= md.index2){
							if(listChange){
								break;
							}
							handleMulDiv(md);
						}
					}
					for(OpPair as : addSub){
						if(g.index1 <= as.index1 && g.index2 >= as.index2){
							if(listChange){
								break;
							}
							handleAddSub(as);
						}
					}
					group.remove(g);
				}			
			}
			else{
				for(OpPair e : exp){
					if(listChange){
						break;
					}
					handleExponents(e);
				}
				for(OpPair md : mulDiv){
					if(listChange){
						break;
					}
					handleMulDiv(md);
				}
				for(OpPair as : addSub){
					if(listChange){
						break;
					}
					handleAddSub(as);
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
	
	public void RunProgram(File fileName) throws Exception{
		Scanner scLines = new Scanner(fileName);						//Scanner to read string values line by line
		String currentStr, collectStr = "", printStr = "";								//String used to reference the most recently scanned string from source code text
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
						if(collectStr.equals("")){
							if(!llp.nodes.terminals.containsKey(currentStr) && !isAssignment){
								Map<String, Double> newVar = new HashMap<String, Double>();
								newVar.put(currentStr, 0.0);
								curVar = currentStr;
								rootSCT.getSymTab().add(newVar);
							}
							if(currentStr.equals("input")){
								isInput = true;
								isAssignment = false;
							}
							if(isAssignment){
								if(FloatCheck(currentStr)){
									expVals.add(Double.parseDouble(currentStr));
								}
								for(Map<String, Double> var : rootSCT.getSymTab()){
									if(var.containsKey(currentStr)){
										expVals.add(var.get(currentStr));
									}
								}
								if(currentStr.equals("(")){
									if(expVals.isEmpty()){
										OpPair newOp = new OpPair(0, 0, null);
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
								if(currentStr.equals(")")){
									group.getLast().index2 = expVals.size()-1;
								}
							}
							if(isPrint && firstParens){
								if(FloatCheck(currentStr)){
									expVals.add(Double.parseDouble(currentStr));
								}
								for(Map<String, Double> var : rootSCT.getSymTab()){
									if(var.containsKey(currentStr)){
										expVals.add(var.get(currentStr));
									}
								}
								if(currentStr.equals("(")){
									if(expVals.isEmpty()){
										OpPair newOp = new OpPair(0, 0, null);
										group.add(newOp);
										insideParens = true;
									}
									else{
										OpPair newOp = new OpPair(expVals.size(), 0, null);
										group.add(newOp);
										insideParens = true;
									}
								}
								if(currentStr.equals("+") || currentStr.equals("-")){
									System.out.println("add check");
									OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
									addSub.add(newOp);
									opCount++;
								}
								if(currentStr.equals("*") || currentStr.equals("/")){
									System.out.println("mul check");
									OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
									mulDiv.add(newOp);
									opCount++;
								}
								if(currentStr.equals("^")){
									OpPair newOp = new OpPair(expVals.size()-1, expVals.size(), currentStr);
									exp.add(newOp);
									opCount++;
								}
								if(currentStr.equals(")") && insideParens){
									System.out.println("group check");
									group.getLast().index2 = expVals.size()-1;
									insideParens = false;
								}
								if((currentStr.equals(",")||currentStr.equals(";")) && !expVals.isEmpty()){
									performOperations();
									//System.out.println(expVals.getLast());
								    printStr += expVals.getLast();
									expVals.clear();
								}
							}
							if(isPrint && currentStr.equals("(") && !firstParens){
								firstParens = true;
							}
							if(isInput && (currentStr.equals("int")||currentStr.equals("float"))){
								inputStatement(currentStr);
								isInput = false;
								alreadyInput = true;
							}
							if(currentStr.equals("=")){
								isAssignment = true;
							}
							if(currentStr.equals("print")){
								isPrint = true;
							}
							if(currentStr.equals(";")){
								if(isAssignment || alreadyInput){
									performOperations();
									for(Map<String, Double> var : rootSCT.getSymTab()){
										if(var.containsKey(curVar)){
											var.replace(curVar, expVals.getLast());
											//System.out.println(expVals.getLast());
											if(firstParens){
												expVals.clear();
											}
											//System.out.println(curVar+" = "+var.get(curVar));
										}
									}
								}
								if(isPrint){
									printStr = printStr.replaceAll("\"", "");
									System.out.println(printStr);
									printStr = "";
								}
								isAssignment = false;
								firstParens = false;
								isPrint = false;
								isInput = false;
								alreadyInput = false;
							}
							
						}
						else{
							collectStr += " "+currentStr;
							printStr += " "+collectStr;
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

		if(quoteCount % 2 != 0){
			System.out.println("ERROR: Unbalanced Quotation Marks");
		}
		if(errorFlag){
			System.out.println("ERROR");
		}
	}
}
