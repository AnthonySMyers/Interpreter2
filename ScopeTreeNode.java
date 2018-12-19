import java.util.HashMap;
import java.util.Map;

/**
 * Class definition for ScopeTreeNode data type
 * Used to create nodes interpretation. ScopeTreeNodes are created during a treewalk of the AST.
 * Symbol tables for each scope tree node are built during treewalk
 * @author Minhkhoa Vu	minhkhoavu954@gmail.com
 * @author Anthony Myers
 * @author Juan Espinoza
 * @author Miguel Castorena
 */

public class ScopeTreeNode {
	private Map<String, Double> symTab = new HashMap<String, Double>();		//Symbol Table that holds all symTab entries
	private ScopeTreeNode child, mom;
	
	/**
	* Constructor for emtpy scope tree node
	*/
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
