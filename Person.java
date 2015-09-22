import java.io.*;
import java.util.LinkedList;
import java.util.stream.*;

public class Person implements PersonInterface{
	private String name;
	private LinkedList<Person> rank;
	private Person partner;

	public Person(String n){
		name = n;
		rank = new LinkedList<Person>();
		partner = null;
	}

	public String getName(){
		return this.name;
	}

	public Person getPartner(){
		return this.partner;
	}

	public void addToRank(Person p){
		rank.add(p);
	}

	public LinkedList<Person> getList(){
		return rank;
	}

	public int getRank(Person p){
		return rank.indexOf(p); 
	}

	public String toString(){
		String str = "";

		for(Person p : rank)
			str += p.getName() + " ";

		return name + " " + str;
	}

	public void addPartner(Person p){
		this.partner = p;
	}
}