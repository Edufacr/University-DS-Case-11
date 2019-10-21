package model;

public class AVLTree<T> {
	private AVLNode<T> root;
	
	public AVLTree() {}
	
	public void add(T pContent) {
		if (this.root == null) {
			this.root = new AVLNode<T>(pContent);
			return;
		}
		this.root = this.add(new AVLNode<T>(pContent), this.root);
	}
	
	private AVLNode<T> add(AVLNode<T> newNode, AVLNode<T> pNode) {
		if (pNode == null) {
			return newNode;
		}
		
		if (newNode.compareTo(pNode) < 0) {
			pNode.setLeft(this.add(newNode, pNode.getLeft()));
		} else if (newNode.compareTo(pNode) > 0) {
			pNode.setRight(this.add(newNode, pNode.getRight()));
		}
		
		return pNode;
	}
	
	private AVLNode<T> leftRotation(AVLNode<T> pNode){
		AVLNode<T> temp = pNode.getRight();
		
		pNode.setRight(temp.getLeft());
		temp.setLeft(pNode);
		
		
		return temp;
	}
	
	private AVLNode<T> rightRotation(AVLNode<T> pNode){
		AVLNode<T> temp = pNode.getLeft();
		
		pNode.setLeft(temp.getRight());
		temp.setRight(pNode);
		
		
		return temp;
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
	
	public static void main(String[] args) {
		AVLTree<String> avl = new AVLTree<String>();
		avl.add("F");
		avl.add("C");
		avl.add("B");
		avl.add("G");
		avl.add("E");
		avl.add("A");
		avl.add("J");
		avl.add("C");
		avl.print();
	}
}
