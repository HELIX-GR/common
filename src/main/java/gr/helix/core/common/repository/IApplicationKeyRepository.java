package gr.helix.core.common.repository;

import java.util.List;
import java.util.Optional;

import gr.helix.core.common.model.ApplicationException;
import gr.helix.core.common.model.ApplicationKey;

public interface IApplicationKeyRepository {

    /**
     * Get all registered application keys
     *
     * @return a list of {@link ApplicationKey} objects
     */
    List<ApplicationKey> getAll();

    /**
     * Finds an application key by the client id
     *
     * @param clientId the client id
     * @return an {@link ApplicationKey} instance if a registration exists
     */
    Optional<ApplicationKey> findByClientId(String clientId);

    /**
     * Finds an application key
     *
     * @param key the application key
     * @return an {@link ApplicationKey} instance if a registration exists
     */
    Optional<ApplicationKey> findByKey(String key);

    /**
     * Creates a new application key for the given client id
     *
     * @param clientId the client id
     * @throws ApplicationException if a registration already exists for the given client id
     */
    void create(String clientId) throws ApplicationException;

    /**
     * Deletes an existing application key for the given client id
     *
     * @param clientId the client id
     * @throws ApplicationException if no registration exists for the given client id
     */
    void delete(String clientId) throws ApplicationException;

    /**
     * Revokes the application key for the given client id
     *
     * @param clientId the client id
     * @throws ApplicationException if a registration does not exist for the given client id
     */
    void revoke(String clientId) throws ApplicationException;

    /**
     * Validates an application key
     *
     * @param key the key to validate
     * @return true if the key is valid; Otherwise false is returned
     */
    boolean validate(String key);

}
