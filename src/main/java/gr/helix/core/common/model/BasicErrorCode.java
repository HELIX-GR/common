package gr.helix.core.common.model;

public enum BasicErrorCode implements ErrorCode
{
    UNKNOWN,
    NOT_IMPLEMENTED,
    NOT_SUPPORTED,

    INPUT_NOT_READABLE,
    INPUT_INVALID,

    RESOURCE_NOT_FOUND,
    LOCALE_NOT_SUPPORTED,

    NO_RESULT,

    AUTHENTICATION_FAILED,
    AUTHENTICATION_REQUIRED,

    USER_NOT_FOUND,

    IO_ERROR,
    URI_SYNTAX_ERROR,
    HTTP_ERROR,
    ;

    @Override
    public String key() {
        return (this.getClass().getSimpleName() + '.' + this.name());
    }

}
