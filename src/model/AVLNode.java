package model;


public class AVLNode<T> implements Comparable<AVLNode>{

	private T contents;
	private int balance;
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
	
	public void updateBalance() {
		int leftBalance = 0;
		int rightBalance = 0;
		
		// recursive function to get height
		
		this.balance = rightBalance - leftBalance;
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
		if (this.toString().compareTo(pOtherNode.toString()) < 0) {
			return -1;
		} else if (this.toString().compareTo(pOtherNode.toString()) > 0) {
			return 1;
		}
		return 0;
	}

}
