package gr.helix.core.common.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookServerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String serverUrl;

    private final String userName;

    private final String dataDirectory;

    private final Long memoryQuota;
    
    private final Long spaceQuota;
    
    @JsonCreator
    public NotebookServerRequest(
        @JsonProperty("serverUrl") String serverUrl,
        @JsonProperty("userName") String userName,
        @JsonProperty("dataDirectory") String dataDirectory,
        @JsonProperty("memoryQuota") Long memoryQuota,
        @JsonProperty("spaceQuota") Long spaceQuota
    ) {
        this.serverUrl = serverUrl;
        this.userName = userName;
        this.dataDirectory = dataDirectory;
        this.memoryQuota = memoryQuota;
        this.spaceQuota = spaceQuota;
    }
    
    public NotebookServerRequest(String serverUrl, String userName, String dataDirectory)
    {
        this(serverUrl, userName, dataDirectory, null, null);
    }

    /**
     * The Jupyter Hub URL
     */
    @Email
    @NotBlank
    @JsonProperty("serverUrl")
    public String getServerUrl() {
        return this.serverUrl;
    }

    /**
     * The user name of the notebook server owner
     */
    @Email
    @NotBlank
    @JsonProperty("userName")
    public String getUserName() {
        return this.userName;
    }

    /**
     * The absolute path to the user data directory. This directory must be
     * accessible by the Jupyter Hub server
     */
    @NotBlank
    @JsonProperty("dataDirectory")
    public String getDataDirectory() {
        return this.dataDirectory;
    }
    
    /**
     * The memory quota (in bytes), or a <code>null</code> value for site defaults.
     */
    @JsonProperty("memoryQuota")
    public Long getMemoryQuota() {
        return memoryQuota;
    }
    

    /**
     * The disk space quota (in bytes), or a <code>null</code> value for site defaults.
     */
    @JsonProperty("spaceQuota")
    public Long getSpaceQuota() {
        return spaceQuota;
    }
}
