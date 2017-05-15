import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Semora on March 21, 2017
 */
public class Categorical extends JFrame{
    private ArrayList<String> list;
    private JTable tblCategory;
    private JPanel panel;
    private JLabel lblTitle;
    private JButton btnViewChart;
    private JPanel pnlChart;
    private JButton btnMenu;
    private ArrayList<String> finalCounts;

    Categorical (ArrayList<String> list, String title) {
        this.list = list;
        lblTitle.setText(title);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(800, 700);
        pnlChart.setVisible(false);

        btnViewChart.addActionListener(e -> {
            if (btnViewChart.getText().equals("View Pie Chart")) {
                DefaultPieDataset pieChartData = new DefaultPieDataset();
                for (int i = 0; i < finalCounts.size(); i = i + 2) {
                    pieChartData.setValue(finalCounts.get(i), ((int) Double.parseDouble(finalCounts.get(i + 1))));
                }

                JFreeChart pieChart = ChartFactory.createPieChart3D(null, pieChartData, true, true, true);
                pieChart.getPlot();

                ChartPanel pieChartPanel = new ChartPanel(pieChart);
                pnlChart.removeAll();
                pnlChart.add(pieChartPanel, BorderLayout.CENTER);
                pnlChart.validate();
                pnlChart.setVisible(true);
                btnViewChart.setText("Hide Pie Chart");
            } else {
                pnlChart.setVisible(false);
                btnViewChart.setText("View Pie Chart");
            }
        });

        btnMenu.addActionListener(e -> {
            dispose();/*
            SeratoAndAmora.in.setEnabled(true);
            SeratoAndAmora.in.setFocusable(true);
            SeratoAndAmora.in.show();*/
            Main.main.show();
        });
    }

    private void createUIComponents() {
        tblCategory = new JTable();
        // Sorting
        ArrayList<String> sorted = new ArrayList<>();
        for (String str : list) {
            if (!sorted.contains(str)) {
                sorted.add(str);
            }
        }
        String tempStr;
        for (int t = 0; t < sorted.size() - 1; t++) {
            for (int i = 0; i < sorted.size() - t - 1; i++) {
                if(sorted.get(i + 1).compareTo(sorted.get(i))<0) {
                    tempStr = sorted.get(i);
                    sorted.set(i, sorted.get(i + 1));
                    sorted.set(i + 1, tempStr);
                   }
            }
        }

        tblCategory.setModel(new DefaultTableModel(sorted.size() + 2, 2));
        tblCategory.setValueAt("VALUE LABELS", 0, 0);
        for(int i = 0; i < sorted.size(); i++) {
            tblCategory.setValueAt(sorted.get(i), i + 1, 0);
        }
        ArrayList<Integer> count = new ArrayList<>(sorted.size());
        for(int i = 0; i < sorted.size(); i++) {
            for (String aList : list) {
                if (sorted.get(i).equals(aList)) {
                    if (count.size() == i) {
                        count.add(1);
                    } else {
                        count.set(i, count.get(i) + 1);
                    }
                }
            }
        }
        ArrayList<String> counter = new ArrayList<>();
        tblCategory.setValueAt("PERCENTAGE", 0, 1);
        double n = list.size();
        for(int i = 0; i < count.size(); i++) {
            double ct = count.get(i);
            double ans = (ct / n) * 100;
            int maxLimit = 5;
            if ((ans + "").length() < 5) {
                maxLimit = (ans + "").length();
            }
            counter.add((ans + "").substring(0, maxLimit));
            tblCategory.setValueAt((ans + "").substring(0, maxLimit).concat("%"), i + 1, 1);
        }
        tblCategory.setValueAt("TOTAL: 100%", tblCategory.getRowCount() - 1, 1);

        finalCounts = new ArrayList<>();
        for(int i = 0; i < sorted.size(); i++) {
            finalCounts.add(sorted.get(i));
            finalCounts.add(count.get(i) + "");
        }
    }
}
