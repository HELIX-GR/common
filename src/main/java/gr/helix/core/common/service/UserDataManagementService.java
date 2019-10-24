package gr.helix.core.common.service;

import gr.helix.core.common.model.user.AccountInfo;

public interface UserDataManagementService {

    /**
     * Setup a user's data directory for the JupyterHub cluster.
     * The common things that have to be set-up is to prepare the directory hierarchy, 
     * to enable accounting and to apply space quotas. 
     *
     * @param userAccount The target user 
     * @param serverHost The server host for the JupyterHub application 
     * @param spaceQuota A hard limit (bytes) for the space that can be used. If <tt>null</tt>,
     *    defaults will be used. 
     * 
     * @return True if the operation is successful; Otherwise false is returned
     */
    boolean setupDirs(AccountInfo userAccount, String serverHost, Long spaceQuota);
    
    /**
     * Cleanup a user's data directory for the JupyterHub cluster.
     * This is basically to disable accounting and quota enforcement.
     * 
     * @param userAccount The target user 
     * @param serverHost The server host for the JupyterHub application 
     * @param deleteDirs A flag indicating if user directory should also be (recursively) deleted
     * @return
     */
    boolean cleanupDirs(AccountInfo userAccount, String serverHost, boolean deleteDirs);
    
    /**
     * Report usage of user data directory. 
     * 
     * @param userAccount
     */
    UserDataReport reportUsage(AccountInfo userAccount);
}
