package chessBoardModel;

public class Tile {
	
	public int  getX() { return x; }
	public int  getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	private int x, y;
	
	public Tile(int x, int y) {
		setX(x); setY(y);
	}

}
