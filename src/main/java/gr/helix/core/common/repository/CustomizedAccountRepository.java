package gr.helix.core.common.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import gr.helix.core.common.model.user.AccountProfile;

@Transactional()
public interface CustomizedAccountRepository {

    /**
     * Get the profile for the given email.
     *
     * The email refers to the value fetched by the authentication provider.
     *
     * @param email the profile owner's email
     *
     * @return an instance of {@link ActiveProfile}
     */
    Optional<AccountProfile> getProfileByEmail(String email);

    /**
     * Update user profile
     *
     * @param privateEmail the email used for authenticating the user
     * @param publicEmail the email displayed in the user profile page
     * @param resume
     * @param url
     * @param company
     * @param location
     * @param image
     * @param imageMimeType
     *
     * @return an instance of {@link ActiveProfile}
     */
    AccountProfile updateProfile(
        String privateEmail,
        String publicEmail,
        String name,
        String resume,
        String url,
        String company,
        String location,
        byte[] image,
        String imageMimeType
    );

}
