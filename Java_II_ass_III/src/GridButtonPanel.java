/*
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridButtonPanel {

    private JButton getGridButton(int x, int y) {
        int index = x * N + y;
        return list.get(index);
    }

    private JButton createGridButton(final int row, final int col) {
        final JButton b = new JButton("x" + row + ",y" + col);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton gb = GridButtonPanel.this.getGridButton(row, col);
                System.out.println("x" + row + ",y" + col
                    + " " + (b == gb)
                    + " " + (b.equals(gb)));
            }
        });
        return b;
    }

    private JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(N, N));
        for (int i = 0; i < N * N; i++) {
            int row = i / N;
            int col = i % N;
            JButton gb = createGridButton(row, col);
            list.add(gb);
            p.add(gb);
        }
        return p;
    }

    private void display() {
        JFrame f = new JFrame("GridButton");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(createGridPanel());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GridButtonPanel().display();
            }
        });
    }
    

    private static final int N = 4;
    private final List<JButton> list = new ArrayList<JButton>();

} */