package gr.helix.core.common.model;

import java.time.ZonedDateTime;

public class ApplicationKey {

    private final String        clientId;

    private final String        description;

    private final String        key;

    private final ZonedDateTime createdOn;

    private final boolean       revoked;

    public ApplicationKey(String clientId, String description, String key, ZonedDateTime createdOn, boolean revoked) {
        this.clientId = clientId;
        this.description = description;
        this.key = key;
        this.createdOn = createdOn;
        this.revoked = revoked;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKey() {
        return this.key;
    }

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    public boolean getRevoked() {
        return this.revoked;
    }

}
