package com.example.visualisation.mapper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JFrame;
import java.util.Objects;

import static com.example.Main.*;

public class ChartOfStudents extends JFrame {

    public ChartOfStudents(String title) {
        super(title);
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createPieChart(
                "Retake Groups or Basic Groups",
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
        int isInRetake = 0;
        int baseGroups = 0;

        for (String el : isInRe) {
            if (el.equals("Есть")) {
                isInRetake = isInRetake + 1;
            }
            else {
                baseGroups = baseGroups + 1;
            }
        }

        dataset.setValue(String.format("К группам пересдачи относятся %s студентов",
                isInRetake), isInRetake);
        dataset.setValue(String.format("Базовые группы у %s студентов", baseGroups), baseGroups);
        return dataset;
    }
}
