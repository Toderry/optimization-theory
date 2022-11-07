package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class DrawGraphs extends JFrame {
    private final String title;
    private double[] arrayOfX;
    private double[] arrayOfY;
    private XYSeriesCollection dataset;


    public double[] getArrayOfX() {
        return arrayOfX;
    }

    public double[] getArrayOfY() {
        return arrayOfY;
    }

    public DrawGraphs(String title) {
        this.title = title;
        dataset = new XYSeriesCollection();
    }

    @Override
    public String getTitle() {
        return title;
    }


    public void initUI() {

        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public XYDataset updateDataset(String key, double[] arrayOfX, double[] arrayOfY) {

        XYSeries series = new XYSeries(key);
        int i = 0;
        for(;i < arrayOfX.length;i++){
            series.add(arrayOfX[i],arrayOfY[i]);
        }
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                getTitle(),
                "-lg(E)",
                "N",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(getTitle(),
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

}