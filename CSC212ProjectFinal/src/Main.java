import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		FileDataEntry prog = new FileDataEntry();
		prog.loadFiles("stop.txt", "dataset.csv");
		Scanner input = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("=============================");
			System.out.println("Welcome to the Search Engine");
			System.out.println(" 1) All Indexed Documents");
			System.out.println(" 2) All Indexed Tokens(ordered by ID)");
			System.out.println(" 3) All Indexed Tokens(ordered alphabetically");
			System.out.println(" 4) Boolean Retrieval");
			System.out.println(" 5) Ranking Retrieval");
			System.out.println(" 0) Exit");
			System.out.println("=============================");
			option = input.nextInt();
			switch (option) {
			case 1:
				prog.data.displayAll();
				break;
			case 2:
				prog.invData.displayInvIndex();
				break;
			case 3:
				prog.bstData.displayInvBST();
				break;
			case 4:
				System.out.println("Enter an AND_OR query: ");
				input.nextLine();
				Querying q3 = new Querying(prog.bstData);
				String AND_ORQuery = input.nextLine();
				LinkedList l3 = q3.AND_OR(AND_ORQuery);
				printAllContent(l3, prog);
				break;
			case 5:
				System.out.println("Enter a ranking query: ");
				input.nextLine();
				String rnkQuery = input.nextLine();
				Ranking r1 = new Ranking(prog.data, prog.bstData, rnkQuery);
				r1.insertRankedDocs();
				r1.RankedDisplay();
				break;
			case 0:
				System.out.println("Have a nice day :)");
				System.exit(0);
			default:
				System.out.println("Wrong entry!");
				break;
			}

		} while (option != 0);

	}

	private static void printAllContent(LinkedList<Integer> i, FileDataEntry prog) { // to get all Documents without
																						// removing the stop words

		if (i.empty()) {
			System.out.println("empty list");
			return;
		}

		prog.contentList.findfirst();
		i.findfirst();

		while (!i.last()) {
			while (!prog.contentList.last()) {

				if (prog.contentList.retrieve().id == i.retrieve()) {
					prog.contentList.retrieve().printContent();
					break;
				}
				prog.contentList.findNext();
			}
			prog.contentList.findfirst();
			i.findNext();
		}

		while (!prog.contentList.last()) { // to check last list id with all doc IDs

			if (prog.contentList.retrieve().id == i.retrieve())
				prog.contentList.retrieve().printContent();

			prog.contentList.findNext();
		}

		if (prog.contentList.retrieve().id == i.retrieve()) // to check last list id with last doc ID
			prog.contentList.retrieve().printContent();
		return;
	}

}
