package au.com.sommer.common.filter;

/**
 * A compound {@link Filter} that accepts an item if any of its filters accept
 * the item.
 * 
 * If no filters are given, the filter should reject all items.
 * 
 * This filter also directly supports in-memory filtering when all sub-filters
 * do so.
 * 
 * @see And
 */
public final class Or extends AbstractJunctionFilter {
	
	public Or() {
		
	}

    /**
     * 
     * @param filters
     *            filters of which the Or filter will be composed
     */
    public Or(ValueFilter... filters) {
        super(filters);
    }

    public boolean passesFilter(Object value)
            throws UnsupportedFilterException {
        for (ValueFilter filter : getFilters()) {
            if (filter.passesFilter(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a change in the named property may affect the filtering
     * result. If some of the sub-filters are not in-memory filters, true is
     * returned.
     * 
     * By default, all sub-filters are iterated to check if any of them applies.
     * If there are no sub-filters, true is returned as an empty Or rejects all
     * items.
     */
    @Override
    public boolean appliesToValue(Object value) {
        if (getFilters().isEmpty()) {
            // empty Or filters out everything
            return true;
        } else {
            return super.appliesToValue(value);
        }
    }

}
