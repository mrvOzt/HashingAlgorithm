package Hash;

import java.util.ArrayList;

public class HashTable {
	public static int globalDep;
	public static ArrayList<HashNode> hashTable;
	public static int size;
	public static ArrayList<Node> inserted = new ArrayList<Node>();
	public static ArrayList<Node> tempBucket;
	
	HashTable(int globalDep) {
		this.globalDep = globalDep;
		size= (int) Math.pow(2, globalDep);
		hashTable = new ArrayList<HashNode>(size);
		for (int i = 0; i < size; i++) {
			HashNode binNode = new HashNode(i,globalDep);
			hashTable.add(binNode);
			
		}
	}
	public static void insert(Node selected,int globalD){
			Node control = search(selected.value,globalD);
			if(control != null) {
				control.count++;
			}
			else {
				selected.localIndex = selected.index.substring(selected.index.length() - globalD);
				String searchIndex = selected.localIndex;
				int decIndex = Integer.parseInt(searchIndex, 2);
				HashNode binNode = hashTable.get(decIndex);
				if(binNode.bucket.size() == 10) {
					if(globalD==binNode.next.localDep) {
						ArrayList<HashNode> temp = hashTable;
						globalD++;
						HashTable ht = new HashTable(globalD);
						for(int i = 0;i<binNode.bucket.size();i++) {
							binNode.bucket.get(i).localDep = globalD;
							insert(binNode.bucket.get(i),globalD);
						}
						control(temp);
					}
					else {
						tempBucket = binNode.bucket;
						String localIndex = binNode.next.localIndex;
						int arrow = (int) Math.pow(2, globalD - binNode.next.localDep);
						for (int i = 0; i < arrow; i++) {
							String tempIndex = Integer.toBinaryString(i);
							String nIndex = tempIndex + localIndex;
							int decInd = Integer.parseInt(nIndex, 2);
							hashTable.get(decIndex).next = null;
							hashTable.get(decIndex).bucket.clear();
						}
						for(int i = 0;i < tempBucket.size();i++) {
							tempBucket.get(i).localDep = globalD;
							insert(tempBucket.get(i),globalD);
						}	
					}
				}
				else {
					if(binNode.next != null) {
						selected.next= binNode.next;
					}
					binNode.next = selected;
					binNode.bucket.add(selected);		
				}
			}
	}
	public static Node search(String value,int globalD) {
		Node search = null;
		String binIndex = Node.index(value);
		binIndex = binIndex.substring(binIndex.length() - globalD);
		int decIndex=Integer.parseInt(binIndex,2);  
		Node arrNode = hashTable.get(decIndex).next;
		while(arrNode != null) {
			if(arrNode.value.equals(value)) {
				search = arrNode;
				break;
			}
			else {
				arrNode = arrNode.next;
			}
		}
		return search;
	}
	public static void control(ArrayList<HashNode> hash) {
		for(int i = 0;i <hash.size();i++) {
			HashNode controlNode = hash.get(i);
			if(controlNode.next != null) {
				if(globalDep != controlNode.next.localDep) {
					int arrow = globalDep - controlNode.next.localDep;
					for(int x = 0; x < 2;x++) {
						String bin = String.valueOf(x) + controlNode.binIndex;
						int decimal =Integer.parseInt(bin,2);
						hashTable.get(decimal).next = controlNode.next;
						hashTable.get(decimal).bucket = controlNode.bucket;
					
						
					}
				}	
			}
		}
	}
	
}