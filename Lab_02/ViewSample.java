package Lab02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Semora on March 19, 2017.
 */
public class ViewSample extends JFrame{
    private JTable tblSample;
    private JPanel panel1;
    private JLabel lTitle;
    private JButton btnOK;
    private ArrayList<String> list;

    ViewSample(ArrayList<String> list) {
        this.list = list;
        add(panel1);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("title");
        setSize(500, 500);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        tblSample = new JTable();
        int rows = (int) Math.ceil(Math.sqrt(list.size()));
        System.out.println(rows);
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
