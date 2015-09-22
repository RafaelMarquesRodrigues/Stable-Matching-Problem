
import java.io.*;
import java.util.Random;
import java.util.LinkedList;

public class GenerateCouples {
	private String womenNames[] = {"Julia", "Maria", "Joana", "Beatriz", "Sofia", "Laura", "Ana", "Isabela", "Camila", "Amanda"};
	private String menNames[] = {"Rafael", "Pedro", "Gustavo", "Lucas", "Guilherme", "Arthur", "Matheus", "Gabriel", "Bernardo", "Miguel"};
	private int size;


	public GenerateCouples(int size){
		this.size = size;
	}

	public void createRanks(LinkedList<Man> men, LinkedList<Woman> women){
		Random r = new Random();

 		for(int i = 0; i < size; i++){
 			men.add(new Man(menNames[i]));
 			women.add(new Woman(womenNames[i]));
 		}

 		initializeRanks(men, women);
 		initializeRanks(women, men);
	}

	private void initializeRanks(LinkedList<? extends Person> person, LinkedList<? extends Person> other){
 		for(Person p : person){
 			for(int i = 0; i < size; i++){
	 			Random r = new Random();

	 			int aux = r.nextInt(size);

	 			while(other.get(aux).getName().equals(p.getName()) || p.getList().contains(other.get(aux).getName()))
	 				aux = r.nextInt(size);

	 			p.addToRank(other.get(aux));
 			}
 		}
 	}
}