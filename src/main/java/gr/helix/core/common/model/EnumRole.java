package gr.helix.core.common.model;

import java.util.Arrays;

public enum EnumRole {

    ROLE_USER(1, "User"),ROLE_STANDARD(2, "User Level 1"),ROLE_STANDARD_ACADEMIC(3, "User Level 1.1"),ROLE_STANDARD_STUDENT(4, "User Level 1.2"),ROLE_BETA(5, "User Level 2"),ROLE_BETA_ACADEMIC(6, "User Level 2.1"),ROLE_BETA_STUDENT(7, "User Level 2.2"), ROLE_ADMIN(8, "Administrator");

    private final int    value;

    private final String description;

    private EnumRole(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public static EnumRole fromString(String value) {
        return Arrays.stream(EnumRole.values())
            .filter(r -> r.name().equalsIgnoreCase(value))
            .findFirst()
            .orElse(null);
    }

}
