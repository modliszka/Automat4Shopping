package searchingShelf;

import java.util.Stack;

public class Node {
	float cost;
	float h_cost;
	int[][] boxes;
	Stack<Change> st;
	public Node(int[][] boxes, float cost,float h_cost, Stack<Change> st){
		this.cost=cost;
		this.h_cost=h_cost;
		//Start.arrayCopy(boxes, this.boxes);
//		this.boxes=boxes.clone();
		this.boxes=Start.cloneArray(boxes);
		this.st=(Stack<Change>)st.clone();
	}
}
