//this interface contains methods to read and write files

import java.io.IOException;

public interface ReadWriteFile
{
    public void readFile() throws IOException;
    public void writeFile() throws IOException;
}