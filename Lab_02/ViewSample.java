package Lab02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Semora on March 19, 2017
 */
public class ViewSample extends JFrame{
    private JTable tblSample;
    private JPanel panel1;
    private JLabel lTitle;
    private JButton btnViewSummary;
    static ArrayList<String> list;
    private boolean categorical, integer;

    ViewSample(ArrayList<String> list, String title, boolean categorical) {
        this.list = list;
        this.setAlwaysOnTop(true);
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        this.categorical = categorical;
        add(panel1);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(500, 500);


        btnViewSummary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (categorical) {
                    Categorical ctr = new Categorical(list, title);
                } else {
                    Numerical num = new Numerical(list, title);
                }
            }
        });
    }

    private void createUIComponents() {
        tblSample = new JTable();
        int rows = (int) Math.ceil(Math.sqrt(list.size()));
        int cols = rows;
        if (rows * (rows - 1) >= list.size()) {
            cols--;
        }

        tblSample.setModel(new DefaultTableModel(rows, cols));
        for (int i = 0; i < tblSample.getRowCount(); i++) {
            for (int j = 0; j < tblSample.getColumnCount(); j++) {
                if ((i * cols) + j < list.size()) {
                    tblSample.setValueAt(list.get((i * cols) + j), i , j);
                }
            }
        }
        tblSample.setEnabled(false);
    }
}
