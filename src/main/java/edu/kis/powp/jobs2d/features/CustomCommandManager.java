package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.ComplexCommand;
import edu.kis.powp.jobs2d.features.Readers.Reader;

public class CustomCommandManager {

    private static ComplexCommand complexCommand;
    private static String customStringCommand;

    public static ComplexCommand createComplexCommand(Reader reader) {
        complexCommand = reader.read(customStringCommand);
        return complexCommand;
    }

    public static ComplexCommand getComplexCommand() {
        return complexCommand;
    }

    public static void setCustomStringCommand(String string) {
        customStringCommand = string;
    }
}
