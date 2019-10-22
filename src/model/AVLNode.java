package model;


public class AVLNode<T> implements Comparable<AVLNode>{

	private T contents;
	private int branchSize;
	private AVLNode<T> right;
	private AVLNode<T> left;
	
	public AVLNode(T pContents) {
		this.contents = pContents;
		this.branchSize = 1;
	}
	
	public T getContents() {
		return contents;
	}
	
	public int getBranchSize() {
		return this.branchSize;
	}

	public void setBranchSize(int pBranchSize) {
		this.branchSize = pBranchSize;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> pRight) {
		this.right = pRight;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> pLeft) {
		this.left = pLeft;
	}

	@Override
	public String toString() {
		return this.contents.toString();
	}

	@Override
	public int compareTo(AVLNode pOtherNode) {
		if (pOtherNode == null) {
			return 1;
		}
		
		if (this.toString().compareTo(pOtherNode.toString()) < 0) {
			return -1;
		} else if (this.toString().compareTo(pOtherNode.toString()) > 0) {
			return 1;
		}
		return 0;
	}
}
