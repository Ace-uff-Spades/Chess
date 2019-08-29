package chess;

public class Point {
	int x; 
	int y; 
	
	public Point(String cord) {
		if(cord.length()<=1) {
			x = -1;
			y = -1;
			System.out.println("Invalid cordinates");
		}
		else {
			//if(cord.substring(0,1).equals("a")) {
			String xcord = cord.substring(0,1);
			y = 8 - Integer.parseInt(cord.substring(1));
			
			
			if(xcord.equals("a")) { x = 0;}
			else if(xcord.equals("b")) { x = 1;}
			else if(xcord.equals("c")) { x = 2;}
			else if(xcord.equals("d")) { x = 3;}
			else if(xcord.equals("e")) { x = 4;}
			else if(xcord.equals("f")) { x = 5;}
			else if(xcord.equals("g")) { x = 6;}
			else if(xcord.equals("h")) { x = 7;}
			else {
				x = -1;
			}
			
		}
	}
	public Point(int x1, int y1) {
		x = x1;
		y = y1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public boolean equals(Object o) {
		if(o instanceof Point) {
			Point p = (Point) o; 
			return (p.getX() == this.x && p.getY() == this.y);
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
