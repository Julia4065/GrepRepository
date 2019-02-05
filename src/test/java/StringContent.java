public class StringContent implements IContent {

    private Iterable<String> content;

    public StringContent(Iterable<String> content) {
        this.content = content;
    }

    @Override
    public Iterable<String> textLines() {
        return content;
    }
}
