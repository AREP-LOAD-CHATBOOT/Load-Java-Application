package graphic;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphicData {
	private ArrayList<Double> data;
	
	public GraphicData(ArrayList<Double> data) {
		this.data = data;
	}
	
	public ChartPanel createGraphic() {
		XYSeries oSeries = new XYSeries("Request Times");
        //Random rand = new Random();
        for (int i = 0; i < data.size(); i++) {
        	oSeries.add(i + 1, data.get(i));        	
            //double value = 0.01 + ( 99.99 - 0.01 ) * rand.nextDouble();
            //oSeries.add(i + 1, value);
        }
        
        XYSeriesCollection oDataSet = new XYSeriesCollection();
        oDataSet.addSeries(oSeries);
        
        JFreeChart oChart = ChartFactory.createXYLineChart("Request Times", "Threads", "Time (s)", oDataSet, PlotOrientation.VERTICAL, true, false, false);
        ChartPanel oPanel = new ChartPanel(oChart);		
        
        return oPanel;
	}	
	
}
