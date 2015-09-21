
import java.io.*;
import java.util.LinkedList;
import java.util.stream.*;


public class StableMatching {
	private BufferedReader br;
	private int size;

	public static void main(String[] args) {
		StableMatching s = new StableMatching();
 		s.start();
 	}

 	private void start(){
 		try{
 			br = new BufferedReader(new FileReader("ranks.in"));
 			size = Integer.parseInt(br.readLine());
 			String names[] = new String[size * 2];
 			LinkedList<Man> men = new LinkedList<Man>();
 			LinkedList<Woman> women = new LinkedList<Woman>();

 			for(int i = 0; i < size * 2; i++)
 				names[i] = br.readLine();
 			
 			for(int i = 0; i < size; i++){
 				men.add(new Man(names[i]));
 				women.add(new Woman(names[i + size]));
 			}

 			initializeRanks(men, women);
 			initializeRanks(women, men);

 			printRanks(women);
 			printRanks(men);

 			select(men, women);

 			printPartners(men);
 		}
 		catch(IOException e){
 			System.out.println("error reading file");
 		}
 	}

 	private void select(LinkedList<Man> men, LinkedList<Woman> women){

 		while(notAllMenProposedToAllWomen(men) && !allMatched(men)){
 			Man m = getMan(men);

 			for(Woman w : women){

 				m.addProposal();

 				//if w is free, w accepts the proposal
	 			if(w.isFree()){
	 				w.addPartner(m);
	 				m.addPartner(w);
	 				w.setFree(false);
	 				break;
	 			}
	 			//if w prefers this man to her partner, she swaps
	 			else if(w.getRank(w.getPartner()) > w.getRank(m)){
	 				w.getPartner().addPartner(null);
	 				w.addPartner(m);
	 				m.addPartner(w);
	 				w.setFree(false);
	 				break;
	 			}
 			}
 		}
 	}

 	private boolean allMatched(LinkedList<Man> men){
 		for(Man m : men){
 			if(m.getPartner() == null){
 				return false;
 			}
 		}

 		return true;
 	}

 	private boolean notAllMenProposedToAllWomen(LinkedList<Man> men){
 		for(Man m : men){
 			if(m.getProposals() != size)
 				return true;
 		}

 		return false;
 	}

 	private Man getMan(LinkedList<Man> men){
 		for(Man m : men){
 			System.out.println(men.size() + " " + m.getName());
 			if(m.getPartner() == null)
 				return m;
 		}

 		return null;
 	}

 	public void printPartners(LinkedList<? extends Person> persons){
 		for(Person p : persons)
 			System.out.println(p.getName() + " " + p.getPartner().getName());
 	}

 	private void initializeRanks(LinkedList<? extends Person> person, LinkedList<? extends Person> other) throws IOException {
 		for(Person p : person){
 			String str = br.readLine();
 			String parts[] = str.split(" ");

 			for(int i = 1; i <= size; i++)
 				findPerson(person, parts[0]).addToRank(findPerson(other, parts[i]));
 		}
 	}

 	private Person findPerson(LinkedList<? extends Person> person, String name){
 		return person.stream()
 			.filter(p -> p.getName().equals(name))
 			.findFirst()
 			.orElse(null);
 	}

 	private void printRanks(LinkedList<? extends Person> p){
 		p.stream()
 			.forEach(System.out::println);
 	}
}