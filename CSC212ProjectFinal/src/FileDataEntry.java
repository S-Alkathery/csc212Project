import java.io.*;
import java.util.Scanner;

public class FileDataEntry {
	public LinkedList<String> stopWord;
	public static DataSet data;
	public InvertedIndex invData;
	public InvertedIndexBST bstData;
	public LinkedList<ContentList> contentList;

	public FileDataEntry() {
		stopWord = new LinkedList<String>();
		data = new DataSet();
		invData = new InvertedIndex();
		bstData = new InvertedIndexBST();
		contentList = new LinkedList<ContentList>();

	}

	public void loadFiles(String stopWordFile, String dataSetFile) {
		stopWords(stopWordFile);
		dataSet(dataSetFile);

	}

	public void stopWords(String fName) {
		try {
			File f = new File(fName);
			Scanner in = new Scanner(f);

			while (in.hasNextLine()) {
				String line = in.nextLine();
				stopWord.insert(line);

			}

		} catch (IOException e) {

		}
	}

	public void dataSet(String fName) {
		String line = null;

		try {
			File f = new File(fName);
			Scanner in = new Scanner(f);

			in.nextLine();
			contentList.findfirst();
			while (in.hasNextLine()) {
				line = in.nextLine();

				String temp = line.substring(0, line.indexOf(','));
				int id = Integer.parseInt(temp.trim());
				String content = line.substring(line.indexOf(',') + 1).trim();
				contentList.insert(new ContentList(content, id));
				LinkedList<String> text = indexedInvertedIndex(content, id);
				data.newRow(new Rows(id, text));
			}
		} catch (Exception e) {

		}
	}

	private LinkedList<String> indexedInvertedIndex(String content, int id) {
		LinkedList<String> text = new LinkedList<String>();
		content = content.toLowerCase();
		content = content.replaceAll("[^a-zA-Z0-9 ]", "");
		String[] tokens = content.split("\\s+");
		for (String token : tokens) {
			if (!isAStopWord(token)) {
				text.insert(token);
				invData.add(token, id);
				bstData.add(token, id);
			}
		}

		return text;
	}

	private boolean isAStopWord(String token) {
		if (stopWord.empty())
			return false;

		stopWord.findfirst();
		while (!stopWord.last()) {
			if (stopWord.retrieve().equals(token))
				return true;
			stopWord.findNext();
		}

		if (stopWord.retrieve().equals(token))
			return true;
		else
			return false;
	}

}
