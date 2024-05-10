public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);
        code.executeCode(code::execute);
    }

    public void execute() {
        Integer initialized = input.readInt("init");
        if (nullCheck(initialized)) {
            output.memory.write("int", "1", "init");
            output.memory.write("int", "0", "power");
            return;
        }

        Integer strength = input.readInt("power");
        if (nullCheck(strength)) {return;}
        int power = Integer.parseInt(String.valueOf(strength));
        if (input.getIncomingPower() > 0) {
            output.redstone.turnOnRedstoneSignal();
            int value = power+1 > 15 ? 0 : power + 1;
            output.memory.write("int", String.valueOf(value), "power");
            output.redstone.setSignalStrength(power);
        } else {
            output.redstone.setSignalStrength(0);
            output.memory.write("int", "0", "power");
        }
    }
}
