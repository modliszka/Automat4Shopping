package searchingShelf;

public class Change {
	int box;
	char operation;
	int from;
	int to;
	
	public Change(int box, char operation, int from, int to){
		this.box=box;
		this.operation=operation;
		this.from=from;
		this.to=to;
	}

	public static Change copy(Change ch){
		Change c=new Change(ch.box,ch.operation,ch.from,ch.to);
		return c;
	}
}
