public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);
        code.executeCode(code::execute);
    }

    public void execute() {
        if (input.getIncomingPower() > 0) {
            output.memory.write("str", "powered", "test");
        } else {
            output.memory.clear();
        }

        String isPowered = input.readString("test");
        if (!nullCheck(isPowered)) {
            output.print(isPowered);
        }
    }
}
