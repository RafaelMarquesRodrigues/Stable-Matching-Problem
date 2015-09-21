public class Man extends Person {
	private int proposal;
	
	public Man(String n){
		super(n);
		proposal = 0;
	}

	public int getProposals(){
		return this.proposal;
	}

	public void addProposal(){
		this.proposal++;
	}
}