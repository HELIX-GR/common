package gr.helix.core.common.model;

public class Error {

    public enum EnumLevel {
        INFO,
        WARN,
        ERROR,
        ;
    }

    private final ErrorCode code;

    private final EnumLevel level;

    private final String    description;

    public Error(ErrorCode code, String description) {
        this.code = code;
        this.description = description;
        this.level = EnumLevel.ERROR;
    }

    public Error(ErrorCode code, String description, EnumLevel level) {
        this.code = code;
        this.description = description;
        this.level = level;
    }

    public String getCode() {
        return this.code.key();
    }

    public EnumLevel getLevel() {
        return this.level;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}