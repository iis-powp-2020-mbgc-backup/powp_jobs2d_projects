package edu.kis.powp.jobs2d.command;

import java.util.List;

public interface CommandReader {
    List<DriverCommand> load(String command);
}
