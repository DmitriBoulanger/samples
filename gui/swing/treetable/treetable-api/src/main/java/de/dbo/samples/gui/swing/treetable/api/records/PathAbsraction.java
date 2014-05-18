package de.dbo.samples.gui.swing.treetable.api.records;

import static  de.dbo.samples.gui.swing.treetable.api.records.Tools.nn;
import java.util.List;
import java.util.ArrayList;

/**
 * Reference (basic) implementation of the paths used in data-records.
 * Canonical path is a string of the form /A/B/C.../Z
 * with path-elements as /A, /B, /C, ... /Z.
 * The names are A, B, C, ... Z
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public abstract class PathAbsraction implements Path {
	
	/*
	 * names of the path-elements
	 */
	private List<String> elements = new ArrayList<String>();
	
	/*
	 * Any path has non-null canonical value
	 */
	private String canonicalValue;
	
	/*
	 * Any path can have non-null parent canonical value
	 */
	private String parentCanonicalValue;
	
	/**
	 * creates an 
	 * @param path path-string,  e.g /A/B/C with A as a root
	 *            Any other structure is considered an immediate
	 *            child of the tree-root.
	 *            
	 *  @throws PathException if the path is null or empty string
	 */
	protected PathAbsraction(final String path) {
		if (!nn(path)) {
			throw new PathException("Null or empty path is not allowed: " + path);
		}
		
		final String pathTrimmed = path.trim();
		if (PATH_SEPARATOR_CHAR == pathTrimmed.charAt(0)) {
			final StringBuffer sb = new StringBuffer();
			for (final String element : path.trim().split(PATH_SEPARATOR)) {
				if (nn(element) ){
					final String elementTrimmed = element.trim();
					elements.add(elementTrimmed);
					sb.append(PATH_SEPARATOR);
					sb.append(elementTrimmed);
				}
			}
			canonicalValue = sb.toString();
			parentCanonicalValue = parentCanonicalValue(canonicalValue);
		} else {
			canonicalValue = pathTrimmed;
			parentCanonicalValue = null;
			elements.add(pathTrimmed);
		}
	}
	
	@Override
	public boolean isChildOf(final Path parent) {
		return null!=this.pathElement(0) && null!=parent.pathElement(0)
				&& this.pathElement(0).equals(parent.pathElement(0)) 
				&& this.canonicalValue.startsWith(parent.canonicalValue())
				&& this.depth() == parent.depth() + 1;
	}
	
	@Override
	public boolean isParentOf(final Path child) {
		return this.pathElement(0).equals(child.pathElement(0)) 
				&& child.canonicalValue().startsWith(canonicalValue)
				&& this.depth() == child.depth() - 1;
	}
	
	@Override
	public boolean isSiblingOf(final Path sibling) {
		return null!=this.parentCanonicalValue && null!=sibling.parentCanonicalValue()
				&& this.parentCanonicalValue.equals(sibling.parentCanonicalValue())
				&& !this.canonicalValue.equals(sibling.canonicalValue());
	}
	
	/**
	 * name of the path element at the specified depth
	 */
	@Override
	public String pathElement(final int depth) {
		if (elements.isEmpty()) {
			return 0==depth? canonicalValue : null;
		}
		if (depth < elements.size()) {
			return elements.get(depth);
		}
		return null;
	}
	
	@Override
	public String canonicalValue() {
		return canonicalValue;
	}
	
	@Override
	public String parentCanonicalValue() {
		return parentCanonicalValue;
	}

	@Override
	public final int depth() {
		return elements.size() - 1;
	}
	
	// PRIVATE HELPERS
	
	
	
	private static final String parentCanonicalValue(final String canonicalValue) {
		final int idx = canonicalValue.lastIndexOf(PATH_SEPARATOR);
		if (0==idx) {
			return null;
		} else {
			return canonicalValue.substring(0,idx);
		}
	}
}
