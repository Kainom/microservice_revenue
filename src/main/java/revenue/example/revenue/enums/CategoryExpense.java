package revenue.example.revenue.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryExpense {
    NOT_ESSENTIAL("Not Essential"),
    ESSENTIAL("Essential"),
    LUXURY("Luxury");

    private final String value;

    CategoryExpense(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
