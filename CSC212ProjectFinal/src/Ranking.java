
public class Ranking { // Ranking Query
	public static DataSet data;
	public static InvertedIndexBST invIndexBst;
	public static String query;
	public static LinkedList<DocumentRank> rankedDocs;
	public static LinkedList<Integer> docsFound;
	
	public Ranking(DataSet data, InvertedIndexBST invIndexBst, String query) { // Constructor
		this.data = data;
		this.invIndexBst = invIndexBst;
		this.query = query;
		rankedDocs = new LinkedList<DocumentRank>();
		docsFound = new LinkedList<Integer>();

	}
	public Rows getRow(int id) { //Row is the Document and data is all Documents
		return data.getRow(id);
	}
	
	public boolean existsInList(LinkedList<Integer> list, Integer id) { // Checks if the id is in the linked list																																									
		if (list.empty())
			return false;
		list.findfirst();
		while (!list.last()) {
			if (list.retrieve().equals(id))
				return true;
			list.findNext();
		}
		if (list.retrieve().equals(id))
			return true;
		return false;

	}
	
	public int calcWordFreq(Rows r,String word) { // Counts how many times a word repeats in one row
		int freq=0;
		LinkedList<String> words = r.text;
		words.findfirst();
		while(!words.last()) {
			if(words.retrieve().equalsIgnoreCase(word))
				freq++;
			words.findNext();
		}if(words.retrieve().equalsIgnoreCase(word)) //to check last word
			freq++;
		return freq;
	}
	
	public int calcRank(Rows r, String query) { // Counts term frequency of all words in query
		if(query.length()==0)
			return 0;
		String words[] = query.split(" ");
		int rank = 0;
		for(int i=0;i<words.length;i++)
			rank+=calcWordFreq(r,words[i].trim());
		return rank;
	}
	
	public void Rank(String query) { // First method of this class
		LinkedList<Integer> A = new LinkedList<Integer>();
		if(query.length()==0)
			return;
		String words[]= query.split(" ");
		boolean found = false;
		for(int i=0;i<words.length;i++) {
			found=invIndexBst.duplicateWord(words[i].trim().toLowerCase());
			if(found)
				A=invIndexBst.tree.retrieve().rowID;
			AddInList(A);
		}

	}
	
	public void insertSortedIds(Integer id) { 
		if(docsFound.empty()) {
			docsFound.insert(id);
			return;
		}
		docsFound.findfirst();
		while(!docsFound.last()) {
			if(id<docsFound.retrieve()) {
				Integer tmp = docsFound.retrieve();
				docsFound.Update(id);
				docsFound.insert(tmp);
				return;
			} else
				docsFound.findNext();
		}
		if(id<docsFound.retrieve()) {
			Integer tmp = docsFound.retrieve();
			docsFound.Update(id);
			docsFound.insert(tmp);
			return;
		} else
			docsFound.insert(id);
	}
	public void AddInList(LinkedList<Integer> list) {
		if(list.empty())
			return;
		list.findfirst();
		while(!list.empty()) {
			boolean found = existsInList(docsFound,list.retrieve());
			if(!found)
				insertSortedIds(list.retrieve());
		if(!list.last())
			list.findNext();
		else
			break;
	    }
	}
	
	public void insertAndSort(DocumentRank d) {
		if(rankedDocs.empty()) {
			rankedDocs.insert(d);
			return;
		}
		rankedDocs.findfirst();
		while(!rankedDocs.last()) {
			if(d.rank>rankedDocs.retrieve().rank) {
				DocumentRank tmp = rankedDocs.retrieve();
				rankedDocs.Update(d);
				rankedDocs.insert(tmp);
				return;
			} else
				rankedDocs.findNext();
		}
		if(d.rank>rankedDocs.retrieve().rank) {
			DocumentRank tmp = rankedDocs.retrieve();
			rankedDocs.Update(d);
			rankedDocs.insert(tmp);
			return;
		} else
			rankedDocs.insert(d);
	}
	
	public void insertRankedDocs() { 
		Rank(query);
		if(docsFound.empty())
			System.out.println("empty query");
		docsFound.findfirst();
		while(!docsFound.last()) {
			Rows r = getRow(docsFound.retrieve());
			int rank = calcRank(r,query);
			insertAndSort(new DocumentRank(docsFound.retrieve(),rank));
			docsFound.findNext();
		}
		Rows r = getRow(docsFound.retrieve());
		int rank = calcRank(r,query);
		insertAndSort(new DocumentRank(docsFound.retrieve(),rank));
		
	}
	public void RankedDisplay() { // print method
		if(rankedDocs.empty()) {
			System.out.println("empty");
			return;
		}
		System.out.println("DocID    Score");
		rankedDocs.findfirst();
		while(!rankedDocs.last()) {
			rankedDocs.retrieve().display();
			rankedDocs.findNext();
		}
		rankedDocs.retrieve().display();
	}
	

}
