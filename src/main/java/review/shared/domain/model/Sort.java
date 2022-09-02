package review.shared.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Sort implements Serializable {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private final List<Order> orders;

    protected Sort(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public enum Direction {

        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }
    }


    public enum NullHandling {

        /**
         * Lets the data store decide what to do with nulls.
         */
        NATIVE,

        /**
         * A hint to the used data store to order entries with null values before non null entries.
         */
        NULLS_FIRST,

        /**
         * A hint to the used data store to order entries with null values after non null entries.
         */
        NULLS_LAST;
    }

    public static class Order implements Serializable {

        private static final long serialVersionUID = 1522511010900108987L;
        private static final boolean DEFAULT_IGNORE_CASE = false;
        private static final NullHandling DEFAULT_NULL_HANDLING = NullHandling.NATIVE;

        private final Direction direction;
        private final String property;
        private final boolean ignoreCase;
        private final NullHandling nullHandling;

        public Order(Direction direction, String property) {
            this(direction, property, DEFAULT_IGNORE_CASE, DEFAULT_NULL_HANDLING);
        }

        public Order(Direction direction, String property, NullHandling nullHandlingHint) {
            this(direction, property, DEFAULT_IGNORE_CASE, nullHandlingHint);
        }

        public static Order by(String property) {
            return new Order(DEFAULT_DIRECTION, property);
        }

        public static Order asc(String property) {
            return new Order(Direction.ASC, property, DEFAULT_NULL_HANDLING);
        }

        public static Order desc(String property) {
            return new Order(Direction.DESC, property, DEFAULT_NULL_HANDLING);
        }

        private Order(Direction direction, String property, boolean ignoreCase, NullHandling nullHandling) {

            if (property != null && property.length() > 0 && containsText(property)) {
                throw new IllegalArgumentException("Property must not be null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
            this.ignoreCase = ignoreCase;
            this.nullHandling = nullHandling;
        }

        public Direction getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

        public boolean isAscending() {
            return this.direction.isAscending();
        }


        public boolean isDescending() {
            return this.direction.isDescending();
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public Order ignoreCase() {
            return new Order(direction, property, true, nullHandling);
        }

        public Order with(NullHandling nullHandling) {
            return new Order(direction, this.property, ignoreCase, nullHandling);
        }

        public Order nullsFirst() {
            return with(NullHandling.NULLS_FIRST);
        }

        public Order nullsLast() {
            return with(NullHandling.NULLS_LAST);
        }


        public Order nullsNative() {
            return with(NullHandling.NATIVE);
        }

        public NullHandling getNullHandling() {
            return nullHandling;
        }

        private static boolean containsText(CharSequence str) {
            int strLen = str.length();

            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int hashCode() {

            int result = 17;

            result = 31 * result + direction.hashCode();
            result = 31 * result + property.hashCode();
            result = 31 * result + (ignoreCase ? 1 : 0);
            result = 31 * result + nullHandling.hashCode();

            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Order)) {
                return false;
            }

            Order that = (Order) obj;

            return this.direction.equals(that.direction) && this.property.equals(that.property)
                    && this.ignoreCase == that.ignoreCase && this.nullHandling.equals(that.nullHandling);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {

            String result = String.format("%s: %s", property, direction);

            if (!NullHandling.NATIVE.equals(nullHandling)) {
                result += ", " + nullHandling;
            }

            if (ignoreCase) {
                result += ", ignoring case";
            }

            return result;
        }
    }
}
