package gr.helix.core.common.model.user;

import java.time.ZonedDateTime;

public class ClientKernel {

    private int           id;
    private int           index;
    private String        name;
    private String        description;
    private String        tag;
    private ZonedDateTime createdOn;
    private boolean       active;

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public ZonedDateTime getCreatedOn() {
        return this.createdOn;
    }
    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getIndex() {
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
