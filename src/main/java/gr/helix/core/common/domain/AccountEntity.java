package gr.helix.core.common.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NaturalId;

import gr.helix.core.common.model.EnumRole;
import gr.helix.core.common.model.user.Account;

@Entity(name = "Account")
@Table(
    schema = "web", name = "`account`",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_account_username", columnNames = {"`username`"}),
        @UniqueConstraint(name = "uq_account_email", columnNames = {"`email`"}),
    })
public class AccountEntity {

    @Id
    @Column(name = "`id`", updatable = false)
    @SequenceGenerator(sequenceName = "web.account_id_seq", name = "account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "account_id_seq", strategy = GenerationType.SEQUENCE)
    Integer                 id;

    @NotNull
    @NaturalId
    @Column(name = "`username`", nullable = false, updatable = false)
    String                  username;

    @NotNull
    @NaturalId
    @Email
    @Column(name = "`email`", nullable = false)
    String                  email;

    @Column(name = "`given_name`")
    String                  givenName;

    @Column(name = "`family_name`")
    String                  familyName;

    @Pattern(regexp = "[a-z][a-z]")
    @Column(name = "`lang`")
    String                  lang;

    @Column(name = "`password`")
    String                  password;

    @Column(name = "`active`")
    Boolean                 active  = true;

    @Column(name = "`blocked`")
    Boolean                 blocked = false;

    @Column(name = "`registered_at`")
    ZonedDateTime           registeredAt;

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "account")
    private AccountProfileEntity profile;

    @OneToMany(
        targetEntity = AccountRoleEntity.class,
        mappedBy = "account",
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    List<AccountRoleEntity> roles   = new ArrayList<>();

    @OneToMany(
        targetEntity = AccountKernelEntity.class,
        mappedBy = "account",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    List<AccountKernelEntity> kernels = new ArrayList<>();

    public AccountEntity() {
    }

    public AccountEntity(int id) {
        this.id = id;
    }

    public AccountEntity(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setRegistered(ZonedDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getFullName() {
        return String.format("%s %s", this.givenName, this.familyName).trim();
    }

    public String getLang() {
        return this.lang;
    }

    public boolean isActive() {
        return this.active == null ? true : this.active;
    }

    public boolean isBlocked() {
        return this.blocked == null ? false : this.blocked;
    }

    public ZonedDateTime getRegisteredAt() {
        return this.registeredAt;
    }

    public AccountProfileEntity getProfile() {
        return this.profile;
    }

    public void setProfile(AccountProfileEntity profile) {
        this.profile = profile;
    }

    public List<AccountKernelEntity> getKernels() {
        return this.kernels;
    }

    public boolean hasKernel(String name) {
        for (final AccountKernelEntity k : this.kernels) {
            if (k.getKernel().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void grantKernel(HubKernelEntity kernel, AccountEntity grantedBy) {
        if (!this.hasKernel(kernel.getName())) {
            final AccountKernelEntity accountKernel = new AccountKernelEntity();

            accountKernel.setAccount(this);
            accountKernel.setGrantedBy(grantedBy);
            accountKernel.setKernel(kernel);

            this.getKernels().add(accountKernel);
        }
    }

    public Set<EnumRole> getRoles() {
        final EnumSet<EnumRole> r = EnumSet.noneOf(EnumRole.class);
        for (final AccountRoleEntity ar : this.roles) {
            r.add(ar.role);
        }
        return r;
    }

    public boolean hasRole(EnumRole role) {
        for (final AccountRoleEntity ar : this.roles) {
            if (role == ar.role) {
                return true;
            }
        }
        return false;
    }

    public void grant(EnumRole role, AccountEntity grantedBy) {
        if (!this.hasRole(role)) {
            this.roles.add(new AccountRoleEntity(this, role, null, grantedBy));

            // Handle role hierarchy
            switch (role) {
                case ROLE_BETA_STUDENT :
                case ROLE_BETA_ACADEMIC :
                    this.grant(EnumRole.ROLE_BETA, grantedBy);
                    break;
                case ROLE_STANDARD_STUDENT :
                case ROLE_STANDARD_ACADEMIC :
                    this.grant(EnumRole.ROLE_STANDARD, grantedBy);
                    break;
                default :
                    // No action
            }
        }
    }

    public void revoke(EnumRole role) {
        AccountRoleEntity target = null;
        for (final AccountRoleEntity ar : this.roles) {
            if (role == ar.role) {
                target = ar;
                break;
            }
        }
        if (target != null) {
            this.roles.remove(target);

            // Handle role hierarchy
            switch (role) {
                case ROLE_BETA :
                    this.revoke(EnumRole.ROLE_BETA_STUDENT);
                    this.revoke(EnumRole.ROLE_BETA_ACADEMIC);
                    break;
                case ROLE_STANDARD :
                    this.revoke(EnumRole.ROLE_STANDARD_STUDENT);
                    this.revoke(EnumRole.ROLE_STANDARD_ACADEMIC);
                    break;
                default :
                    // No action
            }
        }
    }

    /**
     * Convert to a DTO object
     *
     * @return a new {@link Account} instance
     */
    public Account toDto() {
        final Account a = new Account(this.id, this.username, this.email);

        a.setActive(this.active);
        a.setBlocked(this.blocked);

        a.setFamilyName(this.familyName);
        a.setGivenName(this.givenName);

        a.setLang(this.lang);
        a.setRegisteredAt(this.registeredAt);
        a.setRoles(this.getRoles());
        this.kernels.stream().forEach(k -> a.getKernels().add(k.getKernel().getName()));

        if (this.profile != null) {
            a.setProfile(this.profile.toDto());
        }

        return a;
    }
}
