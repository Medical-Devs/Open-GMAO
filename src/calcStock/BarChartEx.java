package calcStock;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import EditMultipleObjects.WindowSize;

public class BarChartEx extends JFrame 
{

	private static final long serialVersionUID = -7842613966114320616L;
	private int width = 1360, height = 720;
	
	public BarChartEx() 
	{

        initUI();
    }

    private void initUI() 
    {

        CategoryDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Pareto");
        setBounds(WindowSize.widthScreen / 2 - width / 2 , WindowSize.heightScreen / 2 - height / 2, width, height);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset() 
    {

        var dataset = new DefaultCategoryDataset();
        dataset.setValue(46, "Dispositif Médical", "Respirateur d'anésthésie");
        dataset.setValue(38, "Dispositif Médical", "Moniteur multiparamétrique");
        dataset.setValue(29, "Dispositif Médical", "Pousse seringue");
        dataset.setValue(22, "Dispositif Médical", "Bistouris électrique");
        dataset.setValue(13, "Dispositif Médical", "Radio mobile");
        dataset.setValue(11, "Dispositif Médical", "Table Opératoire");

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) 
    {

        JFreeChart barChart = ChartFactory.createBarChart(
                "MTBF",
                "Les machines",
                "Durée de travail",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    public static void main(String[] args) 
    {

        EventQueue.invokeLater(() -> {

            var ex = new BarChartEx();
            ex.setVisible(true);
        });
    }
}