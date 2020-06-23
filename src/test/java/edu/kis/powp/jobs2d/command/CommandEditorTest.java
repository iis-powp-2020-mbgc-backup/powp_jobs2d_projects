package edu.kis.powp.jobs2d.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandEditorTest {

    @Test
    void editor() {
        SetPositionCommand setPositionCommand = new SetPositionCommand(1, 1);
        CommandEditor editorS = new CommandEditor(setPositionCommand);

        editorS.edit(2, 3);
        SetPositionCommand resultS = (SetPositionCommand) editorS.getCommand();
        assertEquals(resultS.getPosX(), 2);
        assertEquals(resultS.getPosY(), 3);

        OperateToCommand operateToCommand = new OperateToCommand(1, 1);
        CommandEditor editorO = new CommandEditor(operateToCommand);

        editorO.edit(2, 3);
        OperateToCommand resultO = (OperateToCommand) editorO.getCommand();
        assertEquals(resultO.getPosX(), 2);
        assertEquals(resultO.getPosY(), 3);

        ArrayList<DriverCommand> comms = new ArrayList<>();
        comms.add(setPositionCommand);
        comms.add(operateToCommand);
        ICompoundCommand compoundCommand = new ImmutableComplexCommand(comms);
        CommandEditor editorC = new CommandEditor(compoundCommand);

        editorC.edit(0, 1);
        ICompoundCommand resultC = (ICompoundCommand) editorC.getCommand();
        Iterator<DriverCommand> it = resultC.iterator();
        assertEquals(it.next(), operateToCommand);
        assertEquals(it.next(), setPositionCommand);
    }
}