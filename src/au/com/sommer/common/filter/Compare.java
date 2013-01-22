package au.com.sommer.common.filter;

/**
 * Simple container filter comparing an item property value against a given
 * constant value. Use the nested classes {@link Equal}, {@link Greater},
 * {@link Less}, {@link GreaterOrEqual} and {@link LessOrEqual} instead of this
 * class directly.
 * 
 * This filter also directly supports in-memory filtering.
 * 
 * The reference and actual values must implement {@link Comparable} and the
 * class of the actual property value must be assignable from the class of the
 * reference value.
 */
public abstract class Compare extends ValueFilter {

    public enum Operation {
        EQUAL, GREATER, LESS, GREATER_OR_EQUAL, LESS_OR_EQUAL
    };

    private final Operation operation;
    private Object value;

    /**
     * A {@link Compare} filter that accepts items for which the identified
     * property value is equal to <code>value</code>.
     * 
     * For in-memory filters, equals() is used for the comparison. For other
     * containers, the comparison implementation is container dependent and may
     * use e.g. database comparison operations.
     */
    public static final class Equal extends Compare {
        /**
         * Construct a filter that accepts items for which the identified
         * property value is equal to <code>value</code>.
         * 
         * For in-memory filters, equals() is used for the comparison. For other
         * containers, the comparison implementation is container dependent and
         * may use e.g. database comparison operations.
         * 
         * @param propertyId
         *            the identifier of the property whose value to compare
         *            against value, not null
         * @param value
         *            the value to compare against - null values may or may not
         *            be supported depending on the container
         */
        public Equal() {
            super(Operation.EQUAL);
        }
        public Equal(Object value) {
            super(value, Operation.EQUAL);
        }
    }

    /**
     * A {@link Compare} filter that accepts items for which the identified
     * property value is greater than <code>value</code>.
     * 
     * For in-memory filters, the values must implement {@link Comparable} and
     * {@link Comparable#compareTo(Object)} is used for the comparison. For
     * other containers, the comparison implementation is container dependent
     * and may use e.g. database comparison operations.
     */
    public static final class Greater extends Compare {
        /**
         * Construct a filter that accepts items for which the identified
         * property value is greater than <code>value</code>.
         * 
         * For in-memory filters, the values must implement {@link Comparable}
         * and {@link Comparable#compareTo(Object)} is used for the comparison.
         * For other containers, the comparison implementation is container
         * dependent and may use e.g. database comparison operations.
         * 
         * @param propertyId
         *            the identifier of the property whose value to compare
         *            against value, not null
         * @param value
         *            the value to compare against - null values may or may not
         *            be supported depending on the container
         */
        public Greater() {
            super(Operation.GREATER);
        }
        public Greater(Object value) {
            super(value, Operation.GREATER);
        }
    }

    /**
     * A {@link Compare} filter that accepts items for which the identified
     * property value is less than <code>value</code>.
     * 
     * For in-memory filters, the values must implement {@link Comparable} and
     * {@link Comparable#compareTo(Object)} is used for the comparison. For
     * other containers, the comparison implementation is container dependent
     * and may use e.g. database comparison operations.
     */
    public static final class Less extends Compare {
        /**
         * Construct a filter that accepts items for which the identified
         * property value is less than <code>value</code>.
         * 
         * For in-memory filters, the values must implement {@link Comparable}
         * and {@link Comparable#compareTo(Object)} is used for the comparison.
         * For other containers, the comparison implementation is container
         * dependent and may use e.g. database comparison operations.
         * 
         * @param propertyId
         *            the identifier of the property whose value to compare
         *            against value, not null
         * @param value
         *            the value to compare against - null values may or may not
         *            be supported depending on the container
         */
        public Less() {
            super(Operation.LESS);
        }
        public Less(Object value) {
            super(value, Operation.LESS);
        }
    }

    /**
     * A {@link Compare} filter that accepts items for which the identified
     * property value is greater than or equal to <code>value</code>.
     * 
     * For in-memory filters, the values must implement {@link Comparable} and
     * {@link Comparable#compareTo(Object)} is used for the comparison. For
     * other containers, the comparison implementation is container dependent
     * and may use e.g. database comparison operations.
     */
    public static final class GreaterOrEqual extends Compare {
        /**
         * Construct a filter that accepts items for which the identified
         * property value is greater than or equal to <code>value</code>.
         * 
         * For in-memory filters, the values must implement {@link Comparable}
         * and {@link Comparable#compareTo(Object)} is used for the comparison.
         * For other containers, the comparison implementation is container
         * dependent and may use e.g. database comparison operations.
         * 
         * @param propertyId
         *            the identifier of the property whose value to compare
         *            against value, not null
         * @param value
         *            the value to compare against - null values may or may not
         *            be supported depending on the container
         */
        public GreaterOrEqual() {
            super(Operation.GREATER_OR_EQUAL);
        }
        public GreaterOrEqual(Object value) {
            super(value, Operation.GREATER_OR_EQUAL);
        }
    }

    /**
     * A {@link Compare} filter that accepts items for which the identified
     * property value is less than or equal to <code>value</code>.
     * 
     * For in-memory filters, the values must implement {@link Comparable} and
     * {@link Comparable#compareTo(Object)} is used for the comparison. For
     * other containers, the comparison implementation is container dependent
     * and may use e.g. database comparison operations.
     */
    public static final class LessOrEqual extends Compare {
        /**
         * Construct a filter that accepts items for which the identified
         * property value is less than or equal to <code>value</code>.
         * 
         * For in-memory filters, the values must implement {@link Comparable}
         * and {@link Comparable#compareTo(Object)} is used for the comparison.
         * For other containers, the comparison implementation is container
         * dependent and may use e.g. database comparison operations.
         * 
         * @param propertyId
         *            the identifier of the property whose value to compare
         *            against value, not null
         * @param value
         *            the value to compare against - null values may or may not
         *            be supported depending on the container
         */
        public LessOrEqual() {
            super(Operation.LESS_OR_EQUAL);
        }
        public LessOrEqual(Object value) {
            super(value, Operation.LESS_OR_EQUAL);
        }
    }

    Compare(Operation operation) {
        this.operation = operation;
    }

    Compare(Object value, Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public boolean passesFilter(Object value) {
        switch (getOperation()) {
        case EQUAL:
            return (null == this.value) ? (null == value) : this.value
                    .equals(value);
        case GREATER:
            return compareValue(value) > 0;
        case LESS:
            return compareValue(value) < 0;
        case GREATER_OR_EQUAL:
            return compareValue(value) >= 0;
        case LESS_OR_EQUAL:
            return compareValue(value) <= 0;
        }
        // all cases should have been processed above
        return false;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected int compareValue(Object value1) {
        if (null == value) {
            return null == value1 ? 0 : -1;
        } else if (null == value1) {
            return 1;
        } else if (getValue() instanceof Comparable
                && value1.getClass().isAssignableFrom(getValue().getClass())) {
            return -((Comparable) getValue()).compareTo(value1);
        }
        throw new IllegalArgumentException("Could not compare the arguments: "
                + value1 + ", " + getValue());
    }

    public boolean appliesToValue(Object value) {
        return value != null;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

    @Override
    public boolean equals(Object obj) {

        // Only objects of the same class can be equal
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        final Compare o = (Compare) obj;

        if (getOperation() != o.getOperation()) {
            return false;
        }
        return (null == getValue()) ? null == o.getValue() : getValue().equals(
                o.getValue());
    }

	/**
     * Returns the comparison operation.
     * 
     * @return {@link Operation}
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Returns the value to compare the property against.
     * 
     * @return comparison reference value
     */
    public Object getValue() {
        return value;
    }

	public void setValue(Object value) {
		this.value = value;
	}
}
