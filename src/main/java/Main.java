import java.io.IOException;

public class Main {

    public static void main(String[] rawArgs) throws IOException {

        GrepLineArgs grepArgs = new GrepLineArgs(rawArgs);

        grepArgs.resultFile().put(new Grepped(grepArgs.inputFile(), grepArgs.greppedWords()));
    }
}
