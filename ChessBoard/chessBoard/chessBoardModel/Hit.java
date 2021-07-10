package chessBoardModel;

public class Hit {
	
	public Tile getFrom() { return from; }
	public Tile getTo  () { return to  ; }
	public void setFrom(Tile from) { this.from = from; }
	public void setTo  (Tile to  ) { this.to   = to  ; }
	private Tile from;
	private Tile to;
	
	public Hit(Tile from, Tile to) {
		setFrom(from); setTo(to);
	}
}
