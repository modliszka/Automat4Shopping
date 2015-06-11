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
}
