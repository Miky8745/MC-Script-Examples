public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);

        code.output.write("str", "Hello there!!!", "lol");
    }
}
