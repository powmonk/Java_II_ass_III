import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

public class TileGame {
	public static void main(String[] args){
		TileFrame frame = new TileFrame();
		frame.setTitle("Tile Game");
		frame.setVisible(true);
	}
	
	static class TileFrame extends JFrame implements ActionListener{
		public TileFrame(){
			final int DEFAULT_FRAME_WIDTH = 500;
			final int DEFAULT_FRAME_HEIGHT = 400;
			setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
			
			addButtons();
			
			addFooter();
			
			setLayout(new GridLayout(2, 1));
			add(buttonPanel); 
			add(footerPanel);


		}
		
		private void addFooter() {
			footerPanel = new JPanel();
			footerPanel.setLayout(new GridLayout());
			
			// TODO Auto-generated method stub
			footerText = new TextField();
			footerPanel.add(footerText);
			
			shuffleButton = new JButton("Shuffle");
			shuffleButton.addActionListener(this);
			footerPanel.add(shuffleButton);
			
		}

		private void addButtons() {
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(4,4));
			
			//x1
			x1y1 = new JButton();
			x1y1.setText(tiles[0]);
			x1y1.addActionListener(this);
			buttonPanel.add(x1y1);
			//x1 
			x1y2 = new JButton(tiles[1]);
			x1y2.addActionListener(this);
			buttonPanel.add(x1y2);
			//x1
			x1y3 = new JButton(tiles[2]);
			x1y3.addActionListener(this);
			buttonPanel.add(x1y3);
			//x1
			x1y4 = new JButton(tiles[3]);
			x1y4.addActionListener(this);
			buttonPanel.add(x1y4);

			//x2
			x2y1 = new JButton(tiles[4]);
			x2y1.addActionListener(this);
			buttonPanel.add(x2y1);
			//x2 
			x2y2 = new JButton(tiles[5]);
			x2y2.addActionListener(this);
			buttonPanel.add(x2y2);
			//x2
			x2y3 = new JButton(tiles[6]);
			x2y3.addActionListener(this);
			buttonPanel.add(x2y3);
			//x2
			x2y4 = new JButton(tiles[7]);
			x2y4.addActionListener(this);
			buttonPanel.add(x2y4);

			//x3
			x3y1 = new JButton(tiles[8]);
			x3y1.addActionListener(this);
			buttonPanel.add(x3y1);
			//x3 
			x3y2 = new JButton(tiles[9]);
			x3y2.addActionListener(this);
			buttonPanel.add(x3y2);
			//x3
			x3y3 = new JButton(tiles[10]);
			x3y3.addActionListener(this);
			buttonPanel.add(x3y3);
			//x3
			x3y4 = new JButton(tiles[11]);
			x3y4.addActionListener(this);
			buttonPanel.add(x3y4);
			
			//x4
			x4y1 = new JButton(tiles[12]);
			x4y1.addActionListener(this);
			buttonPanel.add(x4y1);
			//x4 
			x4y2 = new JButton(tiles[13]);
			x4y2.addActionListener(this);
			buttonPanel.add(x4y2);
			//x4
			x4y3 = new JButton(tiles[14]);
			x4y3.addActionListener(this);
			buttonPanel.add(x4y3);
			//x4
			x4y4 = new JButton();
			x4y4.addActionListener(this);
			buttonPanel.add(x4y4);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == shuffleButton){
				tiles[0] = "shuffle";
				x1y1.setText(tiles[0]);
			}
			
		}
		
	private String[] tiles = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};	
	private JPanel buttonPanel, footerPanel;
	private JButton shuffleButton, x1y1, x1y2, x1y3, x1y4, x2y1, x2y2, x2y3, x2y4, x3y1, x3y2, x3y3, x3y4, x4y1, x4y2, x4y3, x4y4;
	private TextField footerText;
	}
}