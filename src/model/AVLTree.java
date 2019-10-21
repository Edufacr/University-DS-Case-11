package model;

public class AVLTree<T> {
	private AVLNode<T> root;
	
	public AVLTree() {}
	
	public void add(T pContent) {
		this.add(pContent, this.root);
	}
	
	private void add(T pContent, AVLNode pNode) {
		if (pNode == null) {
			
		}
	}
	
	
	private void printPreorder(AVLNode<T> pNode) {
		if (pNode != null) {
			System.out.println(pNode.toString());
			printPreorder(pNode.getLeft());
			printPreorder(pNode.getRight());
		}
	}
	
	public void print() {
		printPreorder(this.root);
	}
}
