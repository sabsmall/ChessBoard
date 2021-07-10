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
	

	public BufferedImage getHorse () { return horse; }
	public void          setHorse (BufferedImage horse) {
		this.horse = ImageUtil.convertToManaged(horse);
		invertedHorse = ImageUtil.invertImageColors(horse);
		w = horse.getWidth();
		h = horse.getHeight();
	}
	private BufferedImage horse;
	private BufferedImage invertedHorse;
	private int w, h;
	
	
	public static final boolean TOP_LEFT_WHITE = true; 
	public static final boolean TOP_LEFT_BLACK = false; 
	public boolean isTopLeftWhite() { return isTopLeftWhite; }
	public void   setTopLeftWhite(boolean isTopLeftWhite) { 
		this.isTopLeftWhite = isTopLeftWhite;
		setBackground(getColor(isTopLeftWhite));
	}
	private boolean isTopLeftWhite;
	
	
	public ChessBoardPanel(ChessBoardModel model, BufferedImage horse, boolean isTopLeftWhite) {
		
		setModel(model);
		setHorse(horse);
		
		setPreferredSize ( new Dimension (
										model.getColumns() * horse.getWidth (),
										model.getRows   () * horse.getHeight()    ) );
		
		setTopLeftWhite(isTopLeftWhite);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		for (int y = 0; y < model.getRows(); y++) {
			for (int x = 0; x < model.getColumns(); x++) {
				if (model.is(x, y)) drawHorse(g2, x*w, y*h, isWhite(x,y));
				else drawTile(g2, x*w, y*h, isWhite(x,y));
			}
		}
	}
	
	
	
	private boolean isWhite(int x, int y) {
		return ((x+y)%2==0) == isTopLeftWhite;
	}
	private void drawTile(Graphics2D g2, int x, int y, boolean isWhite) {
		if(isWhite) g2.setColor(Color.WHITE);
		else g2.setColor(Color.BLACK);
		g2.fillRect(x, y, w, h);
	}
	
	private void drawHorse(Graphics2D g2, int x, int y, boolean isWhite) {
		if(isWhite) g2.drawImage(horse, x, y, this);
		else g2.drawImage(invertedHorse, x, y, this);
	}
	
	private Color getColor(boolean c) { return c ? Color.WHITE : Color.BLACK; }
	
}
