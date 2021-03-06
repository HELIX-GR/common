package gr.helix.core.common.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RestResponse<Result> {

    private final List<Error> errors = new ArrayList<Error>();

    private final Result      result;

    protected RestResponse() {
        this.result = null;
    }

    protected RestResponse(Result r) {
        this.result = r;
    }

    protected RestResponse(Result r, List<Error> errors) {
        this.result = r;
        this.errors.addAll(errors);
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public List<Error> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    @JsonInclude(Include.NON_NULL)
    public Result getResult() {
        return this.result;
    }

    @JsonProperty("success")
    public boolean getSuccess() {
        return !this.hasErrors();
    }

    public static <R> RestResponse<R> result(R r) {
        return new RestResponse<>(r);
    }

    public static <R> RestResponse<R> success() {
        return new RestResponse<R>();
    }

    public static <R> RestResponse<R> error(ErrorCode code, String description, Error.EnumLevel level) {
        return RestResponse.<R>error(new Error(code, description, level));
    }

    public static <R> RestResponse<R> error(ErrorCode code, String description) {
        return error(code, description, Error.EnumLevel.ERROR);
    }

    public static <R> RestResponse<R> error(Error e) {
        return new RestResponse<R>(null, Collections.singletonList(e));
    }

    public static <R> RestResponse<R> error(List<Error> errors) {
        return new RestResponse<R>(null, errors);
    }

    public static <R> RestResponse<R> invalid(List<FieldError> fieldErrors) {
        final List<Error> errors = fieldErrors.stream()
            .map(e -> {
                final List<String> arguments = Arrays.stream(e.getArguments())
                    .skip(1)
                    .map(Object::toString)
                    .collect(Collectors.toList());

                return new ValidationError(
                    BasicErrorCode.VALIDATION_ERROR, e.getField(), e.getCode(), arguments
                );
            }).collect(Collectors.toList());

        return new RestResponse<R>(null, errors);
    }
}
