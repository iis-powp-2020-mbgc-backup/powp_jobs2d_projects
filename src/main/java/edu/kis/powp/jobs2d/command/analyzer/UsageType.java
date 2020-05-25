package edu.kis.powp.jobs2d.command.analyzer;

/**
 * This enum was defined to determine what activity is done by device, for example
 * if device head executes SetPositionCommand command it means that head of device
 * only READ the state, and moves freely to other place, so it uses less energy and resources.
 * However if mentioned device executes OperateToCommand command it means, that, apart from moving
 * to exact point, it uses head to WRITE some pixels/information on i.e. some material that results
 * in higher cost of energy and resources.
 */
public enum UsageType {
    READ,WRITE
}
