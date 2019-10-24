package gr.helix.core.common.model;

import java.util.List;

public class ValidationError extends Error {

    private final String       reason;

    private final List<String> arguments;

    public ValidationError(ErrorCode code, String description, String reason, List<String> arguments) {
        super(code, description, EnumLevel.WARN);

        this.reason = reason;
        this.arguments = arguments;
    }

    public String getReason() {
        return this.reason;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

}
