package gr.helix.core.common.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import gr.helix.core.common.model.user.ClientKernel;

@Entity(name = "HubKernel")
@Table(
    schema = "lab", name = "hub_kernel",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_pk_hub_kernel_name", columnNames = {"`name`"})
    }
)
public class HubKernelEntity {

    @Id
    @Column(name = "`id`", updatable = false)
    @SequenceGenerator(
        schema = "lab",
        sequenceName = "hub_kernel_id_seq",
        name = "hub_kernel_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(generator = "hub_kernel_id_seq", strategy = GenerationType.SEQUENCE)
    private Integer          id;

    @Column(name = "`name`")
    private String           name;

    @Column(name = "`description`")
    private String           description;

    @Column(name = "`tag`")
    private String           tag;

    @Column(name = "`created_on`")
    private final ZonedDateTime createdOn = ZonedDateTime.now();

    @Column(name = "`active`")
    private Boolean             active;

    @Column(name = "`index`")
    private int                 index;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getId() {
        return this.id;
    }

    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ClientKernel toDto() {
        final ClientKernel record = new ClientKernel();

        record.setActive(this.active);
        record.setCreatedOn(this.createdOn);
        record.setDescription(this.description);
        record.setId(this.id);
        record.setIndex(this.index);
        record.setName(this.name);
        record.setTag(this.tag);

        return record;
    }

}
