package Lab03;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;

/**
 * Created by Amora & Serato on April 21, 2017.
 */
public class MainFrame extends JFrame {
    JPanel panel;
    private JTextArea taMenuHeader;
    private JRadioButton rbGrouped;
    private JRadioButton rbUngrouped;
    private JRadioButton rbQuit;
    private JButton btnOK1;
    private JTextField tfTitle;
    private JPanel panel2;
    private JLabel lTitle;
    private JPanel panel1;
    private JButton btnOK2;
    private JTextField tfSize;
    private JLabel lSize;
    private JButton btnOK3;
    private JPanel panel3;
    private JTextArea taData;
    private JPanel panel4;
    private JLabel lEnterData;
    private JLabel lNum;
    private JButton btnDone;
    private JPanel panel5;
    private JPanel pnlUngrouped;
    private JPanel pnlGrouped;
    private JTextField tfIntervals;
    private JCheckBox cbFirstOpen;
    private JCheckBox cblastOpen;
    private JButton btnOK4;
    private JPanel panel6;
    private JLabel lInterval;
    private JTable tblUngroup;
    private int index = 1;
    private int size;
    private ArrayList<String> list;
    private boolean integer;
    private boolean error = false;


    MainFrame() {
        index = 1;
        size = 0;
        list = new ArrayList<>();
        integer = false;
        error = false;
        add(panel);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        for (Component c : panel.getComponents()) {
            if (c instanceof Panel) {
                c.setVisible(false);
            }
        }
        panel1.setVisible(true);
        setTitle(taMenuHeader.getText());
        setVisible(true);
        setSize(625, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnOK1.setEnabled(false);
        askNature();

        btnOK1.addActionListener(e -> {
            if (rbQuit.isSelected()) {
                int i = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    dispose();
                } else {
                    askNature();
                }
            } else {
                refocus(panel1, panel2);
            }
        });

        btnOK2.setEnabled(false);
        askTitle();

        btnOK2.addActionListener(e -> {
            System.out.println("TRIGG");
            if (tfTitle.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rbGrouped.isSelected()) {
                    refocus(panel2, pnlGrouped);
                    pnlGrouped.setVisible(true);
                } else {
                    refocus(panel2, pnlUngrouped);
                }
            }
        });

        tfTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (tfTitle.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (rbGrouped.isSelected()) {
                            refocus(panel2, pnlGrouped);
                            pnlGrouped.setVisible(true);
                        } else {
                            refocus(panel2, pnlUngrouped);
                            pnlUngrouped.setVisible(true);
                            panel3.setVisible(true);
                        }
                        System.out.println("ACTIVATED");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        if (rbUngrouped.isSelected()) {
            askSize();
        } else {
            askInterval();
        }

        if (rbUngrouped.isSelected()) { // Ungrouped Data
            panel3.setVisible(true);
            btnOK3.setEnabled(false);
            askSize();

            btnOK3.addActionListener(e -> {
                boolean error = false;
                try {
                    size = Integer.parseInt(tfSize.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if (error || size <= 1) {
                    JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    tfSize.setText("");
                } else {
                    refocus(panel3, panel4);
                }
            });

            tfSize.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == VK_ENTER) {
                        boolean error = false;
                        int size = 0;
                        try {
                            size = Integer.parseInt(tfSize.getText());
                        } catch (NumberFormatException e1) {
                            error = true;
                        }
                        if (error || size <= 1) {
                            JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                            tfSize.setText("");
                        } else {
                            refocus(panel3, panel4);
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            list = new ArrayList<>();

            taData.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == VK_ENTER) {
                        if (taData.getText().equals("") || taData.getText().equals("\n") || taData.getText().equals("\t")) {
                            JOptionPane.showMessageDialog(panel, "Please enter a datum.", "Error", JOptionPane.ERROR_MESSAGE);
                            taData.setText("");
                        } else {
                            String str = taData.getText();
                            if (str.charAt(0) == '\n' || str.charAt(0) == '\t') {
                                str = str.substring(1, str.length());
                            }
                            try {
                                if (index == 1) {
                                    try {
                                        Double.parseDouble(str);
                                        integer = !str.contains(".");
                                        error = false;
                                    } catch (NumberFormatException e2) {
                                        JOptionPane.showMessageDialog(panel, "Please enter a numerical value.", "Error", JOptionPane.ERROR_MESSAGE);
                                        error = true;
                                    }
                                }
                                if (!error) {
                                    if (integer) {
                                        int d = Integer.parseInt(str);
                                        list.add(d + "");
                                    } else {
                                        double d = Double.parseDouble(str);
                                        list.add(d + "");
                                    }
                                    askData();
                                }
                            } catch (NumberFormatException e2) {
                                if (integer) {
                                    JOptionPane.showMessageDialog(panel, "Please input an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Please input a floating number.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            taData.setText("");
                        }
                    } else if (e.getKeyCode() == VK_TAB) {
                        JOptionPane.showMessageDialog(panel, "Tab character is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                        taData.setText("");
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            btnDone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Component c : getComponents()) {
                        c.setVisible(false);
                    }
                    panel1.setVisible(true);
                    btnOK1.setEnabled(false);
                    panel1.setEnabled(true);
                    panel2.setVisible(false);
                    panel3.setVisible(false);
                    panel4.setVisible(false);
                    panel5.setVisible(false);
                    panel1.setEnabled(false);
                    index = 1;
                    lNum.setText("1.");
                    size = 0;
                    integer = false;
                    error = false;

                    new Editing(list, tfTitle.getText());
                    list = new ArrayList<>();

                    for (Component c : panel.getComponents()) {
                        if (c instanceof JPanel) {
                            for (Component c1 : ((JPanel) c).getComponents()) {
                                c1.setEnabled(true);
                            }
                        }
                    }
                    tfSize.setText("");
                    tfTitle.setText("");
                    taData.setText("");
                    Semora.in.setEnabled(false);
                }
            });
        } else { // Grouped Data

        }
    }

    private void askInterval() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!tfIntervals.getText().equals("")) {
                btnOK4.setEnabled(true);
                break;
            }
        }
    }

    private void askSize() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!tfSize.getText().equals("")) {
                btnOK3.setEnabled(true);
                break;
            }
        }
    }

    private void askNature() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (rbGrouped.isSelected() || rbUngrouped.isSelected() || rbQuit.isSelected()) {
                btnOK1.setEnabled(true);
                break;
            }
        }
    }

    private void askTitle() {
        panel1.setEnabled(true);
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!tfTitle.getText().equals("")) {
                btnOK2.setEnabled(true);
                break;
            }
        }
    }

    private void refocus(JPanel prev, JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }

    private void askData() {
        if (++index > Integer.parseInt(tfSize.getText())) {
            refocus(panel4, panel5);
        } else {
            lNum.setText(index + ". ");
        }
    }

    private void createUIComponents() {
        tblUngroup = new JTable();

        int rows = (int) Math.ceil(Math.sqrt(list.size()));
        int cols = rows;
        if (rows * (rows - 1) >= list.size()) {
            cols--;
        }
        tblUngroup.setModel(new DefaultTableModel(rows, cols));
        for (int i = 0; i < tblUngroup.getRowCount(); i++) {
            for (int j = 0; j < tblUngroup.getColumnCount(); j++) {
                if ((i * cols) + j < list.size()) {
                    tblUngroup.setValueAt(list.get((i * cols) + j), i , j);
                }
            }
        }
    }
}