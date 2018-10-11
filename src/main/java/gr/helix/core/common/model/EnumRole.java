package gr.helix.core.common.model;

import java.util.Arrays;

public enum EnumRole {

    ROLE_USER(1, "User"),
    ROLE_ADMIN(2, "Administrator"),
    ROLE_DEVELOPER(3, "Developer"),
    ROLE_STANDARD(1001, "User Level 1"),
    ROLE_STANDARD_ACADEMIC(1002, "User Level 1.1"),
    ROLE_STANDARD_STUDENT(1003, "User Level 1.2"),
    ROLE_BETA(2001, "User Level 2"),
    ROLE_BETA_ACADEMIC(2002, "User Level 2.1"),
    ROLE_BETA_STUDENT(2003, "User Level 2.2"), 
    ;

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
