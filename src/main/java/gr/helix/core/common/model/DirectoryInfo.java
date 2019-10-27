package gr.helix.core.common.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A file system entry for a directory
 */
public class DirectoryInfo extends FileSystemEntry {

    private List<FileInfo> files = new ArrayList<FileInfo>();

    private List<DirectoryInfo> folders = new ArrayList<DirectoryInfo>();

    public DirectoryInfo(String name, String path, ZonedDateTime createdOn, String type) {
        super(0, name, path, createdOn, "Folder");
    }
    
    public DirectoryInfo(String name, String path, long createdOnEpoch, String type) {
        super(0, name, path, toDateTime(createdOnEpoch), "Folder");
    }

    public DirectoryInfo(String name, String path, ZonedDateTime createdOn, String type, List<FileInfo> files, List<DirectoryInfo> folders) {
        super(0, name, path, createdOn, type);
        this.files.addAll(files);
        this.folders.addAll(folders);
    }
    
    public DirectoryInfo(String name, String path, long createdOnEpoch, String type, List<FileInfo> files, List<DirectoryInfo> folders) {
        super(0, name, path, toDateTime(createdOnEpoch), type);
        this.files.addAll(files);
        this.folders.addAll(folders);
    }

    public List<FileInfo> getFiles() {
        files.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
        return Collections.unmodifiableList(files);
    }

    public List<DirectoryInfo> getFolders() {
        folders.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
        return Collections.unmodifiableList(folders);
    }

    public int getCount() {
        return (files.size() + folders.size());
    }

    @Override
    public long getSize() {
        return files.stream().mapToLong(f -> f.getSize()).sum() + folders.stream().mapToLong(f -> f.getSize()).sum();
    }

    public void addFile(FileInfo fi) {
        Optional<FileInfo> existing = files.stream().filter(f -> f.getName().equalsIgnoreCase(fi.getName())).findFirst();
        if (existing.isPresent()) {
            this.files.remove(existing.get());
        }
        this.files.add(fi);
    }

    public void addFolder(DirectoryInfo di) {
        Optional<DirectoryInfo> existing = folders.stream().filter(d -> d.getName().equalsIgnoreCase(di.getName())).findFirst();
        if (existing.isPresent()) {
            this.folders.remove(existing.get());
        }
        this.folders.add(di);
    }
    
    //
    // Utilities
    //
    
    public static DirectoryInfo createDirectoryInfo(String name, Path path, String relativePath)
    {
        if (!Files.isDirectory(path) || !Files.isReadable(path))
            return null;
        
        final File file = path.toFile();

        final DirectoryInfo di = new DirectoryInfo(name, relativePath, file.lastModified(), "Folder");

        for (final File f : file.listFiles()) {
            if (!f.getName().startsWith(".")) {
                if (f.isDirectory()) {
                    di.addFolder(createDirectoryInfo(f.getName(), f.toPath(), relativePath + f.getName() + "/"));
                }
                if (f.isFile()) {
                    di.addFile(createFileInfo(f, relativePath));
                }
            }
        }

        return di;
    }

    public static FileInfo createFileInfo(File file, String path)
    {
        return new FileInfo(file.length(), file.getName(), path + file.getName(), file.lastModified(), "File");
    }
    
}