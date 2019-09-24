package gr.helix.core.common.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookServerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String      serverUrl;

    private final String      userName;

    private final String      dataDirectory;

    @JsonCreator
    public NotebookServerRequest(
        @JsonProperty("serverUrl") String serverUrl,
        @JsonProperty("userName") String userName,
        @JsonProperty("dataDirectory") String dataDirectory
    ) {
        this.serverUrl = serverUrl;
        this.userName = userName;
        this.dataDirectory = dataDirectory;
    }

    /**
     * The Jupyter Hub URL
     */
    @Email
    @NotBlank
    @JsonProperty("serverUrl")
    public String serverUrl() {
        return this.serverUrl;
    }

    /**
     * The user name of the notebook server owner
     */
    @Email
    @NotBlank
    @JsonProperty("userName")
    public String userName() {
        return this.userName;
    }

    /**
     * The absolute path to the user data directory. This directory must be
     * accessible by the Jupyter Hub server
     */
    @NotBlank
    @JsonProperty("dataDirectory")
    public String dataDirectory() {
        return this.dataDirectory;
    }

}
