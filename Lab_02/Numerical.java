package Lab02;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Semora on March 22, 2017
 */
public class Numerical extends JFrame{
    private JPanel panel;
    private JTable tblNumerical;
    private JLabel lTitle;
    private JButton btnViewHist;
    private JPanel pnlChart;
    private JButton btnMenu;
    private JButton btnFirstCL;
    private JButton btnLastCL;
    private ArrayList<String> list;
    private String actualFirst[] = new String[3];
    private String actualLast[] = new String[3];
    private final double[] margin = {0.0, 0.0, -1.8, -2.700, -3.60, -4.53, -5.35, -6.2, -7.2, -8.2, -9.1};

    Numerical(ArrayList<String> list, String title) {
        this.list = list;
        lTitle.setText(title);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presenting and Summarizing Data");
        setSize(800, 700);
        pnlChart.setVisible(false);

        btnViewHist.addActionListener(e -> {
            if (btnViewHist.getText().equals("View Histogram")) {
                // USING BAR CHART
                JFreeChart barChart = ChartFactory.createBarChart(null, "Midpoint", "Frequency", createDataset());
                CategoryPlot plot = barChart.getCategoryPlot();
                plot.setDomainGridlinesVisible(true);


                plot.getDomainAxis().setCategoryMargin(0.0);
                ((BarRenderer) plot.getRenderer()).setItemMargin(margin[tblNumerical.getRowCount() - 3]);
                ChartPanel chartPanel = new ChartPanel(barChart);
                barChart.removeLegend();
                pnlChart.removeAll();
                pnlChart.add(chartPanel, BorderLayout.CENTER);
                pnlChart.validate();
                pnlChart.setSize(450, 500);
                pnlChart.setVisible(true);
                btnViewHist.setText("Hide Histogram");
            } else {
                pnlChart.setVisible(false);
                btnViewHist.setText("View Histogram");
            }
        });

        btnMenu.addActionListener(e -> {
            dispose();
            SeratoAndAmora.in.setEnabled(true);
            SeratoAndAmora.in.setFocusable(true);
            SeratoAndAmora.in.show();
        });

        btnFirstCL.addActionListener(e -> {
            if (btnFirstCL.getText().equals("Collapse First Class Limit")) {
                actualFirst[0] = (String) tblNumerical.getValueAt(1, 0);
                actualFirst[1] = (String) tblNumerical.getValueAt(1, 1);
                actualFirst[2] = tblNumerical.getValueAt(1, 2).toString();
                String convert[] = {"", "", ""};
                for (int i = actualFirst[0].length() - 1; i >= 0; i--) {
                    if (actualFirst[0].charAt(i) != ' ') {
                        convert[0] = actualFirst[0].charAt(i) + convert[0];
                    } else {
                        break;
                    }
                }
                for (int i = actualFirst[1].length() - 1; i >= 0; i--) {
                    if (actualFirst[1].charAt(i) != ' ') {
                        convert[1] = actualFirst[1].charAt(i) + convert[1];
                    } else {
                        break;
                    }
                }
                tblNumerical.setValueAt("<= " + convert[0], 1, 0);
                tblNumerical.setValueAt("<= " + convert[1], 1, 1);
                tblNumerical.setValueAt("-", 1, 2);
                btnFirstCL.setText("Undo Collapse First Class Limit");
            } else {
                tblNumerical.setValueAt(actualFirst[0], 1, 0);
                tblNumerical.setValueAt(actualFirst[1], 1, 1);
                tblNumerical.setValueAt(actualFirst[2], 1, 2);
                btnFirstCL.setText("Collapse First Class Limit");
            }
        });

        btnLastCL.addActionListener(e -> {
            if (btnLastCL.getText().equals("Collapse Last Class Limit")) {
                actualLast[0] = (String) tblNumerical.getValueAt(tblNumerical.getRowCount() - 2, 0);
                actualLast[1] = (String) tblNumerical.getValueAt(tblNumerical.getRowCount() - 2, 1);
                if ((tblNumerical.getValueAt(tblNumerical.getRowCount() - 2, 2).toString()) != null)
                    actualLast[2] = tblNumerical.getValueAt(tblNumerical.getRowCount() - 2, 2).toString();
                String convert[] = {"", "", ""};
                for (int i = 0; i < actualLast[0].length(); i++) {
                    if (actualLast[0].charAt(i) != ' ') {
                        convert[0] = convert[0].concat(actualLast[0].charAt(i) + "");
                    } else {
                        break;
                    }
                }
                for (int i = 0; i < actualLast[1].length(); i++) {
                    if (actualLast[1].charAt(i) != ' ') {
                        convert[1] = convert[1].concat(actualLast[1].charAt(i) + "");
                    } else {
                        break;
                    }
                }
                tblNumerical.setValueAt(">= " + convert[0], tblNumerical.getRowCount() - 2, 0);
                tblNumerical.setValueAt(">= " + convert[1], tblNumerical.getRowCount() - 2, 1);
                tblNumerical.setValueAt("-", tblNumerical.getRowCount() - 2, 2);
                btnLastCL.setText("Undo Collapse Last Class Limit");
            } else {
                tblNumerical.setValueAt(actualLast[0], tblNumerical.getRowCount() - 2, 0);
                tblNumerical.setValueAt(actualLast[1], tblNumerical.getRowCount() - 2, 1);
                tblNumerical.setValueAt(actualLast[2], tblNumerical.getRowCount() - 2, 2);
                btnLastCL.setText("Collapse Last Class Limit");
            }
        });
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 1; i < tblNumerical.getRowCount() - 1; i++) {
            if (tblNumerical.getValueAt(i, 2).equals("-")) {
                if (i == 1) {
                    dataset.addValue((int) tblNumerical.getValueAt(i, 3), actualFirst[2], actualFirst[2]);
                } else {
                    dataset.addValue((int) tblNumerical.getValueAt(i, 3), actualLast[2], actualLast[2]);
                }
            } else {
                dataset.addValue((int) tblNumerical.getValueAt(i, 3), tblNumerical.getValueAt(i, 2).toString(), tblNumerical.getValueAt(i, 2).toString());
            }
        }
        return dataset;
    }

    private void createUIComponents() {
        tblNumerical = new JTable();
        // classification
        int maxDecimal = 0;
        ArrayList<Number> val;
        list = ViewSample.list;
        if (list.get(0).contains(".")) {
            val = new ArrayList<>();
            for(String str : list) {
                val.add(Double.parseDouble(str));
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '.') {
                        if (str.length() - i > maxDecimal) {
                            maxDecimal = str.length() - i - 1;
                        }
                    }
                }
            }
            for(int z = 0; z < list.size(); z++) {
                String str = list.get(z);
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '.') {
                        int difference = maxDecimal - (str.length() - i);
                        for (int j = 0; j <= difference; j++) {
                            list.set(z, str.concat("0"));
                        }
                    }
                }
            }
        } else {
            val = new ArrayList<>();
            for (String str : list) {
                val.add(Integer.parseInt(str));
            }
        }

        Number min = val.get(0);
        Number max = val.get(0);
        for (Number num : val) {
            if (num.floatValue() < min.floatValue()) {
                min = num;
            }
            if (num.floatValue() > max.floatValue()) {
                max = num;
            }
        }

        int range = (int) Math.ceil(max.floatValue() - min.floatValue());
        int k = (int) Math.ceil(1 + (3.322 * Math.log10(list.size()))); // Sturge's Rule
        float classWidth;
        String maxD = "#.";
        for (int i = 0; i < maxDecimal; i++) {
            maxD = maxD.concat("0");
        }
        DecimalFormat numberFormat = new DecimalFormat(maxD);
        if (maxDecimal == 0) {
            classWidth = (int) Math.ceil((float) range / (float) k);
        } else {
            classWidth = (float) range / (float) k;
            String str = numberFormat.format(classWidth);
            classWidth = Float.parseFloat(str);
        }

        tblNumerical.setModel(new DefaultTableModel(k + 2, 7));

        tblNumerical.setValueAt("CLASS LIMIT", 0, 0);
        tblNumerical.setValueAt("TRUE CLASS LIMIT", 0, 1);
        tblNumerical.setValueAt("MIDPOINTS", 0, 2);
        tblNumerical.setValueAt("FREQUENCY", 0, 3);
        tblNumerical.setValueAt("%", 0, 4);
        tblNumerical.setValueAt("CF", 0, 5);
        tblNumerical.setValueAt("C%", 0, 6);
        tblNumerical.setValueAt("n = " + list.size(), k + 1, 3);
        tblNumerical.setValueAt("TOTAL = 100%", k + 1, 4);

        if (maxDecimal == 0) {
            int classes = (int) classWidth;
            int lowestLimit = min.intValue();
            int highestLimit;
            if (classWidth != 0) {
                highestLimit = min.intValue() + classes - 1;
            } else {
                highestLimit = min.intValue();
            }

            for (int i = 1; i <= k; i++) {
                tblNumerical.setValueAt(lowestLimit + " - " + highestLimit, i, 0);
                lowestLimit = highestLimit + 1;
                if (classWidth != 0) {
                    highestLimit = lowestLimit + classes - 1;
                } else {
                    highestLimit = lowestLimit;
                }
            }
        } else {
            System.out.println(list);
            String minimum = list.get(0);
            String maximum = minimum;
            for (String str : list) {
                if (Double.parseDouble(minimum) > Double.parseDouble(str)) {
                    minimum = str;
                }
                if (Double.parseDouble(maximum) < Double.parseDouble(str)) {
                    maximum = str;
                }
            }

            String lowestLimit = minimum;
            String highestLimit = (Double.parseDouble(minimum) + classWidth) + "";
            for (int i = 0; i < highestLimit.length(); i++) {
                if (highestLimit.charAt(i) == '.') {
                    int dif = maxDecimal - (highestLimit.length() - i);
                    for (int j = 0; j <= dif; j++) {
                        highestLimit = highestLimit.concat("0");
                    }
                }
            }
            for (int i = 1; i <= k; i++) {
                tblNumerical.setValueAt(numberFormat.format(Double.parseDouble(lowestLimit)) + " - " + numberFormat.format(Double.parseDouble(highestLimit)), i, 0);
                String pastHigh = highestLimit;
                highestLimit = ((Double.parseDouble(highestLimit) + classWidth) + "");
                lowestLimit = pastHigh.substring(0, pastHigh.length() - 1).concat("1");
                highestLimit = highestLimit.substring(0, highestLimit.length()). concat("0");
            }
        }

        // true CL & midpoints
        int cumulativeCount = 0;
        double cumulativePercent = 0;
        if (maxDecimal == 0) {
            for (int i = 1; i <= k; i++) {
                String values = (String) tblNumerical.getValueAt(i, 0);
                String min1 = "", max1 = "";
                int j;
                for(j = 0; values.charAt(j) != ' '; j++) {
                    min1 = min1.concat(values.charAt(j) + "");
                }
                for(j = j + 3; j < values.length(); j++) {
                    max1 = max1.concat(values.charAt(j) + "");
                }
                tblNumerical.setValueAt(((Double.parseDouble(min1) - 0.5) + " - " + (Double.parseDouble(max1) + 0.5)), i, 1);

                // midpoint
                tblNumerical.setValueAt((Integer.parseInt(min1) + Integer.parseInt(max1)) / 2, i, 2);

                // frequency
                int realLowLimit = Integer.parseInt(min1);
                int realHighLimit = Integer.parseInt(max1);
                int count = 0;
                double percent;
                int real;
                for (Number num : val) {
                    real = num.intValue();
                    if (real >= realLowLimit && real < realHighLimit) {
                        count++;
                        cumulativeCount++;
                    }
                }
                percent = ((double) count / list.size()) * 100;
                cumulativePercent += percent;

                DecimalFormat intForm = new DecimalFormat("#.00");
                tblNumerical.setValueAt(count, i, 3);
                tblNumerical.setValueAt(intForm.format(percent) + "%", i, 4);
                tblNumerical.setValueAt(cumulativeCount, i, 5);
                tblNumerical.setValueAt(intForm.format(cumulativePercent) + "%", i, 6);
            }
        } else {
            maxD = maxD.concat("0");
            numberFormat = new DecimalFormat(maxD);
            double target = 5 * Math.pow(10, ((maxDecimal + 1) * -1));
            for (int i = 1; i <= k; i++) {
                String values = (String) tblNumerical.getValueAt(i, 0);
                String min1 = "", max1 = "";
                int j;
                for(j = 0; values.charAt(j) != ' '; j++) {
                    min1 = min1.concat(values.charAt(j) + "");
                }
                for(j = j + 3; j < values.length(); j++) {
                    max1 = max1.concat(values.charAt(j) + "");
                }
                tblNumerical.setValueAt((numberFormat.format(Double.parseDouble(min1) - target) + " - " + numberFormat.format(Double.parseDouble(max1.concat("5")))), i, 1);

                // midpoint
                tblNumerical.setValueAt(numberFormat.format((Double.parseDouble(min1) + Double.parseDouble(max1)) / 2), i, 2);

                // frequency
                double realLowLimit = Double.parseDouble(min1);
                double realHighLimit = Double.parseDouble(max1);
                int count = 0;
                double percent;
                for (Number num : val) {
                    double real = num.doubleValue();
                    if (real >= realLowLimit && real < realHighLimit || (realHighLimit == max.doubleValue() && real == realHighLimit)) {
                        cumulativeCount++;
                        count++;
                    }
                }
                percent = ((double) count / list.size()) * 100;
                cumulativePercent += percent;

                tblNumerical.setValueAt(count, i, 3);
                tblNumerical.setValueAt(numberFormat.format(percent) + "%", i, 4);
                tblNumerical.setValueAt(cumulativeCount, i, 5);
                tblNumerical.setValueAt(numberFormat.format(cumulativePercent) + "%", i, 6);
            }
        }
    }
}
