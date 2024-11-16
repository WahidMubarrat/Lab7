import java.io.IOException;
public interface AdminWorks {
    void modifyUser(String id, String newType)throws IOException;
    boolean renameFile(String oldFilename, String newFilename) throws IOException;
}
