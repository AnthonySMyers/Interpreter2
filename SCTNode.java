package interpreter;

import java.util.LinkedList;

public class SCTNode {
	private LinkedList<SCTNode> collection = new LinkedList<SCTNode>();
	private SCTNode momNode;
	public int currentChild = 0, totalChildren = 0, height;
	public LinkedList<symbolTableEntry> symbolTable = new LinkedList<symbolTableEntry>();
	
	/**
	* SCTNode Constructor
	*/
	public SCTNode() {
		
	}
	
	
	public void addChild(SCTNode child){
		collection.add(child);
	}
	
	public LinkedList<SCTNode> getChildren(){
		return collection;
	}
	
	public SCTNode getMom(){
		return momNode;
	}
	
	public void setMom(SCTNode newMom){
		momNode = newMom;
	}
	
	
	/**
	* Method adds entry into the sym table
	*/
	public void addSymTabEntry(symbolTableEntry newEntry) {
		symbolTable.add(newEntry);
	}
}
