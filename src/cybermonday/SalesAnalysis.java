package cybermonday;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class SalesAnalysis extends JFrame {

    public SalesAnalysis() {
        setTitle("Sales Analysis");
        setBounds(200, 100, 800, 600);
        setLayout(new BorderLayout());

        // Sample sales data
        Map<String, Integer> salesData = generateSampleSalesData();

        // Create chart panel
        JPanel chartPanel = createChartPanel(salesData);
        add(chartPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private Map<String, Integer> generateSampleSalesData() {
        Map<String, Integer> data = new HashMap<>();
        data.put("Electronics", 120);
        data.put("Fashion", 80);
        data.put("Home Appliances", 60);
        data.put("Books", 90);
        data.put("Toys", 50);
        return data;
    }

    private JPanel createChartPanel(Map<String, Integer> salesData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Populate dataset
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }

        // Create bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Sales Analysis",
                "Category",
                "Sales (Units)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Customize chart
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
        chart.setBackgroundPaint(Color.white);

        // Return chart panel
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        new SalesAnalysis();
    }
}
