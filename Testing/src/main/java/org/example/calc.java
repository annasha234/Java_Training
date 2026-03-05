package org.example;

public class calc {
    public double add(Object a, Object b) {
        double num1 = parseToDouble(a);
        double num2 = parseToDouble(b);
        return num1 + num2;
    }

    private double parseToDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Enter number only");
            }
        }
        throw new IllegalArgumentException("Enter number only");
    }
}
