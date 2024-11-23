
public class Words {
	public String word;
	public LinkedList<Integer> rowID;

	public Words(String word) {
		this.word = word;
		rowID = new LinkedList<Integer>();
	}

	public void addID(int id) {
		if (!duplicateID(id)) {
			rowID.insert(id);
		}
	}

	public boolean duplicateID(int id) {
		if (rowID.empty())
			return false;

		rowID.findfirst();
		while (!rowID.last()) {
			if (rowID.retrieve().equals(id))
				return true;
			rowID.findNext();
		}
		if (rowID.retrieve().equals(id))
			return true;
		else
			return false;
	}

}
