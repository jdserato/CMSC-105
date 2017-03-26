package Lab02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel pnlHistogram;
    private JButton btnMenu;
    private ArrayList<String> list;
    DecimalFormat numberFormat;

    Numerical(ArrayList<String> list, String title) {
        this.list = list;
        lTitle.setText(title);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("title");
        setSize(800, 500);
        pnlHistogram.setVisible(false);

        // TODO implementation
        btnViewHist.setEnabled(false);
        btnViewHist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SeratoAndAmora.in.show();
            }
        });
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
        int classWidth = (int) Math.ceil((float) range / (float) k);

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
        String maxD = "#.";
        for (int i = 0; i < maxDecimal; i++) {
            maxD = maxD.concat("0");
        }
        numberFormat = new DecimalFormat(maxD);
        if (maxDecimal == 0) {
            int lowestLimit = min.intValue();
            int highestLimit = min.intValue() + classWidth - 1;
            for (int i = 1; i <= k; i++) {
                tblNumerical.setValueAt(lowestLimit + " - " + highestLimit, i, 0);
                lowestLimit = highestLimit + 1;
                highestLimit = lowestLimit + classWidth - 1;
            }
        } else {
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
                double percent = 0;
                int real;
                for (Number num : val) {
                    real = num.intValue();
                    if (real >= realLowLimit && real <= realHighLimit) {
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
                tblNumerical.setValueAt((Double.parseDouble(min1) + Double.parseDouble(max1)) / 2, i, 2);

                // frequency
                double realLowLimit = Double.parseDouble(min1);
                double realHighLimit = Double.parseDouble(max1);
                int count = 0;
                double percent = 0;
                for (Number num : val) {
                    double real = num.doubleValue();
                    if (real >= realLowLimit && real <= realHighLimit) {
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
