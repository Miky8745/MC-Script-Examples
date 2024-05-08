import java.util.Objects;

public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);

        code.output.print(String.valueOf(code.input.getIncomingPower()));
        code.output.print(String.valueOf(code.input.getIncomingPower() > 0));

        if (code.input.getIncomingPower() > 0) {
            code.output.write("str", "Hello world!", "test");
        }

        code.output.print(String.valueOf(Objects.equals(code.input.readString("test"), "Hello world!")) + " final");

        if (Objects.equals(code.input.readString("test"), "Hello world!")) {
            code.output.print("OOOOOOOOOOOOOOO");
        }

        code.end();
    }
}
