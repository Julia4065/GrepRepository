import java.io.IOException;
import java.util.Arrays;

public class GrepLineArgs {

    private String[] args;
    private String inputFilePath;
    private Iterable<String> greppedWords;
    private String resultFilePath;

    public GrepLineArgs(String[] args) {
        if (args.length == 1) this.args = args[0].split(" ");
        else this.args = args;

        for (int i = 0; i < this.args.length; i++) {
            switch (this.args[i]) {
                case "-f":
                    this.inputFilePath = this.args[i + 1];
                    break;
                case "-o":
                    this.resultFilePath = this.args[i + 1];
                    break;
                case "-w":
                    this.greppedWords = Arrays.asList(this.args[i + 1].split("\\|"));
                    break;
            }
        }
    }

    public File inputFile() {
        return new File(inputFilePath);
    }

    public Iterable<String> greppedWords() {
        return greppedWords;
    }

    public File resultFile() {
        return new File(resultFilePath);
    }

}
