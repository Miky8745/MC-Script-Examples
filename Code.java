public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);
        code.executeCode(code::execute);
    }

    public void execute() {
        Integer rawState = input.readInt("state");
        if (nullCheck(rawState)) {return;}
        int state = Integer.parseInt(String.valueOf(rawState));
        String value = String.valueOf(state == 1 ? 0 : 1);
        output.memory.write("int", value, "state");
        if (state == 1) {
            output.turnOnRedstoneSignal();
        }
    }
}
