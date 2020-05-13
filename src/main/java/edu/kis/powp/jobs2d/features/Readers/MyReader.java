package edu.kis.powp.jobs2d.features.Readers;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.ComplexCommand;

import java.util.ArrayList;

public class MyReader implements Reader {

    @Override
    public ComplexCommand read(String contentToParse) {

        // W TAKI SPOSOB TRZEBA WPISYWAC KOMENDY!!!
        // setPosition,1,1;
        // operateTo,10,10;
        // operateTo,100,30;

        ArrayList<DriverCommand> commandList = new ArrayList<>();

        String[] arr = contentToParse.split(";");

        for(int i=0;i<arr.length;i++) {
            System.out.println("Komenda:" + arr[i]);
            String[] command = arr[i].trim().replace("\n","").replace("\r","").split(",");
            if(command[0].equals("setPosition")) {
                commandList.add(new SetPositionCommand(Integer.valueOf(command[1]),Integer.valueOf(command[2])));
            }else if(command[0].equals("operateTo")){
                commandList.add(new OperateToCommand(Integer.valueOf(command[1]),Integer.valueOf(command[2])));
            }else{
                System.out.println("Blad odczytu komendy w lini " + (i+1));
            }
        }

        return new ComplexCommand(commandList);
    }

}
