package revenue.example.revenue.enums;

public enum IndexRevenue {
    SELIC,
    IPCA,
    CDB;

    public String getIndexDescription() {
        return this.name();
    }
    

    public String getIndexName() {
        return this.name().toLowerCase();
    }
}

