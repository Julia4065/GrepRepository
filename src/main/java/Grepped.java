import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.StreamSupport;

public class Grepped implements IContent {

    IContent content;
    Iterable<String> grepWords;

    public Grepped(IContent content, Iterable<String> grepWords) {
        this.content = content;
        this.grepWords = grepWords;
    }

    @Override
    public Iterable<String> textLines() throws IOException {
        Iterable<String> result = new ArrayList<>();

        String[] grepped = StreamSupport.stream(grepWords.spliterator(), false).toArray(String[]::new);
//        String[] grepped = StreamSupport.stream(grepWords.spliterator(), false).toArray(String[]::new)[0].split("\\|");

        if (grepped[0].matches(" ")) {
            throw new UnsupportedOperationException("Please specify grep words.");
        }
        for (String line : content.textLines()) {
            if (Arrays.stream(grepped).parallel().anyMatch(line::contains)) {
                ((ArrayList<String>) result).add(line);
            }
        }
        return result;
    }
}
