package edu.kis.powp.jobs2d.command;

interface Visitable {
    public double accept(CommandVisitor visitor);
}
