import java.util.Objects;

public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);

        code.output.print(String.valueOf(code.input.getIncomingPower()));

        if (code.input.getIncomingPower() > 0 && !Objects.equals(code.input.readString("test"), "Hello World!")) {
            code.output.write("str", "Hello world!", "test");
        }

        if (Objects.equals(code.input.readString("test"), "Hello world!")) {
            code.output.print("OOOOOOOOOOOOOOO");
        }
    }
}
