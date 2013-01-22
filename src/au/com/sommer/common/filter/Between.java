package au.com.sommer.common.filter;

public class Between extends ValueFilter {

    private final Object propertyId;
    private final Comparable<?> startValue;
    private final Comparable<?> endValue;

    public Between(Object propertyId, Comparable<?> startValue, Comparable<?> endValue) {
        this.propertyId = propertyId;
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public Object getPropertyId() {
        return propertyId;
    }

    public Comparable<?> getStartValue() {
        return startValue;
    }

    public Comparable<?> getEndValue() {
        return endValue;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean passesFilter(Object value)
            throws UnsupportedOperationException {
        if (value instanceof Comparable) {
            Comparable cval = (Comparable) value;
            return cval.compareTo(getStartValue()) >= 0
                    && cval.compareTo(getEndValue()) <= 0;
        }
        return false;
    }

    public boolean appliesToValue(Object propertyId) {
        return getPropertyId() != null && getPropertyId().equals(propertyId);
    }

    @Override
    public int hashCode() {
        return getPropertyId().hashCode() + getStartValue().hashCode()
                + getEndValue().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // Only objects of the same class can be equal
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        final Between o = (Between) obj;

        // Checks the properties one by one
        boolean propertyIdEqual = (null != getPropertyId()) ? getPropertyId()
                .equals(o.getPropertyId()) : null == o.getPropertyId();
        boolean startValueEqual = (null != getStartValue()) ? getStartValue()
                .equals(o.getStartValue()) : null == o.getStartValue();
        boolean endValueEqual = (null != getEndValue()) ? getEndValue().equals(
                o.getEndValue()) : null == o.getEndValue();
        return propertyIdEqual && startValueEqual && endValueEqual;

    }
}
