import java.util.LinkedList;
import java.util.Map;

public class ScopeTreeNode {
	private LinkedList<Map<String, Double>> symTab = new LinkedList<Map<String, Double>>();
	private ScopeTreeNode child, mom;
	
	public ScopeTreeNode(){
		child = null;
		mom = null;
	}
	
	public LinkedList<Map<String, Double>> getSymTab(){
		return symTab;
	}
	
	public void setChild(ScopeTreeNode newChild){
		child = newChild;
	}
	
	public void setMom(ScopeTreeNode newMom){
		mom = newMom;
	}
	
	public ScopeTreeNode getChild(){
		return child;
	}
	
	public ScopeTreeNode getMom(){
		return mom;
	}

}
