
public class InvertedIndex {
	public LinkedList<Words> invIndex;

	public InvertedIndex() {
		this.invIndex = new LinkedList<Words>();
	}

	public void add(String word, int id) {
		if (!duplicateWord(word)) {
			Words temp = new Words(word);
			invIndex.insert(temp);
			temp.rowID.insert(id);
		} else
			invIndex.retrieve().addID(id);

	}
	

	public boolean duplicateWord(String word) {
		if (invIndex == null || invIndex.empty())
			return false;

		invIndex.findfirst();
		while (!invIndex.last()) {
			if (invIndex.retrieve().word.equals(word))
				return true;
			invIndex.findNext();
		}
		if (invIndex.retrieve().word.equals(word))
			return true;
		else
			return false;
	}

	public void displayInvIndex() {
		if (invIndex == null || invIndex.empty())
			return;
		System.out.println("Inverted Index:");
		invIndex.findfirst();
		while (!invIndex.last()) {
			System.out.println("Word: " + invIndex.retrieve().word);
			System.out.print("[ ");
			invIndex.retrieve().rowID.display();
			System.out.println("]");
			System.out.println("=============================");
			invIndex.findNext();
		}
		System.out.println("Word: " + invIndex.retrieve().word);
		System.out.print("[ ");
		invIndex.retrieve().rowID.display();
		System.out.println("]");
		System.out.println("=============================");
	}
}
