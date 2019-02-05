import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class GrepLineArgsTest {

    private static final String ARG_LINE = "java -jar Grep.jar -f readFromThisFile.txt -w dear|mother|hello -o writeInThisFile.txt";
    private static final String ARG_LINE2 = "java -jar Grep.jar -w dear|mother|hello -o writeInThisFile.txt -f readFromThisFile.txt";

    @Parameterized.Parameter
    public String argLineParam;


    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                { ARG_LINE },
                { ARG_LINE2 }
        });
    }


    @Test
    public void gets_input_file() throws IOException {
        //arrange
        String[] argLine = new String[]{argLineParam};
        GrepLineArgs grepLineArgs = new GrepLineArgs(argLine);

        //act
        String actual = grepLineArgs.inputFile().getPath();

        //assert
        String expected = "readFromThisFile.txt";
        assertThat("Incorrect input file name", actual, is(equalTo(expected)));
    }

    @Test
    public void extracts_grepped_words() {
        //arrange
        String[] argLine = new String[]{ARG_LINE2};
        GrepLineArgs grepLineArgs = new GrepLineArgs(argLine);

        //act
        Iterable<String> actual = grepLineArgs.greppedWords();

        //assert
        List<String> expected = Arrays.asList("dear", "mother", "hello");
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void gets_result_file() {
        //arrange
        String[] argLine = new String[]{ARG_LINE2};
        GrepLineArgs grepLineArgs = new GrepLineArgs(argLine);

        //act
        String actual = grepLineArgs.resultFile().getPath();

        //assert
        String expected = "writeInThisFile.txt";
        assertThat("Incorrect result file", actual, is(equalTo(expected)));
    }


}
