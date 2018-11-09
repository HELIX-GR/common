package gr.helix.core.common.model;

public enum ApplicationKeyErrorCode implements ErrorCode
{
    KEY_ALREADY_EXISTS,
    KEY_NOT_FOUND,
    KEY_IS_REVOKED,
    ;

    @Override
    public String key() {
        return (this.getClass().getSimpleName() + '.' + this.name());
    }

}
