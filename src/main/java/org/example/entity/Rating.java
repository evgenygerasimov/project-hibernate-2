package org.example.entity;

public enum Rating {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    public static Rating fromString(String dbValue) {
        return switch (dbValue) {
            case "PG-13" -> PG_13;
            case "NC-17" -> NC_17;
            default -> Rating.valueOf(dbValue);
        };
    }
}
