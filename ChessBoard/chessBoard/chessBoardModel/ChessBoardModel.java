package chessBoardModel;

import java.util.LinkedList;

public class ChessBoardModel {
	
	private boolean[][] hasHorse;
	public int  getColumns () { return hasHorse[0].length; }
	public int  getRows    () { return hasHorse   .length; }
	
	public void    set   (int x, int y) { hasHorse[x][y] = true ; }
	public void    clear (int x, int y) { hasHorse[x][y] = false; }
	public boolean is    (int x, int y) { return hasHorse[x][y];  }
	
	public ChessBoardModel(int width, int height) {
		hasHorse = new boolean[width][height];
		clearAll();
	}
	
	public void clearAll() {
		for (int w = 0; w < hasHorse.length; w++) {
			for (int h = 0; h < hasHorse[w].length; h++) {
				hasHorse[w][h] = false;
			}
		}
	}
	
	public LinkedList<Hit> hits() {
		
		LinkedList<Hit> hits = new LinkedList<Hit>();
		
		for (int x = 0; x < hasHorse.length; x++) {
			for (int y = 0; y < hasHorse[0].length; y++) {
				LinkedList<Tile> boundedMoves = getBoundedHorseMoves(x, y);
				for (Tile tile : boundedMoves) {
					if (hasHorse[tile.getX()][tile.getY()]) hits.add(new Hit(new Tile(x, y), tile));
				}
			}
		}
		return hits;
	}
	
	private LinkedList<Tile> getBoundedHorseMoves(int x, int y) {
		
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
