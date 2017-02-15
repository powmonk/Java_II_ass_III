import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Font;
import java.awt.GridLayout;

public class TileGame {
	public static void main(String[] args){
		TileFrame frame = new TileFrame();
		frame.setTitle("Tile Game");
		frame.setVisible(true);

	}
	
	static class TileFrame extends JFrame implements ActionListener{
		public TileFrame(){
			randomise();

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
			footerText.setFont(new Font("Courier", 1, 12));
			footerPanel.add(footerText);
			
			shuffleButton = new JButton("Shuffle");
			shuffleButton.addActionListener(this);
			footerPanel.add(shuffleButton);
			
		}

		private void addButtons() {
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(4,4));
			
			//x1
			x1y1 = new JButton(tiles[0]);
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
//			x4y4.addActionListener(this);
			x4y4.setEnabled(false);
			buttonPanel.add(x4y4);
			
		}

		private void setButtons(){
			x1y1.setText(tiles[0]);
			x1y2.setText(tiles[1]);
			x1y3.setText(tiles[2]);
			x1y4.setText(tiles[3]);
			x2y1.setText(tiles[4]);
			x2y2.setText(tiles[5]);
			x2y3.setText(tiles[6]);
			x2y4.setText(tiles[7]);
			x3y1.setText(tiles[8]);
			x3y2.setText(tiles[9]);
			x3y3.setText(tiles[10]);
			x3y4.setText(tiles[11]);
			x4y1.setText(tiles[12]);
			x4y2.setText(tiles[13]);
			x4y3.setText(tiles[14]);

		}

		private void randomise(){
			int random;
			ArrayList<String> seqOrder = new ArrayList<String>();
			
			for(int i=1; i<=15; i++){
				seqOrder.add(Integer.toString(i));
			}

			for(int i=0; i<15; i++){
				random = (int)(Math.random()*seqOrder.size());
				tiles[i] = seqOrder.get(random);
				seqOrder.remove(random);
			}
		}
		
		private void swapNumbers(ActionEvent e){
			prev = curr;
			
			for(int i=0;i<tiles.length;i++){
				if(tiles[i] == e.getActionCommand().toString()){
					curr = i;
				}
			}
			
			if(prev > -1 && curr > -1){
				temp = tiles[prev];
				tiles[prev] = tiles[curr];
				tiles[curr] = temp;
				setButtons();
				prev = -1;
				curr = -1;
			}
			
			
		}
		
		private static boolean winCondition(){
			for(int i=0; i<tiles.length;i++){
				if(Integer.parseInt(tiles[i]) != i+1){
					return false;
				}
			}
			return true;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == shuffleButton){
				curr = -1;
				randomise();
				setButtons();
			}else{
				swapNumbers(e);
				if(winCondition()){
					footerText.setText("WIN!!!");
				}
			}
			
		}
		
	private static String[] tiles = new String[15];	
	private JPanel buttonPanel, footerPanel;
	private JButton shuffleButton, x1y1, x1y2, x1y3, x1y4, x2y1, x2y2, x2y3, x2y4, x3y1, x3y2, x3y3, x3y4, x4y1, x4y2, x4y3, x4y4;
	private TextField footerText;
	private int prev = -1, curr = -1;
	private String temp;
	}
}