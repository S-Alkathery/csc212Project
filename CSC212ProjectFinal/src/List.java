
public interface List <T> {
	boolean empty();
	boolean full();
	
	void findfirst();
	void findNext();
	boolean last ();
	T retrieve () ;
	void Update (T e);
	void insert (T e) ;
	void remove ();
	void display();
	boolean existsInList(LinkedList<String> list, String word);

}
