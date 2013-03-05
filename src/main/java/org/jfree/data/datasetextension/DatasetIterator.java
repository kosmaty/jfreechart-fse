package org.jfree.data.datasetextension;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Base interface for {@link DatasetCursor} based iterators.  
 * 
 * @author zinsmaie
 */
public interface DatasetIterator<E> extends Iterator<E>, Serializable {

	/**
	 * @return the same as {@link Iterator#next()} but already casted
	 */
	public DatasetCursor nextCursor();
	
}
