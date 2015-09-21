public interface PersonInterface {
	String getName();
	Person getPartner();
	void addToRank(Person p);
	int getRank(Person p);
	String toString();
	void addPartner(Person p);
}