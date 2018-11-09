package gr.helix.core.common.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import gr.helix.core.common.model.ApplicationKey;

@Entity(name = "ApplicationKey")
@Table(
    schema = "web", name = "`application_key`"
)
public class ApplicationKeyEntity {

    @Id
    @Column(name = "`client_id`", updatable = false)
    String        clientId;

    @Column(name = "`description`", nullable = true, updatable = false)
    String        description;

    @NotNull
    @Column(name = "`api_key`", nullable = false)
    String        key;

    @Column(name = "`created_on`")
    ZonedDateTime createdOn = ZonedDateTime.now(ZoneId.systemDefault());

    @Column(name = "`revoked`")
    boolean       revoked = false;

    public ApplicationKeyEntity() {
    }

    public ApplicationKeyEntity(String clientId, String key) {
        this.clientId = clientId;
        this.key = key;
    }

    public ApplicationKeyEntity(String clientId, String description, String key) {
        this.clientId = clientId;
        this.description = description;
        this.key = key;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isRevoked() {
        return this.revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public String getClientId() {
        return this.clientId;
    }

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Convert to a DTO object
     *
     * @return a new {@link ApplicationKey} instance
     */
    public ApplicationKey toDto() {
        return new ApplicationKey(
            this.clientId,
            this.description,
            this.key,
            this.createdOn,
            this.revoked
        );
    }
}
