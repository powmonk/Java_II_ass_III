import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
			for(int i=0;i<4;i++){
				JPnls.add(i, new JPanel());
				JPnls.get(i).setLayout(new GridLayout(1,4));
				
				for(int j=0; j<4; j++){
					JBtns[i][j] = new JToggleButton();
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
			
			for(int i=0;i<tiles.length;i++){
				if(tiles[i] == e.getActionCommand().toString() && e.getActionCommand() != " "){
					curr = i;
					footerText.setText("");
				}
			}

			if(prev < 0 && tiles[curr] != ""){
				clearToggle();
				footerText.setText("Start by selecting the empty tile");
				prev = -1;
				curr = -1;
			}
			
			if(prev > -1 && curr > -1){
				temp = tiles[prev];
				tiles[prev] = tiles[curr];
				tiles[curr] = temp;
				setButtons();
				prev = -1;
				curr = -1;
				clearToggle();
			}
			
			
		}

		private void clearToggle() {
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					JBtns[i][j].setSelected(false);
				}
			}
		}

		private void checkButton(ActionEvent e) {
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					e.getActionCommand();

				}
			}
			
		}
		
		private static boolean winCondition(){
			for(int i=0; i<tiles.length-1; i++){
				if(Integer.parseInt(tiles[i]) != i+1){
					return false;
				}
			}
			return true;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == shuffleButton){
				curr = -1;
				footerText.setText(" ");
				randomise();
				setButtons();
				clearToggle();
			}else{
//				footerText.setText(e.toString());
				swapNumbers(e);
				
				checkButton(e);
				
				if(winCondition()){
					footerText.setText("WIN!!!");
				}
			}
			
		}
		

	private static final int N = 4;
	private static String[] tiles = new String[N*N];	
	private JPanel footerPanel;
	private JButton shuffleButton;
	private List<JPanel>  JPnls = new ArrayList<JPanel>();
	private JToggleButton[][] JBtns = new JToggleButton[N][N];
//	private List<JButton> JBtns = new ArrayList<JButton>();
	private JTextArea footerText;
	private int prev = -1, curr = -1;
	private String temp;
	}
}