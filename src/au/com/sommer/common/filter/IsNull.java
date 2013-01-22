package au.com.sommer.common.filter;

/**
 * Simple container filter checking whether a value is null.
 * 
 * This filter also directly supports in-memory filtering.
 */
public final class IsNull extends ValueFilter {

    public IsNull() {
    	
    }

    public boolean passesFilter(Object value)
            throws UnsupportedOperationException {
        return null == value;
    }

    public boolean appliesToValue(Object propertyId) {
        return true;
    }

}
