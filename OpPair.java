/**
 * stores the math operations to be used to calculate a variable later in program
 * @author Anthony Myers
 *
 */
public class OpPair {
	public int index1 = -1, index2 = -1;
	public String operation;
	
	/**
	 * constructor to do math statement
	 * @param first - index of first number
	 * @param second - index of second number
	 * @param op - math operation
	 */
	public OpPair(int first, int second, String op){
		index1 = first;
		index2 = second;
		operation = op;
	}

}
