package searchingShelf;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return (int)((o1.cost+o1.h_cost)-(o2.cost+o2.h_cost));
	}

}
