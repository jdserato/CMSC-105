package Lab03;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Semora on April 23, 2017.
 */
public class Editing extends JFrame{
    private JPanel panel;
    private JTable tblPop;
    private JLabel lTitle;
    private JPanel panel1;
    private JButton editButton;
    private JButton continueButton;
    private ArrayList<String> list;
    
    Editing(ArrayList<String> list, String title) {
        this.list = list;
        this.setAlwaysOnTop(true);
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(500, 500);
        add(panel);
    }

    private void createUIComponents() {
        tblPop = new JTable();
        int rows = (int) Math.ceil(Math.sqrt(list.size()));
        int cols = rows;
        if (rows * (rows - 1) >= list.size()) {
            cols--;
        }

        tblPop.setModel(new DefaultTableModel(rows, cols));
        for (int i = 0; i < tblPop.getRowCount(); i++) {
            for (int j = 0; j < tblPop.getColumnCount(); j++) {
                if ((i * cols) + j < list.size()) {
                    tblPop.setValueAt(list.get((i * cols) + j), i , j);
                }
            }
        }
        tblPop.setEnabled(true);
    }
}
