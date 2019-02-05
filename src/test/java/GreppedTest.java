import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GreppedTest {

    private final String TEST_PATH = "C:/Users/Iuliia Kuznietsova/IdeaProjects/Grep/testDirectory";

    @Before
    public void cleanUp() throws IOException {
        FileUtils.cleanDirectory(new java.io.File(TEST_PATH));
    }

    @Test
    public void filters_text_with_grepped_word() throws IOException {
        //act
        Iterable<String> grepWords = Arrays.asList("line2");
        Grepped grepped = new Grepped(new StringContent(Arrays.asList("line1 line2", "line3 line4", "line5 line6")), grepWords);
        Iterable<String> actualTextLines = grepped.textLines();

        //assert
        StringContent expectedTextLines = new StringContent(Arrays.asList("line1 line2"));
        assertThat(actualTextLines, is(equalTo(expectedTextLines.textLines())));
    }

    @Test
    public void filters_text_with_grepped_words() throws IOException {
        //act
        Iterable<String> grepWords = Arrays.asList("line2|line5".split("\\|"));
        Grepped grepped = new Grepped(new StringContent(Arrays.asList("line1 line2", "line3 line4", "line5 line6")), grepWords);

        //assert
        Iterable<String> actualTextLines = grepped.textLines();
        StringContent expectedTextLines = new StringContent(Arrays.asList("line1 line2", "line5 line6"));
        assertThat(actualTextLines, is(equalTo(expectedTextLines.textLines())));
    }


}
