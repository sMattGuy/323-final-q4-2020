import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;
import java.io.PrintStream;

public class FlammiaM_Q39_Java{
	public static void main(String args[])throws Exception{
		File data = new File(args[0]);
		HashTable hash = new HashTable();
		Scanner reader = new Scanner(data);
		PrintStream fileOut = new PrintStream(new FileOutputStream(args[2],true));
		System.setOut(fileOut);
		while(reader.hasNext()){
			String word = reader.next();
			hash.insert(word);
		}
		PrintStream finalOut = new PrintStream(new FileOutputStream(args[1],true));
		System.setOut(finalOut);
		hash.printTable();
	}
}
class LLNode{
	int count;
	LLNode next;
	String data;
	LLNode(){
		this.count = 1;
		this.data = "";
	}
	LLNode(String data){
		this.count = 1;
		this.data = data;
	}
	void printData(){
		if(this.count > 1){
			System.out.print(this.data + "(" + this.count + ")->");
		}
		else{
			System.out.print(this.data + "->");
		}
	}
}
class LinkedList{
	LLNode head;
	LinkedList(){
		this.head = new LLNode("dummy");
	}
	void insert(String data){
		LLNode newNode = new LLNode(data);
		LLNode reader = this.head;
		if(reader.next == null){
			reader.next = newNode;
		}
		else{
			while(reader.next!=null){
				if(reader.data.equals(data)){
					reader.count++;
					return;
				}
				reader = reader.next;
			}
			if(reader.data.equals(data)){
				reader.count++;
			}
			else{
				reader.next = newNode;
			}
		}
		System.out.println("Printing list after insert");
		this.printList();
	}
	void printList(){
		LLNode reader = this.head;
		while(reader.next != null){
			reader.printData();
			reader = reader.next;
		}
		System.out.println("NULL");
	}
}
class HashTable{
	LinkedList[] hashTable;
	HashTable(){
		this.hashTable = new LinkedList[29];
		for(int i=0;i<29;i++){
			this.hashTable[i] = new LinkedList();
		}
	}
	void insert(String data){
		int index = this.hashFunction(data);
		this.hashTable[index].insert(data);
	}
	int hashFunction(String data){
		int val = 1;
		for(int i=0;i<data.length();i++){
			val = val*32 + (int)data.charAt(i);
		}
		if(val < 0){
			val = val * -1;
		}
		return val%29;
	}
	void printTable(){
		System.out.println("Printing Table");
		for(int i=0;i<29;i++){
			System.out.print("Index " + i + ":");
			this.hashTable[i].printList();
		}
	}
}