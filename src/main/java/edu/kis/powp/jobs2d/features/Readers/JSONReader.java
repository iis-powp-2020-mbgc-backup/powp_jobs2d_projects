package edu.kis.powp.jobs2d.features.Readers;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.ComplexCommand;

import java.util.ArrayList;

public class JSONReader implements Reader{

    @Override
    public ComplexCommand read(String contentToParse) {
        return new ComplexCommand(null);
    }

}
