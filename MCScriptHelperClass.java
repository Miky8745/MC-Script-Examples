public class MCScriptHelperClass {

    private String[] args;
    private MemoryData<?>[] memoryData;
    public Input input = new Input();
    public Output output = new Output();
    private StringBuilder result = new StringBuilder();

    public MCScriptHelperClass(String[] args) {
        this.args = args;
        if (args.length >= 2) {
            int length = args[1].split("@@").length;
            this.memoryData = new MemoryData[length];
            loadMemory();
        }
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

    private static class Utils {

        private static MemoryData<?> getMemoryDataFromString(String line) {
            String[] parts = line.split("#");
            if (parts.length < 3) {
                return null;
            }
            String topic = parts[0];
            String tClass = parts[1];
            String value = parts[2];

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
    }

    class Input {
        public int getIncomingPower() {
            return Integer.parseInt(args[0]);
        }

        public String readString(String topic) {
            for (MemoryData<?> item : memoryData) {
                if (item == null) {continue;}
                if (item.topic.equals(topic)) {
                    return item.get().toString();
                }
            }

            return null;
        }

        public Integer readInt(String topic) {
            for (MemoryData<?> item : memoryData) {
                if (item.topic.equals(topic)) {
                    return Integer.valueOf(item.get().toString());
                }
            }

            return null;
        }

        public Float readFloat(String topic) {
            for (MemoryData<?> item : memoryData) {
                if (item.topic.equals(topic)) {
                    return Float.valueOf(item.get().toString());
                }
            }

            return null;
        }
    }

    class Output {
        public void write(String datatype, String value, String topic) {
            String cmd = "write#" + datatype + "#" + value + "#" + topic;
            makeSyscall(cmd);
        }

        public void print(String message) {
            result.append(message).append("@@");
        }

        public void write(String datatype, String value, String topic, int address) {
            String cmd = "writeIndex#" + address + "#" + datatype + "#" + value + "#" + topic;
            makeSyscall(cmd);
        }

        private void makeSyscall(String cmd) {
            print("syscall&" + cmd);
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