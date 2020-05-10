package edu.kis.powp.jobs2d.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableCompoundCommandTest {

    @Test
    void iterator() {

        List<DriverCommand> dcList = new ArrayList<>();
        dcList.add(new SetPositionCommand(10, 10));
        dcList.add(new OperateToCommand(10, 10));
        dcList.add(new SetPositionCommand(10, 10));
        dcList.add(new OperateToCommand(10, 10));

        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);

        Iterator<DriverCommand> iter = icc.iterator();
        while(iter.hasNext()){
            DriverCommand dc = iter.next();
            Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
                iter.remove();
            });
        }

    }

}