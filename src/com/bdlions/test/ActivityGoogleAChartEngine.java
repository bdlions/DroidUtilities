package com.bdlions.test;

import java.text.DecimalFormat;

import com.bdlions.achartengine.ChartFactory;
import com.bdlions.achartengine.GraphicalView;
import com.bdlions.achartengine.model.CategorySeries;
import com.bdlions.achartengine.model.SeriesSelection;
import com.bdlions.achartengine.renderer.DefaultRenderer;
import com.bdlions.achartengine.renderer.SimpleSeriesRenderer;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityGoogleAChartEngine extends Activity {
	private GraphicalView mChart;
	private String[] code;
	final DefaultRenderer defaultRenderer  = new DefaultRenderer(); 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_google_achartengine);
        // Ploting the chart
        openChart();        
    }
    
    private void openChart(){
    	
    	code = new String[] {"Fitness", "Health", "Sports"};
    	double[] distribution = { 33.3, 33.3, 33.3 } ;
    	int[] colors = { 0xFFDC3812, 0xFF3266CC, 0xFFFE9900};
    	
    	// Instantiating CategorySeries to plot Pie Chart    	
    	CategorySeries distributionSeries = new CategorySeries("");
    	for(int i=0 ;i < distribution.length;i++){
    		// Adding a slice with its values and name to the Pie Chart
    		distributionSeries.add(code[i], distribution[i]);
    	}   
    	
    	// Instantiating a renderer for the Pie Chart
    	for(int i = 0 ;i<distribution.length;i++){ 
    		// Instantiating a render for the slice
    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
    		seriesRenderer.setColor(colors[i]);
    		// Adding the renderer of a slice to the renderer of the pie chart
    		defaultRenderer.addSeriesRenderer(seriesRenderer);
    	}

    	defaultRenderer.setPanEnabled(false);
    	defaultRenderer.setShowLabels(true);
    	defaultRenderer.setFitLegend(true);
    	defaultRenderer.setInScroll(true);
    	defaultRenderer.setLegendTextSize(15);
    	
    	// Getting a reference to view group linear layout chart_container
    	LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart_containe);
    	LinearLayout.LayoutParams params = new  LinearLayout.LayoutParams(300,300);
    	chartContainer.setLayoutParams(params);
    	
    	// Getting PieChartView to add to the custom layout
    	mChart = ChartFactory.getPieChartView(getBaseContext(), distributionSeries, defaultRenderer);
    	
    	defaultRenderer.setClickEnabled(true);//
        defaultRenderer.setSelectableBuffer(10);
        mChart.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {

        		SeriesSelection seriesSelection = mChart.getCurrentSeriesAndPoint();
        		if (seriesSelection != null) {
        			
        			// Getting the name of the clicked slice
        			int seriesIndex = seriesSelection.getPointIndex();
        			String selectedSeries="";
        			selectedSeries = code[seriesIndex];  
        			ActivityGoogleAChartEngine.this.defaultRenderer.getSeriesRendererAt(seriesIndex).setHighlighted(true);
        	        mChart.repaint();
                    
        			// Getting the value of the clicked slice
        			double value = seriesSelection.getXValue();
        			DecimalFormat dFormat = new DecimalFormat("#.#");
        			 
        			// Displaying the message
        			Toast.makeText(
        					getBaseContext(),
        					selectedSeries + " : "  + Double.valueOf(dFormat.format(value)) + " % " ,
        					Toast.LENGTH_SHORT).show();
        		}
        }
        });
        // Adding the pie chart to the custom layout
    	chartContainer.addView(mChart);
    	
    }

    
}