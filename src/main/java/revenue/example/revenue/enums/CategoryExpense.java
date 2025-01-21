package revenue.example.revenue.enums;



public enum CategoryExpense {
    NOT_ESSENTIAL("Not Essential"),
    ESSENTIAL("Essential"),
    LUXURY("Luxury");

    private String value;

    CategoryExpense(String value) {
        this.value = value;
    }
}
