package ij.plugin.filter;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

/**
* @deprecated
* replaced by ij.plugin.Resizer
*/
public class Resizer implements PlugInFilter {
 
	public int setup(String arg, ImagePlus imp) {
		if (imp!=null)
			IJ.run(imp, "Resize...", "");
		return DONE;
	}

	public void run(ImageProcessor ip) {
	}

}
