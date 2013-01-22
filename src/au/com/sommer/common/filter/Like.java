package au.com.sommer.common.filter;

public class Like extends ValueFilter {
    private final String value;
    private boolean caseSensitive;

    public Like(String value) {
        this(value, true);
    }

    public Like(String value, boolean caseSensitive) {
        this.value = value;
        setCaseSensitive(caseSensitive);
    }

    public String getValue() {
        return value;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean passesFilter(Object value)
            throws UnsupportedOperationException {
        if (!value.getClass()
                .isAssignableFrom(String.class)) {
            // We can only handle strings
            return false;
        }
        String colValue = (String) value;

        String pattern = getValue().replace("%", ".*");
        if (isCaseSensitive()) {
            return colValue.matches(pattern);
        }
        return colValue.toUpperCase().matches(pattern.toUpperCase());
    }

    public boolean appliesToValue(Object value) {
        return value != null && value.getClass().isAssignableFrom(String.class);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (caseSensitive ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Like))
			return false;
		Like other = (Like) obj;
		if (caseSensitive != other.caseSensitive)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
