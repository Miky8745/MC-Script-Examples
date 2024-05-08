public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);
        code.execute();
        code.end();
    }

    public void execute() {
        if (input.getIncomingPower() > 0) {
            output.write("str", "Hello world!", "test");
        }

        String test = input.readString("test");
        if (nullCheck(test)) {return;}
        this.output.print(test);

        if (input.readString("test").equals("Hello world!")) {
            output.print("OOOOOOOOOOOOOOO");
        }
    }
}
