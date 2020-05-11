package edu.kis.powp.jobs2d.command;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableCompoundCommandTest {

    @Test
    void iterator() {

        List<DriverCommand> dcList = new ArrayList<>();
        SetPositionCommand spc = new SetPositionCommand(10, 10);
        dcList.add(spc);
        dcList.add(new OperateToCommand(10, 10));
        dcList.add(new SetPositionCommand(10, 10));
        dcList.add(new OperateToCommand(10, 10));


        List<DriverCommand> compoundList = new ArrayList<>();
        compoundList.add(new OperateToCommand(20, 20));
        compoundList.add(new OperateToCommand(20, 20));
        dcList.add(new DefaultCompoundCommand(compoundList));
        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);
        int compoundListInitialSize = compoundList.size();
        int initialListLength = dcList.size();
        spc = new SetPositionCommand(99, 99);
        dcList.add(spc);
        compoundList.add(new OperateToCommand(20, 20));


        Iterator<DriverCommand> iter = icc.iterator();
        int i = 0;
        while(iter.hasNext()){
            Object obj = iter.next();
            i++;
            //assert that no element can be removed from list
            assertThrows(UnsupportedOperationException.class, iter::remove);
            if(obj instanceof DefaultCompoundCommand){
                DefaultCompoundCommand dcc = (DefaultCompoundCommand)obj;
                AtomicInteger j = new AtomicInteger();
                dcc.forEach(el -> j.getAndIncrement());
                //assert that size of internal DefaultCompoundCommand is unchanged
                Assert.assertEquals(compoundListInitialSize, j.get());
            }
        }
        //assert that list length is unchanged
        assertEquals(i, initialListLength);

    }

}