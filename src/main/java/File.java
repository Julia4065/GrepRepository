import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File implements IContent {

    private String path;

    public File(String path) {
        this.path = path;
    }

    public void put(IContent content) throws IOException {
        Files.write(Paths.get(path), content.textLines());
    }

    public Iterable<String> textLines() throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public String getPath() {
        return path;
    }
}
