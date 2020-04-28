package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.Visitor;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class DriverCommandVisitor implements Visitor {
    private int counter = 0;

    public int getCounter(){
        return this.counter;
    }

    @Override
    public void visit(DriverCommand driverCommand) {
        counter++;
        System.out.println("Counter: " + counter);
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        Iterator<DriverCommand> iterator = compoundCommand.iterator();
        while(iterator.hasNext()){
            counter++;
            iterator.next();
        }
        System.out.println("Counter: " + counter);
    }
}
