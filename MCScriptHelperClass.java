public class MCScriptHelperClass {
    private String[] args;
    private MemoryData<?>[] memoryData;
    public Input input = new Input();
    public Output output = new Output();
    private StringBuilder result = new StringBuilder();
    private final boolean hasMemory;

    public MCScriptHelperClass(String[] args) {
        this.args = args;
        if (args.length >= 2) {
            hasMemory = true;
            int length = args[1].split("@@").length;
            this.memoryData = new MemoryData[length];
            loadMemory();
        } else {
            hasMemory = false;
        }
    }

    public void executeCode(Runnable executable) {
        executable.run();
        end();
    }

    private void loadMemory() {
        String rawMemoryData = args[1];
        String[] lines = rawMemoryData.split("@@");

        for (int i = 0; i < lines.length; i++) {
            memoryData[i] = Utils.getMemoryDataFromString(lines[i]);
        }
    }

    public void end() {
        System.out.println(result.toString());
    }

    public boolean nullCheck(Object o) {
        return o == null;
    }

    private static class Utils {

        private static MemoryData<?> getMemoryDataFromString(String line) {
            String[] parts = line.split("#");
            if (parts.length < 3) {
                return null;
            }
            String topic = toNormal(parts[0].strip());
            String tClass = parts[1];
            String value = toNormal(parts[2].strip());

            switch (tClass) {
                case "java.lang.Integer" -> {
                    Integer object = Integer.parseInt(value);
                    return new MemoryData<>(Integer.class, object, topic);
                }
                case "java.lang.Float" -> {
                    Float object = Float.parseFloat(value);
                    return new MemoryData<>(Float.class, object, topic);
                }
                case "java.lang.String" -> {
                    String object = String.valueOf(value);
                    return new MemoryData<>(String.class, object, topic);
                }
                default -> {
                    return null;
                }
            }
        }

        private static String toNormal(String text) {
            String[] parts = text.split("\\*\\*\\*");
            StringBuilder builder = new StringBuilder();
            for (String part : parts) {
                builder.append(part).append(" ");
            }
            return parts.length > 1 ? builder.toString() : text;
        }
    }

    class Input {
        public int getIncomingPower() {
            return Integer.parseInt(args[0]);
        }

        public boolean hasMemory() {
            return hasMemory;
        }

        public String readString(String topic) {
            if (!hasMemory) {return null;}
            for (MemoryData<?> item : memoryData) {
                if (item == null) {continue;}
                if (item.topic.equals(topic)) {
                    return item.get().toString();
                }
            }

            return null;
        }

        public Integer readInt(String topic) {
            if (!hasMemory) {return null;}
            for (MemoryData<?> item : memoryData) {
                if (item.topic.equals(topic)) {
                    return Integer.valueOf(item.get().toString());
                }
            }

            return null;
        }

        public Float readFloat(String topic) {
            if (!hasMemory) {return null;}
            for (MemoryData<?> item : memoryData) {
                if (item.topic.equals(topic)) {
                    return Float.valueOf(item.get().toString());
                }
            }

            return null;
        }
    }

    class Output {
        public Memory memory = new Memory();
        public Redstone redstone = new Redstone();

        class Memory {
            public void write(String datatype, String value, String topic) {
                value = parseValue(value);
                topic = parseValue(topic);
                String cmd = "write#" + datatype + "#" + value + "#" + topic;
                makeSyscall(cmd);
            }

            public void delete(String topic) {
                topic = parseValue(topic);
                String cmd = "delete#0#" + topic;
                makeSyscall(cmd);
            }

            public void delete(int address) {
                String cmd = "delete#1" + address;
                makeSyscall(cmd);
            }

            public void clear() {
                String cmd = "clear#null";
                makeSyscall(cmd);
            }

            public void write(String datatype, String value, String topic, int address) {
                value = parseValue(value);
                topic = parseValue(topic);
                String cmd = "writeIndex#" + address + "#" + datatype + "#" + value + "#" + topic;
                makeSyscall(cmd);
            }
        }

        class Redstone {
            public void turnOnRedstoneSignal() {
                String cmd = "turnOn#null";
                makeSyscall(cmd);
            }

            public void setSignalStrength(int power) {
                String cmd = "setSignalStrength#" + power;
                makeSyscall(cmd);
            }
        }

        public void print(String message) {
            result.append(message).append("@@");
        }

        private void makeSyscall(String cmd) {
            print("syscall&" + cmd);
        }

        private String parseValue(String value) {
            String[] parts = value.split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                builder.append(part);
                if (i < parts.length-1) {
                    builder.append("***");
                }
            }
            return builder.toString();
        }
    }

    private static class MemoryData<T> {

        private Class<T> tClass;
        private T data;
        private String topic;

        public MemoryData(Class<T> tClass, T data, String topic) {
            this.tClass = tClass;
            this.data = data;
            this.topic = topic;
        }

        public Class<T> gettClass() {
            return this.tClass;
        }

        public T get() {
            return this.data;
        }

        public String getTopic() {
            return this.topic;
        }
    }
}