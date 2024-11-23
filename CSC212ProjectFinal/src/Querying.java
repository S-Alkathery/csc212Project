
public class Querying {
	public static InvertedIndexBST invIndex;

	public Querying(InvertedIndexBST invIndex) {
		this.invIndex = invIndex;
	}

	public LinkedList<Integer> AND(String query) {
		LinkedList<Integer> A = new LinkedList<Integer>();
		LinkedList<Integer> B = new LinkedList<Integer>();
		String queryArr[] = query.split("AND"); // make an array of the words in the query parted

		if (queryArr.length == 0)
			return A;
		boolean found = invIndex.duplicateWord(queryArr[0].trim().toLowerCase()); // trimming spaces and lower casing
		if (found)
			A = invIndex.tree.retrieve().rowID;
		for (int i = 1; i < queryArr.length; i++) {
			found = invIndex.duplicateWord(queryArr[i].trim().toLowerCase()); // trimming spaces and lower casing
			if (found)
				B = invIndex.tree.retrieve().rowID;
			A = AND(A, B);
		}
		return A;
	}

	public boolean existsInOutputList(LinkedList<Integer> outputList, Integer id) {
		if (outputList.empty())
			return false;
		outputList.findfirst();
		while (!outputList.last()) {
			if (outputList.retrieve().equals(id))
				return true;
			outputList.findNext();
		}
		if (outputList.retrieve().equals(id))
			return true;
		return false;

	}

	public LinkedList<Integer> AND(LinkedList<Integer> A, LinkedList<Integer> B) {
		LinkedList<Integer> outputList = new LinkedList<Integer>();
		if (A.empty() || B.empty())
			return outputList;
		A.findfirst();
		while (true) {
			boolean found = existsInOutputList(outputList, A.retrieve());
			if (!found) { // not already in the output list
				B.findfirst();
				while (true) {
					if (B.retrieve().equals(A.retrieve())) {
						outputList.insert(A.retrieve());
						break;
					}
					if (!B.last())
						B.findNext();
					else
						break; // here all cases were included to end the -inner- "while(true)" to avoid
								// infinite loop

				}
			} // ending if not found
			if (!A.last())
				A.findNext(); // checking the next id in A's list
			else
				break; // will break the -outer- "while(true)" after finishing A's list
		}
		return outputList;
	}

	public LinkedList<Integer> OR(String query) {
		LinkedList<Integer> A = new LinkedList<Integer>();
		LinkedList<Integer> B = new LinkedList<Integer>();
		String queryArr[] = query.split("OR"); // make an array of the words in the query parted
		/*
		 * for(String s1 : queryArr) System.out.println(s1.trim());
		 */ // testing the array splitting
		if (queryArr.length == 0)
			return A;
		boolean found = invIndex.duplicateWord(queryArr[0].trim().toLowerCase()); // trimming spaces and lower casing
		if (found)
			A = invIndex.tree.retrieve().rowID;
		for (int i = 1; i < queryArr.length; i++) {
			found = invIndex.duplicateWord(queryArr[i].trim().toLowerCase()); // trimming spaces and lower casing
			if (found)
				B = invIndex.tree.retrieve().rowID;
			A = OR(A, B);
		}
		return A;
	}

	public LinkedList<Integer> OR(LinkedList<Integer> A, LinkedList<Integer> B) {
		LinkedList<Integer> outputList = new LinkedList<Integer>();
		if (A.empty() && B.empty())
			return outputList;
		A.findfirst();
		while (!A.empty()) { // or while(true)
			boolean found = existsInOutputList(outputList, A.retrieve());
			if (!found) { // not already in the output list
				outputList.insert(A.retrieve());
			}

			if (!A.last())
				A.findNext();
			else
				break; // to avoid infinite loop
		}
		B.findfirst();
		while (!B.empty()) { // or while(true)
			boolean found = existsInOutputList(outputList, B.retrieve());
			if (!found) { // not already in the output list
				outputList.insert(B.retrieve());
			}
			if (!B.last())
				B.findNext();
			else
				break; // to avoid infinite loop
		} // market AND sports OR warming AND market -- [ market AND sports , warming AND
			// market]

		return outputList;
	}

	public LinkedList<Integer> AND_OR(String query) {
		LinkedList<Integer> A = new LinkedList<Integer>();
		LinkedList<Integer> B = new LinkedList<Integer>();
		if (query == null || query.trim().isEmpty()) // checking empty query, won't fall in null pointer exception (||)
			return A;
		String orSplit[] = query.split("OR"); // market AND analysts OR warming AND market
		A = AND(orSplit[0].trim());
		for (int i = 1; i < orSplit.length; i++) { // loop for combining the boolean query one by one
			
			String subQuery = orSplit[i].trim();
			if (!subQuery.isEmpty()) { // ensures that the subQuery is not empty (solves previous problem)
				B = AND(subQuery);
				A = OR(A, B);
			}
		}
		return A;
	}

}
