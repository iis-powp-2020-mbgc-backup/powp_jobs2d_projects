package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.*;

import javax.swing.*;

public class CommandListingVisitor implements CommandVisitorInterface {
    private DefaultListModel<DriverCommand> listModel;

    public CommandListingVisitor(DefaultListModel<DriverCommand> listModel) {
        this.listModel = listModel;
    }

    @Override
    public void visit(OperateToCommand driver) {
        listModel.addElement(driver);
    }

    @Override
    public void visit(SetPositionCommand driver) {
        listModel.addElement(driver);
    }

    @Override
    public void visit(ICompoundCommand driver) {
        driver.iterator().forEachRemaining(command -> command.accept(this));
    }
}