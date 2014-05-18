package ij.plugin;
import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import java.awt.geom.*;

/** This plugin implements the Edit/Selection/Rotate command. */
public class RoiRotator implements PlugIn {
	private static double defaultAngle = 15;

	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
		Roi roi = imp.getRoi();
		if (roi==null) {
			IJ.error("Rotate", "This command requires a selection");
			return;
		}
		double angle = showDialog(defaultAngle);
		if (Double.isNaN(angle))
			return;
		if (!IJ.macroRunning())
			defaultAngle = angle;
		if (roi instanceof ImageRoi) {
			((ImageRoi)roi).rotate(angle);
			imp.draw();
			return;
		}
		Roi roi2 = rotate(roi, angle);
		if (roi2==null)
			return;
		Undo.setup(Undo.ROI, imp);
		roi = (Roi)roi.clone();
		imp.setRoi(roi2);
		Roi.previousRoi = roi;
	}
	
	public double showDialog(double angle) {
		GenericDialog gd = new GenericDialog("Rotate Selection");
		int decimalPlaces = 0;
		if ((int)angle!=angle)
			decimalPlaces = 2;
		gd.addNumericField("Angle:", angle, decimalPlaces, 3, "degrees");
		gd.setInsets(5, 0, 0);
		gd.addMessage("Enter negative angle to \nrotate counter-clockwise", null, Color.darkGray);
		gd.showDialog();
		if (gd.wasCanceled())
			return Double.NaN;
		else
			return gd.getNextNumber();
	}
	
	public static Roi rotate(Roi roi, double angle) {
		double theta = -angle*Math.PI/180.0;
		Rectangle r = roi.getBounds();
		double xcenter = r.x+r.width/2.0;
		double ycenter = r.y+r.height/2.0;
		if (roi instanceof ShapeRoi)
			return rotateShape((ShapeRoi)roi, -theta, xcenter, ycenter);
		FloatPolygon poly = roi.getFloatPolygon();
		int type = roi.getType();
		if (type==Roi.LINE) {
			Line line = (Line)roi;
			double x1=line.x1d;
			double y1=line.y1d;
			double x2=line.x2d;
			double y2=line.y2d;
			poly = new FloatPolygon();
			poly.addPoint(x1, y1);
			poly.addPoint(x2, y2);
			xcenter = x1 + (x2-x1)/2.0;
			ycenter = y1 + (y2-y1)/2.0;
		}
		for (int i=0; i<poly.npoints; i++) {
			double dx = poly.xpoints[i]-xcenter;
			double dy = ycenter-poly.ypoints[i];
			double radius = Math.sqrt(dx*dx+dy*dy);
			double a = Math.atan2(dy, dx);
			poly.xpoints[i] = (float)(xcenter + radius*Math.cos(a+theta));
			poly.ypoints[i] = (float)(ycenter - radius*Math.sin(a+theta));
		}
		Roi roi2 = null;
		if (type==Roi.LINE)
			roi2 = new Line(poly.xpoints[0], poly.ypoints[0], poly.xpoints[1], poly.ypoints[1]);
		else if (type==Roi.POINT)
			roi2 = new PointRoi(poly.xpoints, poly.ypoints,poly.npoints);
		else {
			if (type==Roi.RECTANGLE)
				type = Roi.POLYGON;
			if (type==Roi.RECTANGLE && poly.npoints>4) // rounded rectangle
				type = Roi.FREEROI;
			if (type==Roi.OVAL||type==Roi.TRACED_ROI)
				type = Roi.FREEROI;
			roi2 = new PolygonRoi(poly.xpoints, poly.ypoints,poly.npoints, type);
		}
		roi2.setStrokeColor(roi.getStrokeColor());
		if (roi.getStroke()!=null)
			roi2.setStroke(roi.getStroke());
		return roi2;
	}
	
	private static Roi rotateShape(ShapeRoi roi, double angle, double xcenter, double ycenter) {
		Shape shape = roi.getShape();
		AffineTransform at = new AffineTransform();
		at.rotate(angle, xcenter, ycenter);
		Rectangle r = roi.getBounds();
		at.translate(r.x, r.y);
		Shape shape2 = at.createTransformedShape(shape);
		return new ShapeRoi(shape2);
	}
	
}
