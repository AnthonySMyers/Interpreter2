import java.util.HashMap;
import java.util.Map;

public class ScopeTreeNode {
	private Map<String, Double> symTab = new HashMap<String, Double>();
	private ScopeTreeNode child, mom;
	
	public ScopeTreeNode(){
		child = null;
		mom = null;
	}
	
	public Map<String, Double> getSymTab(){
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
