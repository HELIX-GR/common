package gr.helix.core.common.repository;

import java.time.ZonedDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gr.helix.core.common.domain.AccountEntity;
import gr.helix.core.common.domain.AccountProfileEntity;
import gr.helix.core.common.model.user.AccountProfile;

public class CustomizedAccountRepositoryImpl implements CustomizedAccountRepository{

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public Optional<AccountProfile> getProfileByEmail(String email) {
        final String query = "select p from AccountProfile p where p.privateEmail = :email";

        return this.entityManager.createQuery(query, AccountProfileEntity.class)
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .map(p -> p.toDto())
                .findFirst();
    }

    @Override
    public AccountProfile updateProfile(
        String privateEmail,
        String publicEmail,
        String name,
        String resume,
        String url,
        String company,
        String location,
        byte[] image,
        String imageMimeType
    ) {
        final String accountQuery = "select a from Account a where a.email = :email";

        final Optional<AccountEntity> account = this.entityManager.createQuery(accountQuery, AccountEntity.class)
            .setParameter("email", privateEmail)
            .setMaxResults(1)
            .getResultList()
            .stream()
            .findFirst();

        final String profileQuery = "select p from AccountProfile p where p.privateEmail = :email";

        Optional<AccountProfileEntity> profile = this.entityManager.createQuery(profileQuery, AccountProfileEntity.class)
                .setParameter("email", privateEmail)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();

        boolean persist = false;
        if (!profile.isPresent()) {
            profile = Optional.of(new AccountProfileEntity());
            profile.get().setPrivateEmail(privateEmail);

            persist = true;
        }

        this.updateAccountProfileEntity(
            profile.get(),
            publicEmail,
            name,
            resume,
            url,
            company,
            location,
            image,
            imageMimeType
        );

        if (account.isPresent()) {
            profile.get().setAccount(account.get());
        }

        if (persist) {
            this.entityManager.persist(profile.get());
        }
        this.entityManager.flush();

        return profile.get().toDto();
    }

    private void updateAccountProfileEntity(
        AccountProfileEntity entity,
        String publicEmail,
        String name,
        String resume,
        String url,
        String company,
        String location,
        byte[] image,
        String imageMimeType
    ) {
        entity.setCompany(company);
        entity.setImage(image);
        entity.setImageMimeType(imageMimeType);
        entity.setLocation(location);
        entity.setModifiedOn(ZonedDateTime.now());
        entity.setName(name);
        entity.setPublicEmail(publicEmail);
        entity.setResume(resume);
        entity.setUrl(url);
    }
}
