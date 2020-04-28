package edu.kis.powp.jobs2d.command;

/**
 * Interface for objects using visitor.
 */
public interface Visitable {
    void accept(Visitor visitor);
}
