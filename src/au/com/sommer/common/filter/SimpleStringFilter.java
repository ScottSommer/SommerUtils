package au.com.sommer.common.filter;

/**
 * Simple string filter for matching items that start with or contain a
 * specified string. The matching can be case-sensitive or case-insensitive.
 * 
 * This filter also directly supports in-memory filtering. When performing
 * in-memory filtering, values of other types are converted using toString(),
 * but other (lazy container) implementations do not need to perform such
 * conversions and might not support values of different types.
 * 
 * Note that this filter is modeled after the pre-6.6 filtering mechanisms, and
 * might not be very efficient e.g. for database filtering.
 * 
 * TODO this might still change
 */
public final class SimpleStringFilter extends ValueFilter {

    String filterString;
    boolean ignoreCase;
    boolean onlyMatchPrefix;
    
    public SimpleStringFilter() {
    	
    }

    public SimpleStringFilter(String filterString,
            boolean ignoreCase, boolean onlyMatchPrefix) {
        this.filterString = ignoreCase ? filterString.toLowerCase()
                : filterString;
        this.ignoreCase = ignoreCase;
        this.onlyMatchPrefix = onlyMatchPrefix;
    }

    public boolean passesFilter(Object objValue) {
        String value = ignoreCase ? objValue.toString().toLowerCase() : objValue
                .toString();
        if (onlyMatchPrefix) {
            if (!value.startsWith(filterString)) {
                return false;
            }
        } else {
            if (!value.contains(filterString)) {
                return false;
            }
        }
        return true;
    }

    public boolean appliesToValue(Object value) {
        return value != null && value.getClass().isAssignableFrom(String.class);
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filterString == null) ? 0 : filterString.hashCode());
		result = prime * result + (ignoreCase ? 1231 : 1237);
		result = prime * result + (onlyMatchPrefix ? 1231 : 1237);
		return result;
	}

    @Override
    public boolean equals(Object obj) {

        // Only ones of the objects of the same class can be equal
        if (!(obj instanceof SimpleStringFilter)) {
            return false;
        }
        final SimpleStringFilter o = (SimpleStringFilter) obj;

        if (filterString != o.filterString && o.filterString != null
                && !o.filterString.equals(filterString)) {
            return false;
        }
        if (ignoreCase != o.ignoreCase) {
            return false;
        }
        if (onlyMatchPrefix != o.onlyMatchPrefix) {
            return false;
        }

        return true;
    }

    /**
     * Returns the filter string.
     * 
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     * 
     * @return filter string given to the constructor
     */
    public String getFilterString() {
        return filterString;
    }

    /**
     * Returns whether the filter is case-insensitive or case-sensitive.
     * 
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     * 
     * @return true if performing case-insensitive filtering, false for
     *         case-sensitive
     */
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    /**
     * Returns true if the filter only applies to the beginning of the value
     * string, false for any location in the value.
     * 
     * Note: this method is intended only for implementations of lazy string
     * filters and may change in the future.
     * 
     * @return true if checking for matches at the beginning of the value only,
     *         false if matching any part of value
     */
    public boolean isOnlyMatchPrefix() {
        return onlyMatchPrefix;
    }

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public void setOnlyMatchPrefix(boolean onlyMatchPrefix) {
		this.onlyMatchPrefix = onlyMatchPrefix;
	}
    
    
}
