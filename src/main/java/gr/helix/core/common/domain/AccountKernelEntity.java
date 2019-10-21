package gr.helix.core.common.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "AccountKernel")
@Table(schema = "lab", name = "`account_hub_kernel`")
public class AccountKernelEntity {

    @Id
    @Column(name = "`id`", updatable = false)
    @SequenceGenerator(
        schema = "lab",
        sequenceName = "account_hub_kernel_id_seq",
        name = "account_hub_kernel_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(generator = "account_hub_kernel_id_seq", strategy = GenerationType.SEQUENCE)
    int             id;

    @NotNull
    @ManyToOne(targetEntity = AccountEntity.class)
    @JoinColumn(name = "account", nullable = false)
    AccountEntity   account;

    @NotNull
    @ManyToOne(targetEntity = HubKernelEntity.class)
    @JoinColumn(name = "hub_kernel", nullable = false)
    HubKernelEntity kernel;

    @ManyToOne(targetEntity = AccountEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "`granted_by`")
    AccountEntity   grantedBy;

    @Column(name = "granted_at", insertable = false)
    ZonedDateTime   grantedAt = ZonedDateTime.now();

    public AccountEntity getAccount() {
        return this.account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public HubKernelEntity getKernel() {
        return this.kernel;
    }

    public void setKernel(HubKernelEntity kernel) {
        this.kernel = kernel;
    }

    public AccountEntity getGrantedBy() {
        return this.grantedBy;
    }

    public void setGrantedBy(AccountEntity grantedBy) {
        this.grantedBy = grantedBy;
    }

    public int getId() {
        return this.id;
    }

    public ZonedDateTime getGrantedAt() {
        return this.grantedAt;
    }

}
