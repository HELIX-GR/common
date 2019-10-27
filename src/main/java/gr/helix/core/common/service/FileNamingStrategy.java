package gr.helix.core.common.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gr.helix.core.common.model.DirectoryInfo;

public interface FileNamingStrategy
{
    /**
     * Get detailed info on a user's home directory.
     * 
     * @param userName
     * @return 
     * @throws IOException
     */
    default DirectoryInfo getUserDirectoryInfo(String userName) throws IOException
    {
        return DirectoryInfo.createDirectoryInfo("", this.getUserDir(userName), "/");
    }
    
    /**
     * Resolve a user's home directory as an absolute path.
     *
     * <p>This method will not interact in any way with the underlying filesystem; will
     * simply map a user id to a home directory.
     *
     * @param userName
     */
    Path getUserDir(String userName);

    /**
     * Resolve a user's home directory as an absolute path. If <tt>createIfNotExists</tt> is set, attempt
     * to create an empty home directory (if it doesn't already exist).
     *
     * @param userName
     * @param createIfNotExists
     * @throws IOException if an attempt to create the directory fails
     */
    default Path getUserDir(String userName, boolean createIfNotExists) throws IOException
    {
        final Path userDir = this.getUserDir(userName);
        if (createIfNotExists) {
            Files.createDirectories(userDir);
        }
        return userDir;
    }

    /**
     * Resolve a path against a user's home directory
     *
     * @param userName
     * @param relativePath A relative path to be resolved
     * @return an absolute path
     */
    default Path resolvePath(String userName, String relativePath)
    {
        return this.resolvePath(userName, Paths.get(relativePath));
    }
    
    /**
     * Resolve a path against a user's home directory
     * @see FileNamingStrategy#resolvePath(int, String)
     */
    Path resolvePath(String userName, Path relativePath);
}