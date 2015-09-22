
import java.io.*;
import java.util.LinkedList;
import java.util.stream.*;


public class StableMatching {
	private int size;

	public static void main(String[] args) {
		if(args.length < 1){
			System.out.println("Number of couples required [0 - 10]");
			return;
		}

		StableMatching s = new StableMatching();
 		s.start(Integer.parseInt(args[0]));
 	}

 	private void start(int size){
 		this.size = size;
		String names[] = new String[size * 2];
		LinkedList<Man> men = new LinkedList<Man>();
		LinkedList<Woman> women = new LinkedList<Woman>();

		GenerateCouples g = new GenerateCouples(size);
		g.createRanks(men, women);

		printRanks(women);
		printRanks(men);

		select(men, women);

		printPartners(men);
 	}

 	private void select(LinkedList<Man> men, LinkedList<Woman> women){

 		while(!allMatched(men)){
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

 	private void printRanks(LinkedList<? extends Person> p){
 		p.stream()
 			.forEach(System.out::println);
 	}
}