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
    private JPanel pnlNext;
    private JButton btnMenu;
    private JButton btnReuse;
    private ArrayList<String> list;
    private boolean integer;
    private Average[] tendency;
    
    Editing( ArrayList<String> list, String title) {
        this.list = list;
        integer = !list.get(1).contains(".");
        this.setAlwaysOnTop(true);
        setMaximumSize(new Dimension(500, 500));
        lTitle.setText(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Descriptive Statistics");
        setSize(500, 500);
        add(panel);
        pnlTendency.setVisible(false);
        pnlResult.setVisible(false);
        tblPop.setEnabled(false);
        pnlNext.setVisible(false);

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnEdit.getText().equals("Edit")) {
                    tblPop.setEnabled(true);
                    btnEdit.setText("Save");
                    btnCont.setEnabled(false);
                } else {
                    tblPop.setEnabled(false);
                    btnEdit.setText("Edit");
                    btnCont.setEnabled(true);
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
                            if (!(ave instanceof Mode)) {
                                for (Data d : ret) {
                                    if (ave instanceof Mean) {
                                        taResult.append(format.format(Double.parseDouble(d.getName())));
                                        Mean m = (Mean) ave;
                                        Double var = m.getVariance(Double.parseDouble(d.getName()));
                                        taResult.append(" | Variance: " + format.format(var));
                                        Double sd = m.getStandardDev(var);
                                        taResult.append(" | Standard Deviation: " + format.format(sd));
                                    } else {
                                        taResult.append(d.getName() + "");
                                    }
                                }
                            } else {
                                if (ret.size() == list.size()) {
                                    taResult.append("No mode value | No mode");
                                }
                                else {
                                    int ctr = 0;
                                    for (Data i : ret) {
                                        if (++ctr != ret.size()) {
                                            taResult.append(i.getName() + ", ");
                                        } else {
                                            taResult.append(i.getName() + "");
                                        }
                                    }

                                    taResult.append(" | ");

                                    switch (ret.size()) {
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
                regain(pnlTable.getComponents());
                regain(pnlNext.getComponents());
                regain(pnlResult.getComponents());
                regain(pnlTendency.getComponents());

                pnlTendency.setVisible(false);
                taResult.setEnabled(true);
                tblPop.setEnabled(false);
                taResult.setText("");
                pnlResult.setVisible(false);
                pnlNext.setVisible(false);
                tendency = new Average[3];
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
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do  nothing
            }
            if (rbAll.isSelected() || rbMean.isSelected() || rbMedian.isSelected() || rbMode.isSelected()) {
                btnDone.setEnabled(true);
                break;
            }
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
