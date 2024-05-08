public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);

        if (code.input.getIncomingPower() > 0) {
            code.output.write("str", "Hello world!", "test");
        }

        code.output.print(code.input.readString("test"));

        if (code.input.readString("test").equals("Hello world!")) {
            code.output.print("OOOOOOOOOOOOOOO");
        }

        code.end();
    }
}
