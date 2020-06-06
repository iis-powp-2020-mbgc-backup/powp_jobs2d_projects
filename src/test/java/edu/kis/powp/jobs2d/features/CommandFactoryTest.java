package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    private static CommandFactory cf;

    private static List<DefaultCompoundCommand> testCommands;
    private static final List<String> exampleNames = Arrays.asList(
            "line_01", "line_02", "line_03", "circle", "square", "triangle");
    private static List<String> existingCommandNames;
    private static List<String> notExistingCommandNames;

    @BeforeEach
    void setUp() {
        cf = new CommandFactory();

        testCommands = new ArrayList<>();
        existingCommandNames = new ArrayList<>();
        notExistingCommandNames = new ArrayList<>();

        List<DriverCommand> dcList1 = new ArrayList<>();
        dcList1.add(new SetPositionCommand(100, 100));
        dcList1.add(new OperateToCommand(150, 150));
        testCommands.add(new DefaultCompoundCommand(dcList1, exampleNames.get(0)));

        List<DriverCommand> dcList2 = new ArrayList<>();
        dcList2.add(new SetPositionCommand(0, 0));
        dcList2.add(new OperateToCommand(50, 50));
        testCommands.add(new DefaultCompoundCommand(dcList2, exampleNames.get(1)));

        List<DriverCommand> dcList3 = new ArrayList<>();
        dcList3.add(new SetPositionCommand(0, 100));
        dcList3.add(new OperateToCommand(50, 50));
        testCommands.add(new DefaultCompoundCommand(dcList3, exampleNames.get(2)));

        testCommands.forEach(c -> existingCommandNames.add(c.toString()));

        notExistingCommandNames.add(null);
        notExistingCommandNames.add(exampleNames.get(2));
        notExistingCommandNames.add(exampleNames.get(3));
        notExistingCommandNames.add(exampleNames.get(4));


    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void getAndAdd() {

        notExistingCommandNames.forEach(name -> {
            assertNull(cf.get(name));
        });

        testCommands.forEach(command ->{
            cf.add(command);
            assertNotEquals(command, cf.get(command.toString()));
        });

        existingCommandNames.forEach(name ->{
            assertNotNull(cf.get(name));
        });

        List<DriverCommand> listOriginal = new ArrayList<>();
        testCommands.get(0).forEach(listOriginal::add);

        List<DriverCommand> listFromfactory = new ArrayList<>();
        DriverCommand dc = cf.get(testCommands.get(0).toString());
        assertTrue(dc instanceof DefaultCompoundCommand);
        DefaultCompoundCommand dcc = (DefaultCompoundCommand)dc;
        dcc.forEach(listFromfactory::add);

        assertEquals(listOriginal.size(), listFromfactory.size());

        for (int i = 0; i < listOriginal.size(); i++) {
            if(listOriginal.get(i) instanceof OperateToCommand){
                assert(listFromfactory.get(i) instanceof OperateToCommand);
                OperateToCommand a1 = (OperateToCommand)listOriginal.get(i);
                OperateToCommand a2 = (OperateToCommand)listFromfactory.get(i);
                assertEquals(a1.getX(), a2.getX());
                assertEquals(a1.getY(), a2.getY());
            }
            if(listOriginal.get(i) instanceof SetPositionCommand){
                assert(listFromfactory.get(i) instanceof SetPositionCommand);
                SetPositionCommand a1 = (SetPositionCommand)listOriginal.get(i);
                SetPositionCommand a2 = (SetPositionCommand)listFromfactory.get(i);
                assertEquals(a1.getX(), a2.getX());
                assertEquals(a1.getY(), a2.getY());
            }

        }

    }

    @Test
    void getNamesOfStored() {

        testCommands.forEach(command ->{
            cf.add(command);
        });
        assertEquals(existingCommandNames.size(), cf.getNamesOfStored().size());

        existingCommandNames.forEach(name ->{
            assertTrue(cf.getNamesOfStored().contains(name));
        });

    }

    @Test
    void remove() {
        testCommands.forEach(command ->{
            cf.add(command);
        });

        testCommands.forEach(command ->{
            assertNotNull(cf.get(command.toString()));
            cf.remove(command.toString());
            assertNull(cf.get(command.toString()));
        });

    }

    @Test
    void clear() {

    }

}
