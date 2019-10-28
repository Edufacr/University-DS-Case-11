package model;

public class AVLTree<T extends Comparable<T>> {
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
		} else {
			return pNode;
		}
		
		pNode.setBranchSize(1 + Math.max(nodeSize(pNode.getLeft()), nodeSize(pNode.getRight())));
	
		return balance(pNode, newNode);
	}
	
	private AVLNode<T> leftRotation(AVLNode<T> pNode){
		AVLNode<T> temp = pNode.getRight();
		
		pNode.setRight(temp.getLeft());
		temp.setLeft(pNode);
		
		pNode.setBranchSize(1 + Math.max(nodeSize(pNode.getLeft()), nodeSize(pNode.getRight())));
		temp.setBranchSize(1 + Math.max(nodeSize(temp.getLeft()), nodeSize(temp.getRight())));
		
		return temp;
	}

	
	private AVLNode<T> rightRotation(AVLNode<T> pNode){
		AVLNode<T> temp = pNode.getLeft();
		
		pNode.setLeft(temp.getRight());
		temp.setRight(pNode);
		
		pNode.setBranchSize(1 + Math.max(nodeSize(pNode.getLeft()), nodeSize(pNode.getRight())));
		temp.setBranchSize(1 + Math.max(nodeSize(temp.getLeft()), nodeSize(temp.getRight())));
		
		return temp;
	}
	
	private int nodeSize(AVLNode<T> pNode) {
		if (pNode == null) {
			return 0;
		}
		return pNode.getBranchSize();
	}
	
	private AVLNode<T> balance(AVLNode<T> pNode, AVLNode<T> newNode){
		
		int nodeBalance;
		
		if (pNode == null) {
			nodeBalance = 0;
		}
		
		nodeBalance = nodeSize(pNode.getRight()) - nodeSize(pNode.getLeft());
		
		if (nodeBalance < -1) {
			if (newNode.compareTo(pNode.getLeft()) < 0) { // Left Left Case
		    	return rightRotation(pNode); 
		    } else if (newNode.compareTo(pNode.getLeft()) > 0) { // Left Right Case 
				pNode.setLeft(leftRotation(pNode.getLeft())); 
				return rightRotation(pNode); 
			} 
		} else if(nodeBalance > 1) {
			if (newNode.compareTo(pNode.getRight()) > 0) { // Right Right Case 
				return leftRotation(pNode); 
			} else if (newNode.compareTo(pNode.getRight()) < 0) { // Right Left Case
				pNode.setRight(rightRotation(pNode.getRight())); 
				return leftRotation(pNode); 
			} 
		}
		
		return pNode;
	}
	
	public T get(T pContent) {
		return get(pContent, this.root);
	}
	
	private T get (T pContent, AVLNode<T> pNode) {
		
		if (pNode == null) {
			return null;
		}
		
		if(pNode.getContents().compareTo(pContent) == 0) {
			return pNode.getContents();
		} else if(pNode.getContents().compareTo(pContent) > 0) {
			return get(pContent, pNode.getLeft());
		} else{
			return get(pContent, pNode.getRight());
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
	
	public static void main(String[] args) {
		AVLTree<String> avl = new AVLTree<String>();
		avl.add("A");
		avl.add("B");
		avl.add("C");
		avl.add("D");
		avl.add("E");
		avl.add("F");
		//avl.print();
		
		System.out.println(avl.get("A"));
	}
}
