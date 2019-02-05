package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.lv.Un;
import io.cucumber.datatable.DataTable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CucumberSteps {

    @Before
    public void before() throws IOException {
        cleanUpResultFile("C:\\D\\IdeaProjectForGitHub\\Grep\\writeInThisFile.txt");
        cleanUpResultFile("C:\\D\\IdeaProjectForGitHub\\Grep\\readFromThisFile.txt");
    }

    @Given("I have an initial file {string} with the following content")
    public void i_have_an_initial_file_with_content(String filePath, DataTable initialContent) throws IOException {
        List<String> content = extractContentFrom(initialContent);
        writeContentIntoFile(filePath, content);
    }

    @When("I execute the following command line {string}{}{string}")
    public void i_execute_the_following_command_line(String cmdPart1, String greps, String cmdPart2) throws IOException, InterruptedException {
       java.lang.Runtime.getRuntime().exec(cmdPart1 + "\"" + greps + "\"" + cmdPart2);
       Thread.sleep(1000);
    }

    @Then("I receive a new file {string} with extracted words")
    public void i_receive_a_new_file_with_extracted_words(String filePath, DataTable resultContent) throws IOException {
        List<String> expectedContent = extractContentFrom(resultContent);
        List<String> actualContent = readContentFromFile(filePath);

        assertThat(actualContent, containsInAnyOrder(expectedContent.toArray()));
    }

    @Then("I receive an empty file {string}")
    public void i_receive_an_empty_file(String filePath) throws IOException {
        List<String> expectedContent = Collections.emptyList();
        List<String> actualContent = readContentFromFile(filePath);

        assertThat(actualContent, is(equalTo(expectedContent)));
    }

    @Then("I receive exception when I execute the following command line {string}{}{string}")
    public void I_receive_exception_when_I_execute_command(String cmdPart1, String greps, String cmdPart2) throws IOException, InterruptedException {
        Process p = null;
            p = Runtime.getRuntime().exec(cmdPart1 + "\"" + greps + "\"" + cmdPart2);
            p.waitFor();
            final int exitValue = p.waitFor();
            if (exitValue != 0) {
                try (final BufferedReader b = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                    String line;
                    if ((line = b.readLine()) != null)
                        System.out.println(line);
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }
            }
}

    private List<String> readContentFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    private void writeContentIntoFile(String filePath, List<String> content) throws IOException {
        Files.write(Paths.get(filePath), content, Charset.defaultCharset());
    }

    private void cleanUpResultFile(String filePath) throws IOException {
        PrintWriter writer = new PrintWriter(filePath);
        writer.print("");
        writer.close();
    }

    private List<String> extractContentFrom(DataTable content) {
        List<String> contentElements = content.asList();
        return contentElements;
    }
}
