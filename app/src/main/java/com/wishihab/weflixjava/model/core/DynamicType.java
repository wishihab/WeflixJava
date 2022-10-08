package com.wishihab.weflixjava.model.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class DynamicType {

    public static class StringType extends DynamicType {

        private final String value;

        public StringType(@NonNull String value) {
            this.value = value;
        }

        @NonNull
        @Override
        public String getValue() {
            return value;
        }

        @NonNull
        @Override
        public String getString() {
            return value;
        }

        @Override
        public boolean isString() {
            return true;
        }

        @NonNull
        @Override
        public String getAsString() {
            return value;
        }

        @NonNull
        @Override
        public StringType asStringType() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringType that = (StringType) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class NumberType extends DynamicType {
        private final Number value;

        public NumberType(@NonNull Number value) {
            this.value = value;
        }

        @NonNull
        @Override
        public Number getValue() {
            return value;
        }

        @NonNull
        @Override
        public Number getNumber() {
            return value;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public int getAsInt(int fallback) {
            return value.intValue();
        }

        @Override
        public long getAsLong(long fallback) {
            return value.longValue();
        }

        @Override
        public double getAsDouble(double fallback) {
            return value.doubleValue();
        }

        @NonNull
        @Override
        public NumberType asNumberType() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NumberType that = (NumberType) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class BooleanType extends DynamicType {
        private final boolean value;

        public BooleanType(boolean value) {
            this.value = value;
        }

        @NonNull
        @Override
        public Boolean getValue() {
            return value;
        }

        @NonNull
        @Override
        public Boolean getBoolean() {
            return value;
        }

        @Override
        public boolean isBoolean() {
            return true;
        }

        @Override
        public boolean getAsBoolean() {
            return value;
        }

        @NonNull
        @Override
        public BooleanType asBooleanType() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BooleanType that = (BooleanType) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class ListType extends DynamicType {
        private final List<DynamicType> value;

        public ListType(@NonNull List<DynamicType> value) {
            this.value = value;
        }

        /** Get list if it is list type.
         *
         */
        @Nullable
        public List<DynamicType> getList() {
            return value;
        }

        @NonNull
        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public boolean isList() {
            return true;
        }

        @NonNull
        @Override
        public ListType asListType() {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListType listType = (ListType) o;
            return Objects.equals(value, listType.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class MapType extends DynamicType {
        private final Map<String, DynamicType> value;

        public MapType(@NonNull Map<String, DynamicType> value) {
            this.value = value;
        }

        @NonNull
        @Override
        public Object getValue() {
            return value;
        }

        @NonNull
        @Override
        public Map<String, DynamicType> getMap() {
            return value;
        }

        @Override
        public boolean isMap() {
            return true;
        }

        @NonNull
        @Override
        public MapType asMapType() {
            return this;
        }

        /** Get value associated with key as string if available.
         *
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapType mapType = (MapType) o;
            return Objects.equals(value, mapType.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // package private constructor
    DynamicType() {}

    /**
     *  Get current value.
     */
    @NonNull
    public abstract Object getValue();

    /** Get string if it is string type.
     *
     */
    @Nullable
    public String getString() {
        return null;
    }

    /** Get number if it is number type.
     *
     */
    @Nullable
    public Number getNumber() {
        return null;
    }

    /** Get boolean if it is boolean type.
     *
     */
    @Nullable
    public Boolean getBoolean() {
        return null;
    }

    /** Get list if it is list type.
     *
     */
    @Nullable
    public List<DynamicType> getList() {
        return null;
    }

    /** Get as {@link ListType} if it is list type.
     *
     */
    @Nullable
    public ListType asListType() {
        return null;
    }

    /** Get map if it is map type, null otherwise.
     *
     */
    @Nullable
    public Map<String, DynamicType> getMap() {
        return null;
    }

    /** Get as {@link MapType} if it is map type, null otherwise.
     *
     */
    @Nullable
    public MapType asMapType() {
        return null;
    }

    @Nullable
    public NumberType asNumberType() {
        return null;
    }

    @Nullable
    public StringType asStringType() {
        return null;
    }

    @Nullable
    public BooleanType asBooleanType() {
        return null;
    }

    public boolean isString() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isMap() {
        return false;
    }

    public boolean isList() {
        return false;
    }

    @NonNull
    public String getAsString() {
        return String.valueOf(getValue());
    }

    /** Get as Integer, or fallback if failed.
     *
     */
    public int getAsInt(int fallback) {
        int result;
        try {
            result = Integer.parseInt(getAsString());
        } catch (NumberFormatException e) {
            result = fallback;
        }
        return result;
    }

    /** Get as Long, or fallback if failed.
     *
     */
    public long getAsLong(long fallback) {
        long result;
        try {
            result = Long.parseLong(getAsString());
        } catch (NumberFormatException e) {
            result = fallback;
        }
        return result;
    }

    /** Get as Double, or null if failed.
     *
     */
    public double getAsDouble(double fallback) {
        double result;
        try {
            result = Double.parseDouble(getAsString());
        } catch (NumberFormatException e) {
            result = fallback;
        }
        return result;
    }

    public boolean getAsBoolean() {
        return Boolean.parseBoolean(getAsString());
    }

    @NonNull
    @Override
    public String toString() {
        return getAsString();
    }


    /** Get child for given path, or null if path not found.
     *
     * @param paths
     *      Child path.
     * @return
     *      Selected child, or null.
     */
    @Nullable
    public DynamicType path(String... paths) {
        int n = paths.length;
        DynamicType current = this;
        for (int i=0; i<n; i++) {
            String key = paths[i];
            if (current != null && current.isMap() && current.getMap().containsKey(key)) {
                DynamicType next = current.getMap().get(key);
                if (i == n-1) {
                    return next;
                } else {
                    current = next;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
