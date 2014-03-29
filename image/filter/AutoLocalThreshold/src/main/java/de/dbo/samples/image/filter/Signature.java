package de.dbo.samples.image.filter;


import java.awt.Image;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;


import ij.Undo;
import ij.gui.NewImage;
import ij.plugin.filter.RankFilters;
import ij.process.Blitter;
import ij.process.ByteProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Signature {
	 protected static final Logger log = LoggerFactory.getLogger(Signature.class);
	
	/**
	 * Bernsen recommends WIN_SIZE = 31 and CONTRAST_THRESHOLD = 15.
	 * 
	 * 1) Bernsen J. (1986) "Dynamic Thresholding of Grey-Level Images"  
	 *    Proc. of the 8th Int. Conf. on Pattern Recognition, pp. 1251-1255
	 * 2) Sezgin M. and Sankur B. (2004) "Survey over Image Thresholding  Techniques and Quantitative Performance Evaluation" 
	 *    Journal of Electronic Imaging, 13(1): 146-165 
	 *    http://citeseer.ist.psu.edu/sezgin04survey.html
	 *    Ported from E Celebi's fourier_0.8 routines
	 *    This version uses a circular local window, instead of a rectagular one
	 * 
	 * @param imagePlus
	 * @param radius
	 * @param par1_contrast_threshold
	 * @param par2
	 * @param doIwhite
	 */
	 void Bernsen(ImagePlus imp, int radius,  double par1, double par2, boolean doIwhite ) {
			// Bernsen recommends WIN_SIZE = 31 and CONTRAST_THRESHOLD = 15.
			//  1) Bernsen J. (1986) "Dynamic Thresholding of Grey-Level Images" 
			//    Proc. of the 8th Int. Conf. on Pattern Recognition, pp. 1251-1255
			//  2) Sezgin M. and Sankur B. (2004) "Survey over Image Thresholding 
			//   Techniques and Quantitative Performance Evaluation" Journal of 
			//   Electronic Imaging, 13(1): 146-165 
			//  http://citeseer.ist.psu.edu/sezgin04survey.html
			// Ported to ImageJ plugin from E Celebi's fourier_0.8 routines
			// This version uses a circular local window, instead of a rectagular one
			ImagePlus Maximp, Minimp;
			ImageProcessor ip=imp.getProcessor(), ipMax, ipMin;
			int contrast_threshold=15;
			int local_contrast;
			int mid_gray;
			byte object;
			byte backg;
			int temp;

			if (par1!=0) {
				log.info("Bernsen: changed contrast_threshold from :"+ contrast_threshold + "  to:" + par1);
				contrast_threshold= (int) par1;
			}

			if (doIwhite){
				object =  (byte) 0xff;
				backg =   (byte) 0;
			}
			else {
				object =  (byte) 0;
				backg =  (byte) 0xff;
			}

			Maximp=duplicateImage(ip);
			ipMax=Maximp.getProcessor();
			final RankFilters rf = new RankFilters();
			rf.rank(ipMax, radius, RankFilters.MAX);// Maximum
			//Maximp.show();
			Minimp=duplicateImage(ip);
			ipMin=Minimp.getProcessor();
			rf.rank(ipMin, radius, RankFilters.MIN); //Minimum
			//Minimp.show();
			byte[] pixels = (byte [])ip.getPixels();
			byte[] max = (byte [])ipMax.getPixels();
			byte[] min = (byte [])ipMin.getPixels();

			for (int i=0; i<pixels.length; i++) {
				local_contrast = (int)((max[i]&0xff) -(min[i]&0xff));
				mid_gray =(int) ((min[i]&0xff) + (max[i]&0xff) )/ 2;
				temp=(int) (pixels[i] & 0x0000ff);
				if ( local_contrast < contrast_threshold )
					pixels[i] = ( mid_gray >= 128 ) ? object :  backg;  //Low contrast region
				else
					pixels[i] = (temp >= mid_gray ) ? object : backg;
			}    
			//imp.updateAndDraw();
			return;
		}
	
	/**
	 * Sauvola recommends K_VALUE = 0.5 and R_VALUE = 128.
	 * Sauvola J. and Pietaksinen M. (2000) "Adaptive Document Image Binarization"
	 * Pattern Recognition, 33(2): 225-236
	 * http://www.ee.oulu.fi/mvg/publications/show_pdf.php?ID=24
	 * Ported from E Celebi's fourier_0.8 routines
	 * 
	 * @param imp
	 * @param radius
	 * @param par1
	 * @param par2
	 * @param doIwhite
	 */
	
	 void Sauvola(ImagePlus imp, int radius,  double par1, double par2, boolean doIwhite) {
			// Sauvola recommends K_VALUE = 0.5 and R_VALUE = 128.
			// This is a modification of Niblack's thresholding method.
			// Sauvola J. and Pietaksinen M. (2000) "Adaptive Document Image Binarization"
			// Pattern Recognition, 33(2): 225-236
			// http://www.ee.oulu.fi/mvg/publications/show_pdf.php?ID=24
			// Ported to ImageJ plugin from E Celebi's fourier_0.8 routines
			// This version uses a circular local window, instead of a rectagular one

			ImagePlus Meanimp, Varimp;
			ImageProcessor ip=imp.getProcessor(), ipMean, ipVar;
			double k_value = 0.5;
			double r_value = 128;
			byte object;
			byte backg;

			if (par1!=0) {
				log.info("Sauvola: changed k_value from :"+ k_value + "  to:" + par1);
				k_value= par1;
			}

			if (par2!=0) {
				log.info("Sauvola: changed r_value from :"+r_value + "  to:" + par2);
				r_value= par2;
			}

			if (doIwhite){
				object =  (byte) 0xff;
				backg =   (byte) 0;
			}
			else {
				object =  (byte) 0;
				backg =  (byte) 0xff;
			}

			Meanimp=duplicateImage(ip);
			ImageConverter ic = new ImageConverter(Meanimp);
			ic.convertToGray32();

			ipMean=Meanimp.getProcessor();
			RankFilters rf=new RankFilters();
			rf.rank(ipMean, radius, RankFilters.MEAN);// Mean
			//Meanimp.show();
			Varimp=duplicateImage(ip);
			ic = new ImageConverter(Varimp);
			ic.convertToGray32();
			ipVar=Varimp.getProcessor();
			rf.rank(ipVar, radius, RankFilters.VARIANCE); //Variance
			//Varimp.show();
			byte[] pixels = (byte []) ip.getPixels();
			float[] mean = (float []) ipMean.getPixels();
			float[] var = (float []) ipVar.getPixels();

			for (int i=0; i<pixels.length; i++) 
				pixels[i] = ( (int)(pixels[i] &0xff) > (int)( mean[i] * (1.0+ k_value *(( Math.sqrt ( var[i] )/r_value)-1.0)))) ? object : backg;
			//imp.updateAndDraw();
			return;
		}
	 
	    /** 
	     * duplicate and scale the given image.
	     * 
		 * @return an Object[] array with the name and the scaled ImagePlus.
		 * 
		 * Does NOT show the new, image; just returns it. 
		 */
		 public Object[] exec(final Image image, 
				 final String myMethod, int radius,  double par1, double par2, boolean doIwhite ) {

			  
			// 0 - Check validity of parameters
			if (null == image) {
				return null;
			}
			
			final ImagePlus imagePlus =  new ImagePlus();
			imagePlus.setImage(image);
			log.info("imagePlus.getStackSize = "+ imagePlus.getStackSize());
			
			final ImageProcessor imagePlusProcessor = imagePlus.getProcessor();
			int xe = imagePlusProcessor.getWidth();
			int ye = imagePlusProcessor.getHeight();
			
			log.info("Thresholding...");
			long startTime = System.currentTimeMillis();
			
			// 1 Do it
			if (1==imagePlus.getStackSize()) {
				    imagePlusProcessor.snapshot();
				    Undo.setup(Undo.FILTER, imagePlus);
			}
			
			// Apply the selected algorithm
			// ============================
			
			if(myMethod.equals("Bernsen")){
				Bernsen(imagePlus, radius, par1, par2, doIwhite);
			}
//			else if(myMethod.equals("Contrast")){
//				Contrast(imp, radius, par1, par2, doIwhite);
//			}
//			else if(myMethod.equals("Mean")){
//				Mean(imp, radius, par1, par2, doIwhite);
//			}
//			else if(myMethod.equals("Median")){
//				Median(imp, radius, par1, par2, doIwhite);
//			}
//			else if(myMethod.equals("MidGrey")){
//				MidGrey(imp, radius, par1, par2, doIwhite);
//			}
//			else if(myMethod.equals("Niblack")){
//				Niblack (imp, radius, par1, par2, doIwhite); 
//			}
//			else if(myMethod.equals("Otsu")){
//				Otsu(imp, radius, par1, par2, doIwhite);
//			}
//			else if(myMethod.equals("Phansalkar")){
//				Phansalkar(imp, radius, par1, par2, doIwhite);
//			}
			else if(myMethod.equals("Sauvola")){
				Sauvola(imagePlus, radius, par1, par2, doIwhite);
			}
			
			imagePlus.updateAndDraw();
			imagePlus.getProcessor().setThreshold(255, 255, ImageProcessor.NO_LUT_UPDATE);
			
			// 2 - Return the threshold and the image
			log.info("\nDone " + (System.currentTimeMillis() - startTime) / 1000.0);
			return new Object[] {imagePlus};
		}
	
	
	 private ImagePlus duplicateImage(ImageProcessor iProcessor){
			int w=iProcessor.getWidth();
			int h=iProcessor.getHeight();
			ImagePlus iPlus= NewImage.createByteImage("Image", w, h, 1, NewImage.FILL_BLACK);
			ImageProcessor imageProcessor=iPlus.getProcessor();
			imageProcessor.copyBits(iProcessor, 0,0, Blitter.COPY);
			return iPlus;
		} 
	 

}
