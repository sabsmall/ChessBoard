package runner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import chessBoardGraphics.ChessBoardPanel;
import chessBoardModel.ChessBoardModel;
import chessBoardModel.Hit;
import chessBoardModel.Tile;


public class Runner {

	public static void main(String[] args) throws IOException {
		
		ChessBoardModel model = new ChessBoardModel(4, 4);
		
		BufferedImage horse = ImageIO.read(new File("chessBoard/chessBoardGraphics/horse.png"));

		ChessBoardPanel panel = new ChessBoardPanel(model, horse, ChessBoardPanel.TOP_LEFT_WHITE);
		
		manageModel(model);
		
		JFrame frame = new JFrame();
		
		frame.add(panel);
		
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	private static void manageModel(ChessBoardModel model) {
		model.set(1, 0);
		model.set(0, 1);
		model.set(3, 1);
		model.set(2, 2);
		model.set(1, 3);
		
		LinkedList<Hit> hits = model.hits();
		for (Hit hit : hits) {
			System.out.println(
					hit.getFrom().getX()+", "+
					hit.getFrom().getY()+" hits "+
					hit.getTo  ().getX()+", "+
					hit.getTo  ().getY()+"."
			);
		}
		
	}

}
