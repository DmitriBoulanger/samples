package ij.plugin;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.measure.Calibration;
import ij.measure.Measurements;
import ij.measure.ResultsTable;
import ij.plugin.filter.Analyzer;
import ij.process.ImageStatistics;
import ij.process.StackStatistics;

import java.awt.Rectangle;

/** This plugin implements the Image/Stacks/Statistics command. */
public class Stack_Statistics implements PlugIn {
	
	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
    	double histMax = imp.getBitDepth()==8||imp.getBitDepth()==24?256.0:0.0;
		int measurements = Analyzer.getMeasurements();
		Analyzer.setMeasurements(measurements | Measurements.LIMIT);
		ImageStatistics stats = new StackStatistics(imp, 256, 0.0, histMax);
		Analyzer.setMeasurements(measurements);
		ResultsTable rt = Analyzer.getResultsTable();
		rt.incrementCounter();
		Roi roi = imp.getRoi();
		if (roi!=null && !roi.isArea()) {
			imp.deleteRoi();
			roi = null;
		}
		double stackVoxels = 0.0;
		double images = imp.getStackSize();
		if (roi==null)
			stackVoxels = imp.getWidth()*imp.getHeight()*images;
		else if (roi.getType()==Roi.RECTANGLE) {
			Rectangle r = roi.getBounds();
			stackVoxels = r.width*r.height*images;
		} else {
			Analyzer.setMeasurements(measurements & ~Measurements.LIMIT);
			ImageStatistics stats2 = new StackStatistics(imp, 256, 0.0, histMax);
			Analyzer.setMeasurements(measurements);
			stackVoxels = stats2.longPixelCount;
		}
		Calibration cal = imp.getCalibration();
		String units = cal.getUnits();	
		double scale = cal.pixelWidth*cal.pixelHeight*cal.pixelDepth;
		rt.addValue("Voxels", stats.longPixelCount);
		if (scale!=1.0)
		rt.addValue("Volume("+units+"^3)", stats.longPixelCount*scale);
		rt.addValue("%Volume", stats.longPixelCount*100.0/stackVoxels);
		rt.addValue("Mean", stats.mean);
		rt.addValue("StdDev", stats.stdDev);
		rt.addValue("Min", stats.min);
		rt.addValue("Max", stats.max);
		rt.addValue("Mode", stats.dmode);
		rt.show("Results");
	}
	
}
