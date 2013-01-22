package au.com.sommer.common.filter;

import java.util.Date;

/**
 * Simple Filter used to filter Date properties
 * 
 * @author Teppo Kurki
 * 
 */
public class DateFilter extends ValueFilter {
    private static final long serialVersionUID = -1568791220144011057L;
    private final DateInterval dateInterval;
    private final Object propertyId;

    public DateFilter(DateInterval dateInterval, Object propertyId) {
        this.dateInterval = dateInterval;
        this.propertyId = propertyId;
    }

    public boolean passesFilter(Object value)
            throws UnsupportedOperationException {
        if (value == null
                || !(value instanceof Date)) {
            return false;
        }
        return dateInterval.isBetween((Date) value);
    }

    public boolean appliesToValue(Object value) {
        if (value == null || this.propertyId == null) {
            return false;
        }
        return value.equals(this.propertyId);
    }
}