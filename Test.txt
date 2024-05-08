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
        boolean isPowered = false;

        if (input.getIncomingPower() > 0) {
            output.write("str", "Hello world!", "test");
            output.write("int", "1", "time");
            isPowered = true;
        }

        String test = input.readString("test");
        if (nullCheck(test)) {return;}

        if (nullCheck(input.readInt("time"))) {return;}
        int time = input.readInt("time");
        if (!isPowered) {
            output.write("int", "0", "time");
        }
        if (time == 0) {
            output.write("str", "no", "test");
        }

        if (test.strip().equals("Hello world!")) {
            output.print("IsPowered");
        }
    }
}
