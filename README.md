# How to Use the MC-Script Mod

## Starter Guide

### Installation

1. Download the jar file and place it into your mods folder.
2. If you haven't already, download Forge for version 1.20.1.
3. Launch the game.

### Setting Up the CPU Block

1. Place the CPU block.
2. Install any additional components such as redstone input or output.
3. Download the `MCScriptHelperClass` from this repository.

### First Program

Every program you write must be named `Code` and should extend the `MCScriptHelperClass`.

```java
public class Code extends MCScriptHelperClass {

    public Code(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        Code code = new Code(args);
        code.executeCode(code::execute);
    }

    public void execute() {
        output.print("Hello world!");
    }
}
```

This simple method will print 'Hello world!' to the chat every second.

#### Uploading the Code

Now that you have a simple code, let's execute it.

1. Create a GitHub repository and upload the file to it.
2. Open the file and click "Raw."
3. Copy the URL of the raw file.
4. Shorten the URL using a URL shortener.
5. In Minecraft, obtain a "Source Code Loader" item and rename it in an anvil to the shortened URL, ensuring it begins with 'https://'.
6. Open the CPU block GUI by right-clicking and insert the renamed item.
7. After a few seconds and a lag spike, you should see the results.

## Helper Methods and How to Use Them

Now that you've created a simple code for printing 'Hello world!' to chat, let's explore additional functionalities using two subclasses: `Input` and `Output`.

### Input

Reference it using the `input` variable in the `MCScriptHelperClass`.

- `input.getIncomingPower()`: Returns an integer of the incoming signal strength.
- `input.hasMemory()`: Returns true if it received any information about memory state.
- `input.readString(String topic)`: Returns a string representation of the information saved under the specified topic, or null if nothing is saved.
- `input.readInt(String topic)`: Returns an integer representation of the information saved under the specified topic, or null if nothing is saved. May throw NumberFormatException.
- `input.readFloat(String topic)`: Returns a float representation of the information saved under the specified topic, or null if nothing is saved. May throw NumberFormatException.

### Output

Reference it using the `output` variable in the `MCScriptHelperClass`.

- `output.print(String message)`: Displays the specified message in chat.

#### Output.Redstone

Reference it using the `redstone` variable in the `Output` class.

- `output.redstone.turnOnRedstoneSignal()`: Turns on the redstone signal for 1 second.
- `output.redstone.setSignalStrength(int power)`: Saves the strength of the redstone signal. This method does not turn on the redstone signal; it only sets the power of future signals.

#### Output.Memory

Reference it using the `memory` variable in the `Output` class.

- `output.memory.write(String datatype, String value, String topic)`: Writes data to memory under the specified topic. `datatype` should be one of 'str' for String, 'int' for Integer, or 'float' for Float.
- `output.memory.write(String datatype, String value, String topic, int address)`: Writes data to memory under the specified topic at the specified address. `address` should be an integer between 0 and 15.
- `output.memory.delete(String topic)`: Deletes all data, including the topic, stored under the specified topic.
- `output.memory.delete(int address)`: Deletes all data stored at the specified memory address.
- `output.memory.clear()`: Deletes all data stored in memory; this method is called every time you upload a new program to the CPU.