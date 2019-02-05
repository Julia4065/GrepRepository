import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.commons.io.FileUtils;


public class FileTest {

    private final String TEST_PATH = "C:/Users/Iuliia Kuznietsova/IdeaProjects/Grep/testDirectory";

    @Before
    public void cleanUp() throws IOException {
        FileUtils.cleanDirectory(new java.io.File(TEST_PATH));
    }

    @Test
    public void puts_a_content() throws IOException {
        //arrange
        StringContent expectedStringContent = new StringContent(Arrays.asList("line1 line2", "line3 line4"));

        //act
        File file = new File(TEST_PATH + "/testFile.txt");
        file.put(expectedStringContent);

        //assert
        Iterable<String> actualStringContent = Files.readAllLines(Paths.get(TEST_PATH + "/testFile.txt"));
        assertThat(actualStringContent, is(equalTo(expectedStringContent.textLines())));
    }

    @Test
    public void gets_textLines() throws IOException {
        //arrange
        StringContent expectedStringContent = new StringContent(Arrays.asList("line1 line2", "line3 line4"));
        Files.write(Paths.get(TEST_PATH + "/testFile.txt"), expectedStringContent.textLines());

        //act
        File file = new File(TEST_PATH + "/testFile.txt");
        Iterable<String> actualStringContent = file.textLines();

        //assert
        assertThat(actualStringContent, is(equalTo(expectedStringContent.textLines())));
    }
}