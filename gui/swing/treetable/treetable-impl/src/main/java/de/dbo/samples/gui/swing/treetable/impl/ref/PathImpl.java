package de.dbo.samples.gui.swing.treetable.impl.ref;

import de.dbo.samples.gui.swing.treetable.api.records.PathAbsraction;
import de.dbo.samples.gui.swing.treetable.api.records.PathException;

/**
 * 
 * Reference implementation of the record-path
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class PathImpl extends PathAbsraction {
	
	/**
	 * creates the path-instance
	 * @param path
	 *            e.g /A/B/C with A as a root
	 *            Any other structure is considered as single root having no parent
	 *            
	 *  @throws PathException if the path is null or empty string
	 */
	PathImpl(final String path) {
		super(path);
	}
	
	/**
	 * pretty-print of the path.
	 * Shows some extra attributes
	 */
	@Override
	public final StringBuilder print() {
		final StringBuilder sb = new StringBuilder();
		sb.append(" Depth=" + depth());
		sb.append(" Canonical=" + canonicalValue());
		sb.append(" ParentCanonical=" + parentCanonicalValue());
		return sb;
	}
	
}
