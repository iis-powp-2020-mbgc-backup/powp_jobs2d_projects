package edu.kis.powp.jobs2d.drivers;

public interface InkOperator {
    void restoreInk(double amount);
    double getInkLimit();
    void setIsAvailable();
}
