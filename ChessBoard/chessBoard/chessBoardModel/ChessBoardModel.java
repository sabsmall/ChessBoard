package chessBoardModel;

import java.util.LinkedList;

// TODO: Separate out interfaces for view and controller access
public class ChessBoardModel {
	
	private boolean[][] hasKnight;
	public int  getColumns () { return hasKnight[0].length; }
	public int  getRows    () { return hasKnight   .length; }
	
	public void    set   (int x, int y) { hasKnight[x][y] = true ; }
	public void    clear (int x, int y) { hasKnight[x][y] = false; }
	public boolean is    (int x, int y) { return hasKnight[x][y];  }
	
	public ChessBoardModel(int width, int height) {
		hasKnight = new boolean[width][height];
		clearAll();
	}
	
	public void clearAll() {
		for (int w = 0; w < hasKnight.length; w++) {
			for (int h = 0; h < hasKnight[0].length; h++) {
				hasKnight[w][h] = false;
			}
		}
	}
	
	public LinkedList<Hit> hits() {
		
		LinkedList<Hit> hits = new LinkedList<Hit>();
		
		for (int x = 0; x < hasKnight.length; x++) {
			for (int y = 0; y < hasKnight[0].length; y++) {
				if(hasKnight[x][y]) {
					LinkedList<Tile> boundedMoves = getBoundedKnightMoves(x, y);
					for (Tile tile : boundedMoves) {
						if (hasKnight[tile.getX()][tile.getY()]) hits.add(new Hit(new Tile(x, y), tile));
					}
				}
			}
		}
		return hits;
	}
	
	private LinkedList<Tile> getBoundedKnightMoves(int x, int y) {
		
		LinkedList<Tile> boundedMoves = new LinkedList<Tile>();
		
		addIfBounded(x+2, y+1, boundedMoves);
		addIfBounded(x+2, y-1, boundedMoves);
		addIfBounded(x-2, y+1, boundedMoves);
		addIfBounded(x-2, y-1, boundedMoves);
		addIfBounded(x+1, y+2, boundedMoves);
		addIfBounded(x+1, y-2, boundedMoves);
		addIfBounded(x-1, y+2, boundedMoves);
		addIfBounded(x-1, y-2, boundedMoves);
		
		return boundedMoves;
		
	}
	
	private void addIfBounded(int x, int y, LinkedList<Tile> boundedMoves) {
		if (isInsideBoard(x,y)) boundedMoves.add(new Tile(x,y));
	}
	
	private boolean isInsideBoard(int x, int y) {
		return 0 <= x && x < getColumns() && 0 <= y && y < getRows();
	}
	
}
