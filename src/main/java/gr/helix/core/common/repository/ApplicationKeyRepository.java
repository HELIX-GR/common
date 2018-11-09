package gr.helix.core.common.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gr.helix.core.common.domain.ApplicationKeyEntity;
import gr.helix.core.common.model.ApplicationException;
import gr.helix.core.common.model.ApplicationKey;
import gr.helix.core.common.model.ApplicationKeyErrorCode;

@Repository()
public class ApplicationKeyRepository implements IApplicationKeyRepository{

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public List<ApplicationKey> getAll() {
        final String qlString = "select k from ApplicationKey k order by k.clientId";

        return this.entityManager.createQuery(qlString, ApplicationKeyEntity.class)
            .getResultList()
            .stream()
            .map(k -> k.toDto())
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ApplicationKey> findByClientId(String clientId) {
        final String qlString = "select k from ApplicationKey k where k.clientId = :clientId";

        return this.entityManager.createQuery(qlString, ApplicationKeyEntity.class)
            .setParameter("clientId", clientId)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .map(k -> k.toDto())
            .findFirst();
    }

    @Override
    public Optional<ApplicationKey> findByKey(String key) {
        final String qlString = "select k from ApplicationKey k where k.key = :key";

        return this.entityManager.createQuery(qlString, ApplicationKeyEntity.class)
            .setParameter("key", key)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .map(k -> k.toDto())
            .findFirst();
    }

    @Override
    @Transactional
    public void create(String clientId) throws ApplicationException {
        final Optional<ApplicationKey> existing = this.findByClientId(clientId);

        if (existing.isPresent()) {
            throw ApplicationException.fromPattern(ApplicationKeyErrorCode.KEY_ALREADY_EXISTS, Arrays.asList(clientId));
        }

        final ApplicationKeyEntity entity = new ApplicationKeyEntity(clientId, this.createApplicationKey());
        this.entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void delete(String clientId) throws ApplicationException {
        final String qlString = "select k from ApplicationKey k where k.clientId = :clientId";

        final Optional<ApplicationKeyEntity> entity = this.entityManager
            .createQuery(qlString, ApplicationKeyEntity.class)
            .setParameter("clientId", clientId)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .findFirst();

        if (!entity.isPresent()) {
            throw ApplicationException.fromPattern(ApplicationKeyErrorCode.KEY_NOT_FOUND, Arrays.asList(clientId));
        }

        this.entityManager.remove(entity.get());
    }

    @Override
    @Transactional
    public void revoke(String clientId) throws ApplicationException {
        final String qlString = "select k from ApplicationKey k where k.clientId = :clientId";

        final Optional<ApplicationKeyEntity> entity = this.entityManager.createQuery(qlString, ApplicationKeyEntity.class)
            .setParameter("clientId", clientId)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .findFirst();

        if (!entity.isPresent()) {
            throw ApplicationException.fromPattern(ApplicationKeyErrorCode.KEY_NOT_FOUND, Arrays.asList(clientId));
        }

        entity.get().setRevoked(true);
    }

    @Override
    public boolean validate(String key) {
        final String qlString = "select k from ApplicationKey k where k.key = :key";

        final Optional<ApplicationKeyEntity> entity = this.entityManager.createQuery(qlString, ApplicationKeyEntity.class)
            .setParameter("key", key)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .findFirst();

        return (entity.isPresent() && !entity.get().isRevoked());
    }

    private String createApplicationKey() {
        return UUID.randomUUID().toString();
    }

}