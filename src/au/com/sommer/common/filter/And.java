package au.com.sommer.common.filter;

/**
 * A compound {@link Filter} that accepts an item if all of its filters accept
 * the item.
 * 
 * If no filters are given, the filter should accept all items.
 * 
 * This filter also directly supports in-memory filtering when all sub-filters
 * do so.
 * 
 * @see Or
 */
public final class And extends AbstractJunctionFilter {

    /**
     * 
     * @param filters
     *            filters of which the And filter will be composed
     */
    public And(ValueFilter... filters) {
        super(filters);
    }

    public boolean passesFilter(Object value)
            throws UnsupportedFilterException {
        for (ValueFilter filter : getFilters()) {
            if (!filter.passesFilter(value)) {
                return false;
            }
        }
        return true;
    }

}
