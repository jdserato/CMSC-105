package Lab02;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Semora on March 22, 2017.
 */
public class Charts extends JFrame{
    private JPanel pieChartPanel;
    private ArrayList<String> finalCounts;

    Charts(ArrayList<String> finalCounts, String title) {
        add(pieChartPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("title");
        setSize(500, 500);

        this.finalCounts = finalCounts;

        DefaultPieDataset pieChartData = new DefaultPieDataset();
        for (int i = 0; i < finalCounts.size(); i = i + 2){
            pieChartData.setValue(finalCounts.get(i), ((int)Double.parseDouble(finalCounts.get(i+1))));
        }

        JFreeChart pieChart = ChartFactory.createPieChart3D(title, pieChartData, true, true, true);
        PiePlot3D Pie = (PiePlot3D) pieChart.getPlot();

        ChartPanel pieChrtPanel = new ChartPanel(pieChart);
        pieChartPanel.removeAll();
        pieChartPanel.add(pieChrtPanel, BorderLayout.CENTER);
        pieChartPanel.validate();
    }
}
