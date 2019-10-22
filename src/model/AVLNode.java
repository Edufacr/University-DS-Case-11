package model;


public class AVLNode<T> implements Comparable<AVLNode>{

	private T contents;
	private int balance;
	private int branchSize;
	private AVLNode<T> right;
	private AVLNode<T> left;
	
	public AVLNode(T pContents) {
		this.contents = pContents;
	}
	
	public T getContents() {
		return contents;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void setBalance(int pBalance) {
		this.balance = pBalance;
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
//		if (this.right == null && pRight != null) {
//			this.balance++;
//		} else if(this.right != null && pRight == null) {
//			this.balance--;
//		}
		this.right = pRight;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> pLeft) {
//		if (this.left == null && pLeft != null) {
//			this.balance--;
//		} else if(this.left != null && pLeft == null) {
//			this.balance++;
//		}
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
