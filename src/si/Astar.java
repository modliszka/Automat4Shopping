package si;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import shop.Ramka;
import shop.Ruch;

public class Astar  {
	ArrayList<Pair> points = new ArrayList<Pair>();
	ArrayList<Pair> path = new ArrayList<Pair>();
	int agentX, agentY;
	int kasaX=8,kasaY=9;
	private ArrayList<Node> closedList;
    private SortedNodeList openList;
    public static Node[][] nodes = new Node[10][10];
	
	public Astar(){ /*TODO konstruktor z list¹ punktów*/
		
		points.add(new Pair(8,6)); //zadane punkty
		points.add(new Pair(4,6));
		//points.add(new Pair(7,1));
		points.add(new Pair(5,5));
		
		
		points = findOptimalOrder(points);
		points.add(new Pair(kasaX,kasaY)); //do kasy
		
		
		//System.out.println(nodes[2][9].isObstacle() +" free " + ramy_wiedzy.Map.getInstance().isFree(2, 9));
		agentX = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaX();
		agentY = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaY();
		
		closedList = new ArrayList<Node>();
        openList = new SortedNodeList();
       
        //path = findShortestPath(agentX,agentY,7,3);
       /* int startx = agentY,starty = agentX;
        findShortestPath(startx,starty,7,2);
        move(startx,starty);
        startx=7;starty=2;
       // agentX = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaX();
	//	agentY = ramy_wiedzy.Agent.getInstance().getLoc().pobierzWspolrzednaY();
		findShortestPath(7,2,4,7);
        move(7,2);
        
        findShortestPath(4,7,8,9);
        move(4,7);*/
       int startx = agentX,starty = agentY;
        for(Pair p:points){
        	findShortestPath(startx,starty,p.getX(),p.getY());
        	move(startx,starty);
        	startx = p.getX();
        	starty = p.getY();
        }
		
	}
	
	private ArrayList<Pair> findOptimalOrder(ArrayList<Pair> points) {
		ArrayList<Pair> optimal = new ArrayList<Pair>();
		ArrayList<Pair> start = new ArrayList<Pair>();
		ArrayList<Pair> end = new ArrayList<Pair>();
		//Pair max;
		//max = points.get(0);
		for(Pair p:points){
			if(manhattan(1,9,p.getX(),p.getY())<manhattan(kasaX,kasaY,p.getX(),p.getY())){ //8,9 (kasa)
				start.add(p);
			}else{
				end.add(p);
			}
			/*if(manhattan(8,9,p.getX(),p.getY())>manhattan(8,9,max.getX(),max.getY())){
				max=p;
			}*/
		}
		//System.out.println("optimal1s: " + start.toString());
		//System.out.println("optimal1e: " + end.toString());
		start = sortUp(start);
		end = sortDown(end);
		//System.out.println("optimal2s: " + start.toString());
		//System.out.println("optimal2e: " + end.toString());
		optimal = start;
		
		for(Pair e:end){
			optimal.add(e);
		}
		//System.out.println("optimal3: " + optimal.toString());
		return optimal;
	}
	
	public ArrayList<Pair> sortUp(ArrayList<Pair> points){
		ArrayList<Pair> result = new ArrayList<Pair>();
		Pair[] pointsArray = new Pair [points.size()];
		pointsArray = points.toArray(pointsArray);
		
		for (int i =0;i<pointsArray.length-1;i++){
			for(int j=i+1;j<pointsArray.length;j++){
				if(manhattan(agentX, agentY, pointsArray[i].getX(), pointsArray[i].getY())>manhattan(agentX, agentY, pointsArray[j].getX(), pointsArray[j].getY())){
					Pair tmp = pointsArray[i];
					pointsArray[i] = pointsArray[j];
					pointsArray[j]=tmp;
					
				}
			}
		}
		for(int i=0;i<pointsArray.length;i++){
			result.add(pointsArray[i]);
		}
		return result;
	}
	public ArrayList<Pair> sortDown(ArrayList<Pair> points){
		ArrayList<Pair> result = new ArrayList<Pair>();
		Pair[] pointsArray = new Pair [points.size()];
		pointsArray = points.toArray(pointsArray);
		
		for (int i =0;i<pointsArray.length-1;i++){
			for(int j=i+1;j<pointsArray.length;j++){
				if(manhattan(kasaX,kasaY, pointsArray[i].getX(), pointsArray[i].getY())<manhattan(kasaX,kasaY, pointsArray[j].getX(), pointsArray[j].getY())){
					Pair tmp = pointsArray[i];
					pointsArray[i] = pointsArray[j];
					pointsArray[j]=tmp;
					
				}
			}
		}
		for(int i=0;i<pointsArray.length;i++){
			result.add(pointsArray[i]);
		}
		return result;
	}
	

	ArrayList<Pair> findShortestPath(int startX, int startY, int endX, int endY){
		openList.clear();
		closedList.clear();
		for(int i=0;i<nodes.length;i++){
			for(int j=0;j<nodes[i].length;j++){
				nodes[i][j] = new Node(i,j);
			}
		}
		//System.out.println("START");
		ArrayList<Pair> shortestPath = new ArrayList<Pair>();
		shortestPath.add(new Pair(startX,startY));
		//nodes[startX][startY] = new Node(startX,startY);
		nodes[startX][startY].setCost(0);
		nodes[startX][startY].setHeuristic(manhattan(startX,startY,endX,endY));
		//System.out.println("score: " + nodes[startX][startY].getScore());
		nodes[startX][startY].prevx=-1;
		nodes[startX][startY].prevy=-1;
		openList.add(nodes[startX][startY]);
		
		while(openList.size()!=0){
			//System.out.println("WHILE START");
			Node x = openList.getFirst();
			if(x.getX() == endX && x.getY() == endY){
				return getPath(x);
			}
			openList.remove(x);
			closedList.add(x);
			
			for(Node neighbor : x.getNeighborList()) {
                boolean neighborIsBetter;

                if (closedList.contains(neighbor))
                        continue;
                
                if (neighbor.isObstacle())
                	continue;
                
                	int neighborDistanceFromStart = x.getCost() + 1; ///////
                    //System.out.println(neighborDistanceFromStart);
                    if(!openList.contains(neighbor)) {
                    	neighbor.setPreviousNode(x.getX(),x.getY());
                        neighbor.setCost(neighborDistanceFromStart);
                    	neighbor.setHeuristic(manhattan(neighbor.getX(),neighbor.getY(),endX,endY));
                    	//System.out.println("score: " + nodes[neighbor.getX()][neighbor.getY()].getScore());
                        openList.add(neighbor);
                        neighborIsBetter = true;
                    } else if(neighborDistanceFromStart < x.getCost()) {
                        neighborIsBetter = true;
                    } else {
                        neighborIsBetter = false;
                    }
                    if (neighborIsBetter) {
                        neighbor.setPreviousNode(x.getX(),x.getY());
                        //neighbor.setDistanceFromStart(neighborDistanceFromStart);
                        //neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                    	neighbor.setCost(neighborDistanceFromStart);
                    	neighbor.setHeuristic(manhattan(neighbor.getX(),neighbor.getY(),endX,endY));
                    	//System.out.println("score: " + nodes[neighbor.getX()][neighbor.getY()].getScore());
                    	//neighbor = new Node(neighbor.getX(),neighbor.getY(),neighborDistanceFromStart,manhattan(neighbor.getX(),neighbor.getY(),endX,endY));
                    	//shortestPath.add(new Pair(neighbor.getX(),neighbor.getY()));
                    }
                
                
			}
		}
		return null;
	}
	
	private int manhattan(int startX, int startY, int endX, int endY) {
		Double result = new Double (Math.sqrt((startX-endX)*(startX-endX)) + Math.sqrt((startY-endY)*(startY-endY)));
		return result.intValue();
	}

	void move(int sx,int sy){
		System.out.println("path: " + path.toString());
		path.add(0,new Pair(sx,sy));
		while(path.size()>1){
			//System.out.println("path: " + path.get(1) + " " + path.get(0));
			if(path.get(1).getX() - path.get(0).getX() ==1){
				goRight();
				path.remove(0);
			}else if(path.get(1).getX() - path.get(0).getX() == -1){
				goLeft();
				path.remove(0);
			}else{
				if(path.get(1).getY() - path.get(0).getY() == 1){
					goDown();
					path.remove(0);
				}else{
					goUp();
					path.remove(0);
				}
			}
		}
		
		//ramy_wiedzy.Agent.getInstance().setLoc(path.get(0).getX(),path.get(0).getY());
		//goUp();goDown();goUp();goUp();
		
	}
	
	
	void goLeft() {
		Ruch.getInstance().rusz_sie(-1, 0);
	}
	void goRight(){
		Ruch.getInstance().rusz_sie(1,0);
	}
	void goUp(){
		Ruch.getInstance().rusz_sie(0,-1);
	}
	void goDown(){
		Ruch.getInstance().rusz_sie(0,1);
	}
	
	public ArrayList<Pair> getPath(Node last){
		ArrayList<Pair> result = new ArrayList<Pair>();
		//result.add(new Pair(0,0));
		while(last.getPrevX()!=-1){
			result.add(0,new Pair(last.getX(),last.getY()));
            last = nodes[last.getPrevX()][last.getPrevY()];
		}
		//System.out.println("PATH" + result.toString());
		this.path = result;
		return result;
	}
	
	private class Node{
		int cost;
		int heuristic;
		int score;
		int x;
		int y;
		boolean obstacle;
		int prevx,prevy;
		
	/*	public Node(int x, int y, int cost, int heuristic){
			this.x=x;
			this.y=y;
			this.cost = cost;
			this.heuristic = heuristic;
			//System.out.println(heuristic);
			this.score = cost + heuristic;
		}*/
		public Node(int x, int y){
			this.x=x;
			this.y=y;
			this.obstacle=true;
			this.cost=0;
			this.heuristic=0;
			this.score=0;
			if(ramy_wiedzy.Map.getInstance().isFree(x,y)){
				this.obstacle=false;
			
			}
		}
		public int getPrevX() {
		return this.prevx;
	}
		public int getPrevY() {
			return this.prevy;
		}
		public void setPreviousNode(int x, int y) {
			this.prevx=x;
			this.prevy=y;
			
		}
		public void setCost(int cost){
			this.cost = cost;
			this.score = this.cost + this.heuristic;
		}
		public void setHeuristic(int heuristic){
			this.heuristic = heuristic;
			this.score = this.cost + this.heuristic;
		}
		
		public int getScore(){
			return this.score;
		}
		public int getCost(){
			return this.cost;
		}
		public int getX(){
			return this.x;
		}
		public int getY(){
			return this.y;
		}
		public ArrayList<Node> getNeighborList(){
			ArrayList<Node> result = new ArrayList<Node>();
			if(x!=0 && !nodes[x-1][y].isObstacle()){
					result.add(nodes[x-1][y]);
			}
			if(x!=9 && !nodes[x+1][y].isObstacle()){
					result.add(nodes[x+1][y]);
			}
			if(y!=9 && !nodes[x][y+1].isObstacle()){
					result.add(nodes[x][y+1]);
			}
			if(y!=0 && !nodes[x][y-1].isObstacle()){
					result.add(nodes[x][y-1]);
			}
			return result;
		}
		public boolean isObstacle(){
			return obstacle;
		}
		public String toString(){
			return this.getScore() +"";
		}
	}
	
	private class SortedNodeList {

        private ArrayList<Node> list = new ArrayList<Node>();

        public Node getFirst() {
                return list.get(0);
        }

        public void clear() {
                list.clear();
        }

        public void add(Node node) { // TODO KOLEJKA PRIORYTETOWA przestrzen stanow
        	//System.out.println("NODE: " + node);
                list.add(node);
                //System.out.println("list " + list.toString());
                //Collections.sort(list);
                /*TODO sortowanie od najmniejszej*/
                //Collections.sort(list, comparing(Node::getScore));
               /*Collections.sort(list, new Comparator<Node>(){
                    public int compare(Node s1, Node s2) {
                    	return s1.getScore()+"".compareToIgnoreCase(s2.getScore()+"");
                    }    
            });*/
                list = sort(list);
                
                //System.out.println("list " + list.toString());
        }

        private ArrayList<Node> sort(ArrayList<Node> list2) {
        	ArrayList<Node> result = new ArrayList<Node>();
        	Node[] nodeArray = new Node[list2.size()];
        	nodeArray = list2.toArray(nodeArray);
        	
        	for(int i=0;i<nodeArray.length-1;i++){
        		for(int j=i+1;j<nodeArray.length;j++){
        			if(nodeArray[j].getScore()<nodeArray[i].getScore()){
        				Node tmp = nodeArray[j];
        				nodeArray[j] = nodeArray[i];
        				nodeArray[i]=tmp;
        			}
        		}
        	}
        	for(int i=0;i<nodeArray.length;i++){
        		result.add(nodeArray[i]);
        	}

        	return result;
		}

		public void remove(Node n) {
                list.remove(n);
        }

        public int size() {
                return list.size();
        }

        public boolean contains(Node n) {
                return list.contains(n);
        }/*todo przesszukiwanie przestrzeni stanow, kolejka priorytetowa, funkcja nastepnika
        	stan akcja
        */
}
	
}
