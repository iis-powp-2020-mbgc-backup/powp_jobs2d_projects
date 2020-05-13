package edu.kis.powp.jobs2d.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableComplexCommandTest {

    @Test
    void iterator() {

        List<DriverCommand> testList = new ArrayList<>();
        SetPositionCommand positionCommand = new SetPositionCommand(50, 50);
        testList.add(positionCommand);
        testList.add(new OperateToCommand(50, 50));
        testList.add(new SetPositionCommand(50, 50));
        testList.add(new OperateToCommand(50, 50));
        testList.add(new SetPositionCommand(50, 50));
        testList.add(new OperateToCommand(50, 50));


        ImmutableComplexCommand complexCommand = new ImmutableComplexCommand(testList);
        positionCommand = new SetPositionCommand(100, 100);
        testList.add(positionCommand);

       Iterator<DriverCommand> CommandIterator = complexCommand.iterator();
        int checkValue = 0;
          while(CommandIterator.hasNext()){
            DriverCommand nextIterator = CommandIterator.next();
            checkValue++;
            Exception exception = assertThrows(UnsupportedOperationException.class, CommandIterator::remove);
    }
    assertEquals(checkValue, 6);

}

}