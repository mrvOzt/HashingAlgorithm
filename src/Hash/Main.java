package Hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		int count = 0;
		Scanner sc = new Scanner(new File("file.txt"),"ISO-8859-1");
		HashTable ht = new HashTable(8);
		while(sc.hasNext()) { 
			String word = sc.next();
			word = word.strip();
			Node newNode = new Node(word,HashTable.globalDep);
			HashTable.insert(newNode, HashTable.globalDep);
		}
		Node search = HashTable.search("this" , HashTable.globalDep);
		System.out.println("-------Hashing Algorithm-------");
		System.out.println("Search :" + search.value);
		System.out.println("Key :" + search.key);
		System.out.println("Count :" + search.count);
		System.out.println("Index :" + search.index);
		System.out.println("Global Depth :" + HashTable.globalDep);
		System.out.println("Local Depth : " + search.localDep);
	
	}

}
