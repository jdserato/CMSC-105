import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
    private JPanel pnlTendency;
    private JLabel lMeasures;
    private JRadioButton rbMean;
    private JRadioButton rbMedian;
    private JRadioButton rbMode;
    private JRadioButton rbAll;
    private JButton btnDone;
    private JPanel pnlResult;
    private JTextArea taResult;
    private JLabel lResult;
    private JButton btnOK;
    private JPanel pnlNext;
    private JButton btnMenu;
    private JButton btnReuse;
    private int intervals;
    private boolean firstIsOpen, lastIsOpen;
    
    private Average[] tendency = new Average[3];
    private double sumLeft = 0, sumRight = 0, n = 0;
    private ArrayList<Integer> freq;
    private ArrayList<Double> midpoints;

    Creating(int intervals, String title, boolean firstIsOpen, boolean lastIsOpen) {
        this.intervals = intervals;
        this.firstIsOpen = firstIsOpen;
        this.lastIsOpen = lastIsOpen;
        this.setAlwaysOnTop(true);
        freq = new ArrayList<>();
        midpoints = new ArrayList<>();
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Descriptive Statistics");
        setSize(650, 500);
        add(panel);
        btnCont.setEnabled(false);
        pnlResult.setVisible(false);
        pnlTendency.setVisible(false);
        pnlNext.setVisible(false);

        btnEdit.addActionListener(e -> {
            if (btnEdit.getText().equals("Save")) {
                try {
                    sumLeft = 0;
                    sumRight = 0;
                    n = 0;

                    double minPrev;
                    int j;
                    if (firstIsOpen) {
                        minPrev = Double.parseDouble(String.valueOf(tblData.getValueAt(1, 1)));
                        j = 2;
                    } else {
                        minPrev = 0;
                        j = 1;
                    }
                    for (; j < intervals + 1; j++) {
                        if (lastIsOpen && j == intervals) {
                            if (minPrev >= Double.parseDouble(String.valueOf(tblData.getValueAt(j, 1)))) {
                                throw new Exception();
                            }
                        } else {
                            if (Double.parseDouble(String.valueOf(tblData.getValueAt(j, 0))) > Double.parseDouble(String.valueOf(tblData.getValueAt(j, 1)))) {
                                throw new Exception();
                            } else if (minPrev >= Double.parseDouble(String.valueOf(tblData.getValueAt(j, 0)))) {
                                throw new Exception();
                            }
                            minPrev = Double.parseDouble(String.valueOf(tblData.getValueAt(j, 1)));
                        }
                    }

                    // CLASS MARKS
                    for (int i = 1; i < tblData.getRowCount() - 1; i++) {
                        if (i == 1 && firstIsOpen) {
                            tblData.setValueAt("-", 1, 3);
                        } else if (i == intervals && lastIsOpen) {
                            tblData.setValueAt("-", intervals, 3);
                        } else {
                            tblData.setValueAt((Double.parseDouble((String) tblData.getValueAt(i, 1)) + Double.parseDouble((String) tblData.getValueAt(i, 0))) / 2, i, 3);
                        }
                    }

                    // f * X(m) & f * X(m) ^ 2
                    for (int i = 1; i < tblData.getRowCount() - 1; i++) {
                        if (i == 1 && firstIsOpen) {
                            tblData.setValueAt("-", 1, 4);
                            tblData.setValueAt("-", 1, 5);
                        } else if (i == intervals && lastIsOpen) {
                            tblData.setValueAt("-", intervals, 4);
                            tblData.setValueAt("-", intervals, 5);
                        } else {
                            sumRight = sumRight + Double.parseDouble(String.valueOf(tblData.getValueAt(i, 2))) * Math.pow(Double.parseDouble(String.valueOf(tblData.getValueAt(i, 3))), 2);
                            tblData.setValueAt(Double.parseDouble(String.valueOf(tblData.getValueAt(i, 2))) * Math.pow(Double.parseDouble(String.valueOf(tblData.getValueAt(i, 3))), 2), i, 5);
                            sumLeft = sumLeft + Double.parseDouble(String.valueOf(tblData.getValueAt(i, 2))) * Double.parseDouble(String.valueOf(tblData.getValueAt(i, 3)));
                            tblData.setValueAt(Double.parseDouble(String.valueOf(tblData.getValueAt(i, 2))) * Double.parseDouble(String.valueOf(tblData.getValueAt(i, 3))), i, 4);
                            n = n + Double.parseDouble(String.valueOf(tblData.getValueAt(i, 2)));
                        }
                    }
                    tblData.setValueAt("Total: " + sumRight, tblData.getRowCount() - 1, 5);
                    tblData.setValueAt("Total: " + sumLeft, tblData.getRowCount() - 1, 4);
                    btnEdit.setText("Edit");
                    tblData.setEnabled(false);
                    btnCont.setEnabled(true);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(panel, "Cannot save.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                btnEdit.setText("Save");
                tblData.setEnabled(true);
                btnCont.setEnabled(false);
            }
        });

        btnCont.addActionListener(e -> {
            refocus(pnlData, pnlTendency);

            // Storing frequencies and midpoints
            for (int i = 1; i < tblData.getRowCount() - 1; i++) {
                if (firstIsOpen && i == 1) {
                    i++;
                } else if (lastIsOpen && i == tblData.getRowCount() - 2) {
                    break;
                } else {
                    freq.add(Integer.parseInt(String.valueOf((tblData.getValueAt(i, 2)))));
                    midpoints.add(Double.parseDouble(String.valueOf(tblData.getValueAt(i, 3))));
                }
            }
        });

        btnDone.addActionListener(e -> {
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
                DecimalFormat format = new DecimalFormat("#.000");

                for (Average ave : tendency) {
                    try {
                        taResult.append(ave.toString() + ": ");

                        if (ave instanceof Median) {
                            taResult.append("Cannot compute.");
                        }
                        else if (firstIsOpen || lastIsOpen) {
                            if (ave instanceof Mean) {
                                taResult.append("Cannot compute.");
                            }
                        }

                        if (ave instanceof Mean && !(firstIsOpen || lastIsOpen)) {
                            Mean m = (Mean) ave;
                            taResult.append("Mean: " + format.format(m.getAverage(midpoints, freq).get(0).getCount()) + " | Variance: " + format.format(m.getVariance(sumLeft, sumRight, ((Double) n).intValue())) + " | Standard Deviation: " + format.format(m.getStandardDev(m.getVariance(sumLeft, sumRight, ((Double) n).intValue()))));
                        }

                        if (ave instanceof Mode) {
                            Mode m = (Mode) ave;
                            ArrayList<Integer> mode = m.getMode(freq);
                            if (mode.size() == intervals) {
                                taResult.append("No modal class | No mode");
                            } else {
                                int ctr = 0;
                                for (int i : mode) {
                                    if (++ctr != mode.size()) {
                                        taResult.append(tblData.getValueAt(i + 1, 0) + " - " + tblData.getValueAt(i + 1, 1) + ", ");
                                    } else {
                                        taResult.append(tblData.getValueAt(i + 1, 0) + " - " + tblData.getValueAt(i + 1, 1) + "");
                                    }
                                }

                                taResult.append(" | ");

                                switch (mode.size()) {
                                    case 1:
                                        taResult.append("Unimodal");
                                        break;
                                    case 2:
                                        taResult.append("Bimodal");
                                        break;
                                    default:
                                        taResult.append("Multimodal");
                                        break;
                                }
                            }
                        }

                        taResult.append("\n");
                    } catch (NullPointerException e1) {
                        // do nothing
                    }
                }
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refocus(pnlResult, pnlNext);
            }
        });


        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();/*
                Semora.in.setFocusable(true);
                Semora.in.setEnabled(true);
                Semora.in.show();*/
                Main.main.show();
            }
        });
        btnReuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regain(pnlData.getComponents());
                regain(pnlNext.getComponents());
                regain(pnlResult.getComponents());
                regain(pnlTendency.getComponents());

                pnlTendency.setVisible(false);
                taResult.setEnabled(true);
                tblData.setEnabled(false);
                taResult.setText("");
                pnlResult.setVisible(false);
                pnlNext.setVisible(false);
                freq.clear();
                midpoints.clear();
                tendency = new Average[3];
            }
        });
    }

    private void createUIComponents() {
        tblData = new JTable(new DefaultTableModel(intervals + 2, 6));
        tblData.setValueAt("Lower Class Limit", 0, 0);
        tblData.setValueAt("Upper Class Limit", 0, 1);
        tblData.setValueAt("Frequency (f)", 0, 2);
        tblData.setValueAt("Midpoint (X(m))", 0, 3);
        tblData.setValueAt("f * X(m)", 0, 4);
        tblData.setValueAt("f * X(m) ^ 2", 0, 5);
        if (firstIsOpen) {
            tblData.setValueAt("<=", 1, 0);
        }
        if (lastIsOpen) {
            tblData.setValueAt(">=", tblData.getRowCount() - 2, 0);
        }
    }

    private void refocus( JPanel prev,  JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }

    private void regain( Component[] components) {
        for (Component c : components) {
            c.setEnabled(true);
        }
    }
}
