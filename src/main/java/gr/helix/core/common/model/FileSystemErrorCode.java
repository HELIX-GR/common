package gr.helix.core.common.model;

/**
 * Error codes for file system operations
 */
public enum FileSystemErrorCode implements ErrorCode {
    UNKNOWN,
    CANNOT_RESOLVE_PATH,
    PATH_IS_EMPTY,
    PATH_ALREADY_EXISTS,
    PATH_NOT_FOUND,
    PATH_NOT_EMPTY,
    NOT_ENOUGH_SPACE,
    EXTENSION_NOT_SUPPORTED,
    ;

    @Override
    public String key() {
        return (this.getClass().getSimpleName() + '.' + this.name());
    }

}