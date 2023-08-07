package Hash;

public class Node {
	long key;
	String value;
	int count;
	String index;
	String localIndex;
	Node next;
	int localDep;
	
	Node(String value,int globalD){
		key = hashCode(value);
		this.value = value;
		this.count = 1;
		next = null;
		localDep = globalD;
		index = index(value);
		localIndex = index.substring(index.length() - globalD);
	} 
	public static String index(String value) {
	    int hashCode = (int)hashCode(value);
		String index = Integer.toBinaryString(hashCode);	
		String format = "%32s";
		index = String.format(format, index).replaceAll(" ", "0");
		return index;
	}
	//s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]  ---- >  hashcode üretme formülü
		public static long hashCode(String value) {
			long hashcode = 0;
			int len = value.length();
			for(int i = 0; i<len; i++) {
				double a =Math.pow(31, len - (i + 1));
				double c = value.codePointAt(i);
				long multp  = (long) (c*a);
				hashcode = hashcode + multp; 
			}
			return hashcode;
			
		}

	

}
