package de.dbo.samples.gui.swing.treetable.records;

import java.util.List;
import java.util.ArrayList;

public class Path {
	
	private static final char PATH_SEPARATOR_CHAR = '/';
	public static final String PATH_SEPARATOR = String.valueOf(PATH_SEPARATOR_CHAR);

	private List<String> elements = new ArrayList<String>();
	
	private final String root;
	private String canonicalValue;
	private String parentCanonicalValue;
	
	public Path() {
		this(null);
	}
	
	/**
	 * @param path
	 *            e.g /A/B/C with A as a root
	 */
	public Path(final String path) {
		if (!nn(path)) {
			root = null;
			canonicalValue = null;
			parentCanonicalValue = null;
			return;
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
		}
		if (0!=elements.size()) {
			root = elements.get(0);
		} else {
			root = null;
		}
	}
	
	public void addChild(final String child) {
		if (!nn(child)) {
			return;
		}
		final String childTrimmed = child.trim();
		elements.add(childTrimmed);
		
		parentCanonicalValue = canonicalValue;
		canonicalValue = canonicalValue + PATH_SEPARATOR + childTrimmed;
	}
	
	
	public boolean isChildOf(final Path parent) {
		return null!=this.root && null!=parent.root
				&& this.root.equals(parent.root) 
				&& this.canonicalValue.startsWith(parent.canonicalValue)
				&& this.depth() == parent.depth() + 1;
	}
	
	public boolean isParentOf(final Path child) {
		return null!=root && null!=child.root
				&& this.root.equals(child.root) 
				&& child.canonicalValue.startsWith(canonicalValue)
				&& this.depth() == child.depth() - 1;
	}
	
	/**
	 * the same path is not a sibling
	 * @param sibling
	 * @return
	 */
	public boolean isSiblingOf(final Path sibling) {
		return null!=this.parentCanonicalValue && null!=sibling.parentCanonicalValue
				&& this.parentCanonicalValue.equals(sibling.parentCanonicalValue)
				&& !this.canonicalValue.equals(sibling.canonicalValue);
	}
	
	public String root(int depth) {
		if (elements.isEmpty()) {
			return 0==depth? canonicalValue : null;
		}
		if (depth<elements.size()) {
			return elements.get(depth);
		}
		return null;
	}
	
	public boolean rootPath() {
		return null!=root && 1==depth();
	}
	
	public String canonicalValue() {
		return canonicalValue;
	}
	
	public String parentCanonicalValue() {
		return parentCanonicalValue;
	}

	
	public final int depth() {
		if (null!=root)  {
			return elements.size();
		} else {
			return 0;
		}
	}
	
	public final String print() {
		final StringBuffer sb = new StringBuffer();
		sb.append(" Root=" + root);
		sb.append(" Depth=" + depth());
		sb.append(" Canonical=" + canonicalValue());
		sb.append(" ParentCanonical=" + parentCanonicalValue());
		return sb.toString();
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
