package chessBoardGraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import chessBoardModel.ChessBoardModel;

@SuppressWarnings("serial")
public class ChessBoardPanel extends JPanel {
	
	public ChessBoardModel getModel() { return model; }
	public void setModel(ChessBoardModel model) { this.model = model; }
	private ChessBoardModel model;
	

	public BufferedImage getknight () { return knight; }
	public void          setKnight (BufferedImage knight) {
		this.knight = ImageUtil.convertToManaged(knight);
		invertedKnight = ImageUtil.invertImageColors(knight);
		w = knight.getWidth();
		h = knight.getHeight();
	}
	private BufferedImage knight;
	private BufferedImage invertedKnight;
	private int w, h;
	
	
	public static final boolean TOP_LEFT_WHITE = true; 
	public static final boolean TOP_LEFT_BLACK = false; 
	public boolean isTopLeftWhite() { return isTopLeftWhite; }
	public void   setTopLeftWhite(boolean isTopLeftWhite) { 
		this.isTopLeftWhite = isTopLeftWhite;
		setBackground(getColor(isTopLeftWhite));
	}
	private boolean isTopLeftWhite;
	
	
	public ChessBoardPanel(ChessBoardModel model, BufferedImage knight, boolean isTopLeftWhite) {
		
		setModel(model);
		setKnight(knight);
		
		setPreferredSize ( new Dimension (
										model.getColumns() * knight.getWidth (),
										model.getRows   () * knight.getHeight()    ) );
		
		setTopLeftWhite(isTopLeftWhite);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for (int y = 0; y < model.getRows(); y++) {
			for (int x = 0; x < model.getColumns(); x++) {
				if (model.is(x, y)) drawKnight(g2, x*w, y*h, isWhite(x,y));
				else drawTile(g2, x*w, y*h, isWhite(x,y));
			}
		}
	}
	
	private boolean isWhite(int x, int y) { return ((x+y)%2==0) == isTopLeftWhite; }
	
	private void drawKnight(Graphics2D g2, int x, int y, boolean isWhite) {
		if(isWhite) g2.drawImage(knight, x, y, this);
		else g2.drawImage(invertedKnight, x, y, this);
	}
	
	private void drawTile(Graphics2D g2, int x, int y, boolean isWhite) {
		g2.setColor(getColor(isWhite));
		g2.fillRect(x, y, w, h);
	}
	
	private Color getColor(boolean isWhite) { return isWhite ? Color.WHITE : Color.BLACK; }
	
}
