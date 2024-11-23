
public class InvertedIndexBST {
	public BST<Words> tree;

	public InvertedIndexBST() {
		tree = new BST<Words>();
	}

	public void add(String word, int id) {
		if (!duplicateWord(word)) {
			Words w = new Words(word);
			w.rowID.insert(id);
			tree.insert(word, w);
		} else
			tree.retrieve().addID(id);

	}

	public boolean duplicateWord(String word) {
		return tree.findkey(word);
	}

	public void displayInvBST() {
		if (tree.empty() || tree == null)
			return;

		System.out.println("Inverted Index in BST: ");
		System.out.println("=============================");
		printInOrder(tree.root);

	}

	private void printInOrder(BSTNode<Words> node) {
		if (node == null)
			return;
		printInOrder(node.left);
		System.out.println("Word: " + node.data.word);
		System.out.print("[");
		node.data.rowID.display();
		System.out.println("]");
		System.out.println("=============================");
		printInOrder(node.right);
	}

}
