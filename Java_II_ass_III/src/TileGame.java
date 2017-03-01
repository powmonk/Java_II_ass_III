import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TileGame {
	public static void main(String[] args){
		TileFrame frame = new TileFrame();
		frame.setTitle("Tile Game");
		frame.setVisible(true);
	}
	
	static class TileFrame extends JFrame implements ActionListener{
		public TileFrame(){
			this.setSize(new Dimension((1+buttonOn.getIconWidth())*N,(8+buttonOn.getIconHeight())*(N+1)));
			this.setResizable(false);
			
			randomise();  //randomises the numbers in the tiles array			
			addButtons(); //adds N buttons to N panels
			addFooter();  //adds a footer panel 
			
			setLayout(new GridLayout(JPnls.size()+1, 1));
			for(int i=0;i<JPnls.size();i++){
				add(JPnls.get(i)); 
			}
			add(footerPanel);
			footerText.setText("Arrange the numbers into numerical order");
		}
		
		private void addFooter() {
			//This method creates the 2x1 footer panel
			footerPanel = new JPanel();
			footerPanel.setLayout(new GridLayout());
			//Create/add the JTextArea
			footerText = new JTextArea();
			footerText.setEditable(false);
			footerText.setLineWrap(true);
			footerText.setWrapStyleWord(true);
			footerPanel.add(footerText);
			//Create/add the shuffle button
			shuffleButton = new JButton("Shuffle");
			shuffleButton.addActionListener(this);
			footerPanel.add(shuffleButton);
		}

		private void addButtons() {
			//The outer loop creates the rows, the inner loops creates the columns
			for(int i=0;i<N;i++){
				JPnls.add(i, new JPanel());
				JPnls.get(i).setLayout(new GridLayout(1,4));
				JPnls.get(i).setBorder(null);
				//Here the buttons are created by using the inner and outer loop counter for X/Y positions
				for(int j=0; j<N; j++){
					JBtns[i][j] = new JButton();
					JBtns[i][j].addActionListener(this);
					JPnls.get(i).add(JBtns[i][j]);
				}
			}
			setButtons();
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
		
		private boolean isValidMove(Object e) {
			//This method uses the X/Y position of the blank tile to determine the
			//surrounding tiles
			int[] blank = getBlank();
			// This block is a bit dense, but I find it easier to read this way. 
			// The outer if excludes any numbers outside the range of the 2D array to prevent 
			// The inner if checks if the button being pressed is one of the ones adjacent to the blank tile.
			// If the button pressed is one of these it returns true early
			if(blank[0]-1 > -1){ if(JBtns[blank[0]-1][blank[1]] == e){ return true;};}
			if(blank[0]+1 <  N){ if(JBtns[blank[0]+1][blank[1]] == e){ return true;};}
			if(blank[1]-1 > -1){ if(JBtns[blank[0]][blank[1]-1] == e){ return true;};}
			if(blank[1]+1 <  N){ if(JBtns[blank[0]][blank[1]+1] == e){ return true;};}
			return false;
		}
		
		private void swapTiles(ActionEvent e){
			int blank = -1;
			int num = -1;
			
			for(int i=0; i<tiles.length; i++){
				if(tiles[i] == "") blank = i;
				if(tiles[i] == e.getActionCommand()) num = i;
			}
			
			tiles[blank] = e.getActionCommand();
			tiles[num] = "";
			setButtons();
		}
		
		private static boolean winCondition(String[] tiles){
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
				if(i+1 != parsed) return false;
			}
			return true;
		}

		private void setButtons(){
			int count = 0;
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					JBtns[i][j].setIcon(buttonOn);
					JBtns[i][j].setText(tiles[count]);
					JBtns[i][j].setIconTextGap(-55);
					JBtns[i][j].setRolloverEnabled(false);
					JBtns[i][j].setBorderPainted(false);
					JBtns[i][j].setMargin(null);
					count++;
					if(JBtns[i][j].getActionCommand() == "") 
						JBtns[i][j].setIcon(buttonOff);
				}
			}
		}
		
		private int[] getBlank(){
			// This method returns the X/Y location of the blank tile in the 2d array
			// which is then used to determine valid moves
			int[] blankTile = new int[2];
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(JBtns[i][j].getActionCommand() == ""){
						blankTile[0] = i;blankTile[1] =j;
						return blankTile;
					}
				}
			}
			return blankTile;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == shuffleButton){
				footerText.setText("New game!");
				randomise();
				setButtons();
			}else{				
				//Check if  the game has been beaten by comparing the number array to a sequential one
				if(!winCondition(tiles)){
					if(isValidMove(e.getSource())){
						footerText.setText("");
						swapTiles(e);
						if(winCondition(tiles)) footerText.setText("Winner!!");
					}else{
						footerText.setText("You need to pick a tile adjacent to the empty tile");
					}
				}
			}
		}
		
	private Icon buttonOn = new ImageIcon("on.png");
	private Icon buttonOff = new ImageIcon("off.png");
	private static final int N = 4;
	private String[] tiles = new String[N*N];
	private JPanel footerPanel;
	private JButton shuffleButton;
	private List<JPanel> JPnls = new ArrayList<JPanel>();
	private static JButton[][] JBtns = new JButton[N][N];
	private JTextArea footerText;
	}
}