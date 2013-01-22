package au.com.sommer.common.filter;

/**
 * Negating filter that accepts the items rejected by another filter.
 * 
 * This filter directly supports in-memory filtering when the negated filter
 * does so.
 */
public final class Not extends ValueFilter {
    private ValueFilter filter;
    
    public void setFilter(ValueFilter filter) {
		this.filter = filter;
	}
    
    public Not() {
    	
    }

	/**
     * Constructs a filter that negates a filter.
     * 
     * @param filter
     *            {@link Filter} to negate, not-null
     */
    public Not(ValueFilter filter) {
        this.filter = filter;
    }

    /**
     * Returns the negated filter.
     * 
     * @return Filter
     */
    public ValueFilter getFilter() {
        return filter;
    }

    public boolean passesFilter(Object value)
            throws UnsupportedOperationException {
        return !filter.passesFilter(value);
    }

    /**
     * Returns true if a change in the named property may affect the filtering
     * result. Return value is the same as {@link #appliesToProperty(Object)}
     * for the negated filter.
     * 
     * @return boolean
     */
    public boolean appliesToValue(Object value) {
        return filter.appliesToValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        return filter.equals(((Not) obj).getFilter());
    }

    @Override
    public int hashCode() {
        return filter.hashCode();
    }

}
