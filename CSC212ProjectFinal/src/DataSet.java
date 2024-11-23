
public class DataSet {
	public LinkedList<Rows> rows;

	public DataSet() {
		rows = new LinkedList<Rows>();

	}

	public void newRow(Rows r) {
		rows.insert(r);
	}

	public Rows getRow(int id) {
		if (rows.empty()) {
			System.out.println("No Documents");
			return null;
		}
		rows.findfirst();
		while (!rows.last()) {
			if (rows.retrieve().id == id)
				return rows.retrieve();
			rows.findNext();
		}
		if (rows.retrieve().id == id) // to check last id
			return rows.retrieve();
		return null;
	}

	public void displayAll() {
		if (rows.empty()) {
			System.out.println("no rows added");
			return;
		}

		rows.findfirst();

		while (!rows.last()) {
			Rows temp = rows.retrieve();
			System.out.println("================================");
			System.out.println("Id = " + temp.id);
			temp.text.display();
			System.out.println("");

			rows.findNext();
		}
		Rows temp = rows.retrieve();
		System.out.println("================================");
		System.out.println("Id = " + temp.id);
		temp.text.display();
		System.out.println("\n================================");

	}
}
