package edu.kis.powp.jobs2d.command.manager.parsers;

import com.google.gson.Gson;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONCommandParser implements Parser {
    private Gson gson = new Gson();

    @Override
    public InputDataModel parse(String data) {
        return gson.fromJson(data, InputDataModel.class);
    }
}
