package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void get() {
        CommandFactory cf = new CommandFactory();
        assertNull(cf.get(null));
        assertNull(cf.get(""));
        assertNull(cf.get("test"));

        List<DriverCommand> dcList = new ArrayList<>();
        dcList.add(new SetPositionCommand(100, 100));
        dcList.add(new OperateToCommand(150, 150));
        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);

        cf.add("line", icc);

        assertNotEquals(icc, cf.get("line"));

        List<DriverCommand> listOriginal = new ArrayList<>();
        icc.forEach(listOriginal::add);

        List<DriverCommand> listFromfactory = new ArrayList<>();
        cf.get("line").forEach(listFromfactory::add);

        assertEquals(listOriginal.size(), listFromfactory.size());

        for (int i = 0; i < listOriginal.size(); i++) {
            if(listOriginal.get(i) instanceof OperateToCommand){
                assert(listFromfactory.get(i) instanceof OperateToCommand);
                OperateToCommand a1 = (OperateToCommand)listOriginal.get(i);
                OperateToCommand a2 = (OperateToCommand)listOriginal.get(i);
                assertEquals(a1.getX(), a2.getX());
                assertEquals(a1.getY(), a2.getY());
            }
            if(listOriginal.get(i) instanceof SetPositionCommand){
                assert(listFromfactory.get(i) instanceof SetPositionCommand);
                SetPositionCommand a1 = (SetPositionCommand)listOriginal.get(i);
                SetPositionCommand a2 = (SetPositionCommand)listOriginal.get(i);
                assertEquals(a1.getX(), a2.getX());
                assertEquals(a1.getY(), a2.getY());
            }

        }

    }

//    @Test
//    void add() {
//    }

    @Test
    void getNamesOfStored() {
        CommandFactory cf = new CommandFactory();

        List<DriverCommand> dcList = new ArrayList<>();
        dcList.add(new SetPositionCommand(100, 100));
        dcList.add(new OperateToCommand(150, 150));
        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);

        cf.add("line", icc);
        cf.add("circle", icc);
        cf.add("square", icc);

        assert(cf.getNamesOfStored().contains("line"));
        assert(cf.getNamesOfStored().contains("circle"));
        assert(cf.getNamesOfStored().contains("square"));

    }

    @Test
    void remove() {
        CommandFactory cf = new CommandFactory();

        List<DriverCommand> dcList = new ArrayList<>();
        dcList.add(new SetPositionCommand(100, 100));
        dcList.add(new OperateToCommand(150, 150));
        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);

        cf.add("line", icc);
        cf.add("circle", icc);
        cf.add("square", icc);

        cf.remove("square");

        assert(cf.getNamesOfStored().contains("line"));
        assert(cf.getNamesOfStored().contains("circle"));
        assert(!cf.getNamesOfStored().contains("square"));

        cf.remove("circle");

        assert(cf.getNamesOfStored().contains("line"));
        assert(!cf.getNamesOfStored().contains("circle"));
        assert(!cf.getNamesOfStored().contains("square"));

        cf.remove("line");

        assert(!cf.getNamesOfStored().contains("line"));
        assert(!cf.getNamesOfStored().contains("circle"));
        assert(!cf.getNamesOfStored().contains("square"));
    }

    @Test
    void clear() {
        CommandFactory cf = new CommandFactory();

        List<DriverCommand> dcList = new ArrayList<>();
        dcList.add(new SetPositionCommand(100, 100));
        dcList.add(new OperateToCommand(150, 150));
        ImmutableCompoundCommand icc = new ImmutableCompoundCommand(dcList);

        cf.add("line", icc);
        cf.add("circle", icc);
        cf.add("square", icc);

        cf.clear();

        assert(cf.getNamesOfStored().size() == 0);

        assert(!cf.getNamesOfStored().contains("line"));
        assert(!cf.getNamesOfStored().contains("circle"));
        assert(!cf.getNamesOfStored().contains("square"));
    }
}