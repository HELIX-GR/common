package gr.helix.core.common.model.user;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class AccountProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            email;
    private String            name;
    private String            resume;
    private String            url;
    private String            company;
    private String            location;
    private ZonedDateTime     createdOn;
    private ZonedDateTime     modifiedOn;
    private byte[]            image;
    private String            imageMimeType;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
