package de.dbo.samples.gui.swing.treetable.api.records;

/**
 * Reference (basic) implementation of a path.
 * A non-null path is declared in any record.
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public interface Path {
	
	public static final char PATH_SEPARATOR_CHAR = '/';
	public static final String PATH_SEPARATOR = String.valueOf(PATH_SEPARATOR_CHAR);

	public abstract boolean isChildOf(Path parent);

	public abstract boolean isParentOf(Path child);

	/**
	 * the same path is not a sibling
	 * @param sibling
	 * @return
	 */
	public abstract boolean isSiblingOf(Path sibling);

	public abstract String pathElement(int depth);

	/**
	 * string representation of this path
	 * 
	 * @return path as a string
	 */
	public abstract String canonicalValue();

	/**
	 * string representation of the parent path.
	 * It can be null
	 * 
	 * @return parent path as a string
	 */
	public abstract String parentCanonicalValue();

	/**
	 * depth of this path.
	 * Depth is number of path-elements
	 * 
	 * @return depth of this path
	 */
	public abstract int depth();

	/**
	 * pretty-print of this path.
	 * Mostly used for debugging/logging
	 * 
	 * @return readable string
	 */
	public abstract StringBuilder print();

}