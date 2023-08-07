package Hash;

import java.util.ArrayList;

public class HashNode {
	String binIndex;
	ArrayList<Node> bucket;
	Node next;
	
	
	HashNode(int decIndex,int globalD) {
		next = null;
		bucket = new ArrayList<Node>();
		binIndex = Integer.toBinaryString(decIndex);
		String format = "%" + Integer.toString(globalD) + "s";
		binIndex = String.format(format, binIndex).replaceAll(" ", "0");
	}
	
	
	
	
	
}
