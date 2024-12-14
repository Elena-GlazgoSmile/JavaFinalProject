package com.example.visualisation.mapper;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

import static com.example.Main.students;
import static com.example.Main.sums;

public class StudentRealScores extends JFrame {

    public StudentRealScores(String title) {
        super(title);

        DefaultCategoryDataset dataset = createDataset();

        JFreeChart barChart = ChartFactory.createBarChart(
                "Реальные баллы у студентов",
                "Имя/фамилия студента",
                "Всего баллов у студента",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < sums.size(); i++) {
            dataset.addValue(i, students.get(i), sums.get(i));
        }
        return dataset;
    }


}

