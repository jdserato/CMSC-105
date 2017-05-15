import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Semora on May 12, 2017.
 */
public class ViewPopulation extends JFrame{
    private JPanel panel;
    private JTable tblData;
    private JLabel lTitle;
    private JButton btnContinue;
    private JPanel pnlData;
    private JLabel lSelection;
    private JPanel pnlResult;
    private JTable tblResult;
    private JButton btnQuit;
    private JButton btnStrata;
    private JPanel pnlQuit;
    private ArrayList<String> list, proxy;
    private Sampling technique;
    private int sampleSize;

    public ViewPopulation( ArrayList<String> list,Sampling technique, int sampleSize) {
        this.list = list;
        this.technique = technique;
        this.sampleSize = sampleSize;
        setTitle("Basic Sampling Methods");
        lTitle.setText("The Sampling Frame");
        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(675, 450);
        setVisible(true);
        lSelection.setText("You selected " + technique.toString() + ".");

        pnlResult.setVisible(false);
        if (!(technique instanceof RevisedStratified)) {
            btnStrata.setVisible(false);
        }

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refocus(pnlData, pnlResult);
            }
        });
        btnStrata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnStrata.getText().equals("View Strata Sample")) {
                    btnStrata.setText("View Strata");
                    for (int i = 0; i < tblResult.getRowCount(); i++) {
                        for (int j = 0; j < tblResult.getColumnCount(); j++) {
                            tblResult.setValueAt("", i, j);
                        }
                    }
                    ArrayList<ArrayList<String>> answer = ((RevisedStratified) technique).getAnswer();
                    for (int i = 0, z = 0; i < answer.size(); i++) {
                        tblResult.setValueAt("STRATA " + (i + 1), z, 0);
                        for (int j = 0; j < answer.get(i).size(); j++) {
                            tblResult.setValueAt("Index " + (Integer.parseInt(answer.get(i).get(j)) + 1), z + 1, j);
                            tblResult.setValueAt(proxy.get(Integer.parseInt(answer.get(i).get(j)) * 2), z + 2, j);
                        }
                        z += 3;
                    }
                } else {
                    btnStrata.setText("View Strata Sample");
                    ArrayList<ArrayList<String>> lst = ((RevisedStratified) technique).getStrata();
                    for (int i = 0, z = 0; i < lst.size(); i++) {
                        tblResult.setValueAt("STRATA " + (i + 1), z, 0);
                        for (int j = 0; j < lst.get(i).size(); j++) {
                            tblResult.setValueAt("Index " + (Integer.parseInt(lst.get(i).get(j)) + 1), z + 1, j);
                            tblResult.setValueAt(proxy.get(Integer.parseInt(lst.get(i).get(j)) * 2), z + 2, j);
                        }
                        z += 3;
                    }
                }
            }
        });
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.show();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        tblData = new JTable(new DefaultTableModel(((int) Math.ceil((double) list.size()/10) * 3) - 1, 10));
        for (int ctr = 0, inc = 1; ctr < list.size();) {
            for (int i = 0; i < 10 && ctr < list.size(); i++, ctr++) {
                tblData.setValueAt("Index " + (ctr + 1), inc - 1, i);
                tblData.setValueAt(list.get(ctr), inc, i);
            }
            inc += 3;
        }

        int row, col;
        ArrayList<ArrayList<String>> lst;
        ArrayList<String> samp;
        if (technique instanceof RevisedStratified) {
            lst = ((RevisedStratified) technique).getSample((double) sampleSize);
            row = ((int) Math.ceil((double) lst.size() * 3));
            col = lst.get(0).size();
            for (int i = 1; i < lst.size(); i++) {
                if (col < lst.get(i).size()) {
                    col = lst.get(i).size();
                }
            }
            tblResult = new JTable(new DefaultTableModel(row, col));
            for (int i = 0, z = 0; i < lst.size(); i++) {
                tblResult.setValueAt("STRATA " + (i + 1), z, 0);
                for (int j = 0; j < lst.get(i).size(); j++) {
                    tblResult.setValueAt("Index " + (Integer.parseInt(lst.get(i).get(j)) + 1), z + 1, j);
                    tblResult.setValueAt(list.get(Integer.parseInt(lst.get(i).get(j)) * 2), z + 2, j);
                }
                z += 3;
            }
            proxy = new ArrayList<>();
            proxy.addAll(list);
        } else {
            samp = technique.getSample(sampleSize);
            row = ((int) Math.ceil((double) samp.size()/2/10) * 3) - 1;
            col = 10;
            tblResult = new JTable(new DefaultTableModel(row, col));
            for (int ctr = 0, inc = 1; ctr < samp.size();) {
                for (int i = 0; i < 10 && ctr < samp.size(); i++, ctr += 2) {
                    tblResult.setValueAt("Index " + samp.get(ctr + 1), inc - 1, i);
                    tblResult.setValueAt(samp.get(ctr), inc, i);
                }
                inc += 3;
            }
        }
    }

    private void refocus( JPanel prev,  JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }
}
