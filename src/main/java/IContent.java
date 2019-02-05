import java.io.IOException;

public interface IContent {
    Iterable<String> textLines() throws IOException;
}
