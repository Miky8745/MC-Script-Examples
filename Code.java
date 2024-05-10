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
            output.memory.write("int", "1", "works?");
        } else {
            output.memory.delete("test");
        }

        String isPowered = input.readString("test");
        if (!nullCheck(isPowered)) {
            output.print(isPowered);
        }

        Integer works = input.readInt("works?");
        if(!nullCheck(works)) {
            output.print(String.valueOf(works));
        }
    }
}
