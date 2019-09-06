package gr.helix.core.common.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Type;

import gr.helix.core.common.model.user.AccountProfile;

@Entity(name = "AccountProfile")
@Table(schema = "web", name = "`account_profile`")
public class AccountProfileEntity {

    @Id
    @Column(name = "`id`", updatable = false)
    @SequenceGenerator(sequenceName = "web.account_profile_id_seq", name = "account_profile_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "account_profile_id_seq", strategy = GenerationType.SEQUENCE)
    int                   id;

    @OneToOne()
    @JoinColumn(
        name = "account",
        foreignKey = @ForeignKey(name = "fk_account_profile_account")
    )
    private AccountEntity account;

    @Email
    @Column(name = "`private_email`", nullable = false)
    String                privateEmail;

    @Email
    @Column(name = "`public_email`", nullable = false)
    String                publicEmail;

    @Column(name = "`name`")
    String                name;

    @Column(name = "`resume`")
    String                resume;

    @Column(name = "`url`")
    String                url;

    @Column(name = "`company`")
    String                company;

    @Column(name = "`location`")
    String                location;

    @Column(name = "`created_on`")
    ZonedDateTime         createdOn = ZonedDateTime.now();

    @Column(name = "`modified_on`")
    ZonedDateTime         modifiedOn;

    @Column(name = "image_binary")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[]        image;

    @Column(name = "image_mime_type")
    private String        imageMimeType;

    public AccountEntity getAccount() {
        return this.account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getPrivateEmail() {
        return this.privateEmail;
    }

    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
    }

    public String getPublicEmail() {
        return this.publicEmail;
    }

    public void setPublicEmail(String publicEmail) {
        this.publicEmail = publicEmail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public ZonedDateTime getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(ZonedDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getId() {
        return this.id;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageMimeType() {
        return this.imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    /**
     * Convert to a DTO object
     *
     * @return a new {@link AccountProfile} instance
     */
    public AccountProfile toDto() {
        final AccountProfile p = new AccountProfile();

        p.setCompany(this.company);
        p.setCreatedOn(this.createdOn);
        p.setEmail(this.publicEmail);
        p.setImage(this.image);
        p.setImageMimeType(this.imageMimeType);
        p.setLocation(this.location);
        p.setModifiedOn(this.modifiedOn);
        p.setName(this.name);
        p.setResume(this.resume);
        p.setUrl(this.url);

        return p;
    }
}
