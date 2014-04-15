package de.dbo.samples.gui.swing.treetable.records;

import de.dbo.samples.gui.swing.treetable.records.api.Path;
import de.dbo.samples.gui.swing.treetable.records.api.PathException;

import java.util.List;
import java.util.ArrayList;

public class PathImpl implements Path {
	
	private List<String> elements = new ArrayList<String>();
	
	private String canonicalValue;
	private String parentCanonicalValue;
	
	/**
	 * @param path
	 *            e.g /A/B/C with A as a root or something as root
	 */
	public PathImpl(final String path) {
		if (!nn(path)) {
			throw new PathException("Null or empty path is not allowed: " + path);
		}
		
		final String pathTrimmed = path.trim();
		if (PATH_SEPARATOR_CHAR == pathTrimmed.charAt(0)) {
			final StringBuffer sb = new StringBuffer();
			for (final String element : path.trim().split(PATH_SEPARATOR)) {
				if (null != element && 0 != element.trim().length()) {
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
	
	@Override
	public String pathElement(int depth) {
		if (elements.isEmpty()) {
			return 0==depth? canonicalValue : null;
		}
		if (depth<elements.size()) {
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
		return elements.size()-1;
	}
	
	@Override
	public final StringBuilder print() {
		final StringBuilder sb = new StringBuilder();
		sb.append(" Depth=" + depth());
		sb.append(" Canonical=" + canonicalValue());
		sb.append(" ParentCanonical=" + parentCanonicalValue());
		return sb;
	}

	private static final boolean nn(final String x) {
		return null!=x && 0!=x.trim().length();
	}
	
	private static final String parentCanonicalValue(final String canonicalValue) {
		final int idx = canonicalValue.lastIndexOf(PATH_SEPARATOR);
		if (0==idx) {
			return null;
		} else {
			return canonicalValue.substring(0,idx);
		}
	}
	
	
}
