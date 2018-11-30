package interpreter;

public class symbolTableEntry {
	
	private String identifier;
	private String type;
	private String value;
	
	symbolTableEntry(String id, String type, String val){
		this.identifier = id;
		this.type = type;
		this.value = val;
	}
	
	public String getID() {
		return this.identifier;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getValue() {
		return this.value;
	}
}
