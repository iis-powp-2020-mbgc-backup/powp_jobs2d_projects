package edu.kis.powp.jobs2d.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandEditorTest {

    @Test
    void editor() {
        SetPositionCommand setPositionCommand = new SetPositionCommand(1, 1);
        CommandEditor editorS = new CommandEditor(setPositionCommand);
        assertThrows(ImproperCommandTypeException.class, () -> editorS.changeOrder(1, 1));
        try {
            editorS.changeCoordinates(2, 3);
            SetPositionCommand result = (SetPositionCommand) editorS.getCommand();
            assertEquals(result.getPosX(), 2);
            assertEquals(result.getPosY(), 3);
        } catch (ImproperCommandTypeException e) {
            e.printStackTrace();
        }

        OperateToCommand operateToCommand = new OperateToCommand(1, 1);
        CommandEditor editorO = new CommandEditor(operateToCommand);
        assertThrows(ImproperCommandTypeException.class, () -> editorO.changeOrder(1, 1));
        try {
            editorO.changeCoordinates(2, 3);
            OperateToCommand result = (OperateToCommand) editorO.getCommand();
            assertEquals(result.getPosX(), 2);
            assertEquals(result.getPosY(), 3);
        } catch (ImproperCommandTypeException e) {
            e.printStackTrace();
        }

        ArrayList<DriverCommand> comms = new ArrayList<>();
        comms.add(setPositionCommand);
        comms.add(operateToCommand);
        ICompoundCommand compoundCommand = new ImmutableComplexCommand(comms);
        CommandEditor editorC = new CommandEditor(compoundCommand);
        assertThrows(ImproperCommandTypeException.class, () -> editorC.changeCoordinates(1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> editorC.changeOrder(3, 1));
        try {
            editorC.changeOrder(0, 1);
            ICompoundCommand result = (ICompoundCommand) editorC.getCommand();
            Iterator<DriverCommand> it = result.iterator();
            assertEquals(it.next(), operateToCommand);
            assertEquals(it.next(), setPositionCommand);
        } catch (ImproperCommandTypeException e) {
            e.printStackTrace();
        }
    }
}
