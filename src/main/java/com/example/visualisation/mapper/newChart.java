package com.example.visualisation.mapper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JFrame;

import java.util.Objects;

import static com.example.Main.lastSuspicious;
import static com.example.Main.sums;

public class newChart extends JFrame {

    public newChart(String title) {
        super(title);
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createPieChart(
                "C# Students by Array of Suspicious Coincidences",
                dataset,
                true,
                true,
                false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        setContentPane(chartPanel);
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int goodJob = 0;
        int suspiciousJob = 0;
        int nullScores = 0;
        for (int i = 0; i < lastSuspicious.size(); i++) {
            if (Objects.equals(lastSuspicious.get(i), "Нет совпадений")){
                goodJob = goodJob + 1;
            }
            else {
                suspiciousJob = suspiciousJob + 1;
            }
        }

        for (int el : sums) {
            if (el == 0) {
                nullScores = nullScores + 1;
            }
        }

        dataset.setValue(String.format("Подозрительные совпадения с другими у %s студентов",
                suspiciousJob), suspiciousJob);
        dataset.setValue(String.format("Совпадений нет у %s студентов", (goodJob - nullScores)), goodJob - nullScores);
        dataset.setValue(String.format("Нулевые баллы у %s студентов", nullScores), nullScores);
        return dataset;
    }

}
