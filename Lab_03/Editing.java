package Lab03;

import Lab03.AverageGenerator.Average;
import Lab03.AverageGenerator.Mean;
import Lab03.AverageGenerator.Median;
import Lab03.AverageGenerator.Mode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Semora on April 23, 2017.
 */
public class Editing extends JFrame{
    private JPanel panel;
    private JTable tblPop;
    private JLabel lTitle;
    private JPanel pnlTable;
    private JButton btnEdit;
    private JButton btnCont;
    private JLabel lMeasures;
    private JRadioButton rbMean;
    private JRadioButton rbMedian;
    private JRadioButton rbMode;
    private JRadioButton rbAll;
    private JButton btnDone;
    private JPanel pnlTendency;
    private JPanel pnlResult;
    private JTextArea taResult;
    private JLabel lResult;
    private JButton btnOK;
    private ArrayList<String> list;
    private boolean integer;
    private Average[] tendency;
    
    Editing(ArrayList<String> list, String title) {
        this.list = list;
        integer = !list.get(1).contains(".");
        this.setAlwaysOnTop(true);
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(500, 500);
        add(panel);
        pnlTendency.setVisible(false);
        pnlResult.setVisible(false);
        tblPop.setEnabled(false);

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnEdit.getText().equals("Edit")) {
                    tblPop.setEnabled(true);
                    btnEdit.setText("Save");
                } else {
                    tblPop.setEnabled(false);
                    btnEdit.setText("Edit");
                    list.clear();
                    for(int i = 0; i < tblPop.getRowCount(); i++) {
                        for(int j = 0; j < tblPop.getColumnCount(); j++) {
                            try {
                                if (integer) {
                                    int num = Integer.parseInt(tblPop.getValueAt(i, j).toString());
                                    list.add(num + "");
                                } else {
                                    double num = Double.parseDouble(tblPop.getValueAt(i, j).toString());
                                    list.add(num + "");
                                }
                            } catch (NullPointerException e1) {
                                // do nothing
                            } catch (NumberFormatException e2) {
                                JOptionPane.showMessageDialog(panel, tblPop.getValueAt(i, j) + " is not a numeric value. Removing entry...");
                                tblPop.setValueAt("", i, j);
                            }
                        }
                    }
                    System.out.println(list);
                }
            }
        });

        btnCont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refocus(pnlTable, pnlTendency);
            }
        });

        tendency = new Average[3];

        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbMode.isSelected() || rbMedian.isSelected() || rbMean.isSelected() || rbAll.isSelected()) {
                    if (rbMean.isSelected()) {
                        tendency[0] = new Mean();
                    } else if (rbMedian.isSelected()) {
                        tendency[0] = new Median();
                    } else if (rbMode.isSelected()) {
                        tendency[0] = new Mode();
                    } else {
                        tendency[0] = new Mean();
                        tendency[1] = new Median();
                        tendency[2] = new Mode();
                    }
                    refocus(pnlTendency, pnlResult);
                    ArrayList<Double> data = new ArrayList<>();
                    DecimalFormat format = new DecimalFormat("#.000");
                    for (String str : list) {
                        data.add(Double.parseDouble(str));
                    }
                    for (Average ave : tendency) {
                        try {
                            taResult.append(ave.toString() + ": ");
                            ArrayList<Data> ret = ave.getAverage(data);
                            for (Data d : ret) {
                                if (ave instanceof Mean) {
                                    taResult.append(format.format(Double.parseDouble(d.getName())));
                                } else {
                                    taResult.append(d.getName() + "");
                                }
                                if (ave instanceof Mode) {
                                    taResult.append(" | ");
                                    switch ((int) d.getCount()) {
                                        case 0:
                                            taResult.append("No mode value");
                                            break;
                                        case 1:
                                            taResult.append("Unimodal");
                                            break;
                                        case 2:
                                            taResult.append("Bimodal");
                                        case 3:
                                            taResult.append("Multimodal");
                                    }
                                } else if (ave instanceof Mean) {
                                    Mean m = (Mean) ave;
                                    Double var = m.getVariance(Double.parseDouble(d.getName()));
                                    taResult.append(" | Variance: " + format.format(var));
                                    Double sd = m.getStandardDev(var);
                                    taResult.append(" | Standard Deviation: " + format.format(sd));
                                }
                            }

                            taResult.append("\n");
                        } catch (NullPointerException e1) {
                            // do nothing
                        }
                    }
                }
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Semora.in.setEnabled(true);
                Semora.in.setFocusable(true);
                Semora.in.show();
            }
        });
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

    private void askTendency() {
        btnDone.setEnabled(false);
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do  nothing
            }
            if (rbAll.isSelected() || rbMean.isSelected() || rbMedian.isSelected() || rbMode.isSelected()) {
                btnDone.setEnabled(true);
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
}
