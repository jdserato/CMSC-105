package Lab03;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Semora on May 01, 2017.
 */
public class Creating extends JFrame{
    private JTable tblData;
    private JLabel lTitle;
    private JPanel panel;
    private JButton btnEdit;
    private JPanel pnlData;
    private JButton btnCont;
    private int intervals;
    private boolean firstIsOpen, lastIsOpen;

    Creating(int intervals, String title, boolean firstIsOpen, boolean lastIsOpen) {
        this.intervals = intervals;
        this.firstIsOpen = firstIsOpen;
        this.lastIsOpen = lastIsOpen;
        this.setAlwaysOnTop(true);
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(650, 500);
        add(panel);
        btnCont.setEnabled(false);

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnEdit.getText().equals("Save")) {
                    // TODO error checking

                    btnEdit.setText("Edit");
                }
            }
        });
    }

    private void createUIComponents() {
        tblData = new JTable(new DefaultTableModel(intervals + 1, 6));
        tblData.setValueAt("Lower Class Limit", 0, 0);
        tblData.setValueAt("Upper Class Limit", 0, 1);
        tblData.setValueAt("Frequency", 0, 2);
        tblData.setValueAt("Midpoint", 0, 3);
        tblData.setValueAt("f * X(m)", 0, 4);
        tblData.setValueAt("f * X(m) ^ 2", 0, 5);
        if (firstIsOpen) {
            tblData.setValueAt("<=", 1, 0);
        }
        if (lastIsOpen) {
            tblData.setValueAt(">=", tblData.getRowCount() - 1, 0);
        }
    }
}
