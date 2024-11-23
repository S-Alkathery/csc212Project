class Node<T>{
	public T data;
	public Node<T> next;
	public Node (T val) {
		data = val;
		next=null;
	}
}
public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
	public LinkedList () {
		head = current = null;
	}
	@Override
	public boolean empty() {
		return head==null;
	}
	@Override
	public boolean full() {
		return false;
	}
	@Override
	public void findfirst() {
		current = head;
		
	}
	@Override
	public void findNext() {
		current = current.next;
		
	}
	@Override
	public boolean last() {
		return current.next==null;
	}
	@Override
	public T retrieve() {
		return current.data;
	}
	@Override
	public void Update(T val) {
		current.data = val;
		
	}
	@Override
	public void insert(T val) {
		Node<T> tmp;
		if(empty()) {
			current = head = new Node<T> (val);
		}
		else{
			tmp= current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
		
	}
	@Override
	public void remove() {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;
			while(tmp.next !=current)
				tmp = tmp.next;
			tmp.next = current.next;
		}
		if (current.next==null)
			current = head;
		else
			current = current.next;
		
	}
	@Override
	public void display() {
		if(head==null)
			System.out.println("empty list");
		Node<T>p=head;
		while(p!=null) {
			System.out.print(p.data+" ");
			p=p.next;
		}
		System.out.println();
		
	}
	@Override
	public boolean existsInList(LinkedList<String> list, String word) {
		if(list.empty()) return false;
		list.findfirst();
		while(!list.last()) {
			if(list.retrieve().equals(word)) {
				return true;
			}
			list.findNext();
		}
		if(list.retrieve().equals(word)) {
			return true;
		}
		return false;
		
	}


//public static void main(String []args) {
//	List<String>L=new LinkedList<String>();
//	L.insert("aa");
//	L.insert("b");
//	L.insert("gg");
//	L.insert("acc");
//	L.display();
//} 
	}
