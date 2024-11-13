import java.io.IOException;
public interface Writer {
  void writeUsers(String username,String email,String password,String type)throws IOException;
}

