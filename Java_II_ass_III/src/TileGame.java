import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.Color;
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

			final int DEFAULT_FRAME_WIDTH = 400;
			final int DEFAULT_FRAME_HEIGHT = 250;
			setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
			
			addButtons();
			
			addFooter();
			
			setLayout(new GridLayout(JPnls.size()+1, 1));
			for(int i=0;i<JPnls.size();i++){
				add(JPnls.get(i)); 
			}
			add(footerPanel);
		}
		
		private void addFooter() {
			//ignore this comment
			footerPanel = new JPanel();
			footerPanel.setLayout(new GridLayout());
			
			footerText = new JTextArea();
			footerText.setFont(new Font("Courier", 1, 12));
			footerText.setEditable(false);
			footerText.setLineWrap(true);
			footerText.setWrapStyleWord(true);
			footerPanel.add(footerText);
			
			shuffleButton = new JButton("Shuffle");
			shuffleButton.addActionListener(this);
			footerPanel.add(shuffleButton);
			
		}

		private void addButtons() {
			//The outer loop creates the rows, the inner loops creates the columns
			for(int i=0;i<N;i++){
				JPnls.add(i, new JPanel());
				JPnls.get(i).setLayout(new GridLayout(1,4));
				
				for(int j=0; j<N; j++){
					//Here the buttons are created by using the inner and outer loop counter for X + Y positions
					JBtns[i][j] = new JButton();
					JBtns[i][j].addActionListener(this);
					JPnls.get(i).add(JBtns[i][j]);
				}
			}
			setButtons();
		}

		private void setButtons(){
			int count = 0;
			for(int i=0; i<N;i++){
				for(int j=0; j<N;j++){
					//The loop counters give X+Y positions to the 2D array
					JBtns[i][j].setText(tiles[count]);
					count++;
				}
			}
		}

		private void randomise(){
			int random;
			ArrayList<String> seqOrder = new ArrayList<String>();
			
			for(int i=1; i<N*N; i++){
				seqOrder.add(Integer.toString(i));
			}
			seqOrder.add("");
			
			int seqSize = seqOrder.size();

			for(int i=0; i<seqSize; i++){
				random = (int)(Math.random()*seqOrder.size());
				tiles[i] = seqOrder.get(random);
				seqOrder.remove(random);
			}
		}
		
		private void swapNumbers(ActionEvent e){
			prev = curr;
			//this loop compares the tiles array to the command value of the button to check if a number and assign it to curr
			for(int i=0;i<tiles.length;i++){
				if(tiles[i] == e.getActionCommand().toString() ){
					curr = i;
					footerText.setText("");
				}
			}

			// if(prev > -1 && tiles[curr] != ""){
			// 	footerText.setText("No!");
			// } 
				
			if(prev > -1 && curr > -1){
				temp = tiles[prev];
				tiles[prev] = tiles[curr];
				tiles[curr] = temp;
				setButtons();
				prev = -1;
				curr = -1;
				resetButtons();
			}
		}

		private void resetButtons() {
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					JBtns[i][j].setBackground(null);
					JBtns[i][j].setSelected(false);
				}
			}
		}


		private void setTiles(ActionEvent e){
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(JBtns[i][j] == e.getSource()){
						JBtns[i][j].setBackground(activeTile);
					}
				}
			}
		}

		private static boolean isValidMove(Object e) {
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(JBtns[i][j].getBackground() == activeTile){
 					}

					if(JBtns[i][j].getActionCommand() == ""){
						if(JBtns[i][j] == e){return true;}
						if(i-1 > -1){if(JBtns[i-1][j] == e){return true;};}
						if(i+1 <  N){if(JBtns[i+1][j] == e){return true;};}
						if(j-1 > -1){if(JBtns[i][j-1] == e){return true;};}
						if(j+1 <  N){if(JBtns[i][j+1] == e){return true;};}
					}
				}
			}
			
			return false;
		}
		
		private static boolean winCondition(){
			int parsed;
			for(int i=0; i<tiles.length-1; i++){
				// This try-catch stops the contents of the empty tile being compared to a number
				try{
					parsed = Integer.parseInt(tiles[i]);
				}catch(NumberFormatException e) { 
			    	return false; 
			    }catch(NullPointerException e) {
			    	return false;
			    }

				if(parsed != i+1){
					return false;
				}
			}
			return true;
		}
		
		private void rainbowTiles(){
			int count = 0;
			try{
				while(true){
					count=count>255?0:count;
					
					for(int i=0;i<N;i++){
						for(int j=0;j<N;j++){
							JBtns[i][i].setBackground(new Color(count, count, count));
						}
					}
					count++;
					Thread.sleep(10000);
				}
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == shuffleButton){
				curr = -1;
				footerText.setText(" ");
				randomise();
				setButtons();
				resetButtons();
			}else{
				
				if(isValidMove(e.getSource())){
					setTiles(e);
					swapNumbers(e);
					rainbowTiles();

				}else{
					footerText.setText("You need to pick a tile adjacent to the empty tile");
				}
				
				//Check if  the game has been beaten
				if(winCondition()){
					rainbowTiles();
					footerText.setText("WIN!!!");
				}
			}
		}
		
	private static Color activeTile = new Color(200,200,255);
	private static Object selected, prevSelected;
	private static final int N = 4;
	private static String[] tiles = new String[N*N];	
	private JPanel footerPanel;
	private JButton shuffleButton;
	private List<JPanel>  JPnls = new ArrayList<JPanel>();
	private static JButton[][] JBtns = new JButton[N][N];
	private JTextArea footerText;
	private static int prev = -1, curr = -1;
	private String temp;
	}
}