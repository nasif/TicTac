package com.tush.game.android.tictac.nsd;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nasif.noorudeen on 01-11-2014.
 */
public class TicTacUpnPService implements TicTacNetWorkService {

    private String Name;
    private String iAnAProtocol;
    //private Map map=


    public TicTacUpnPService(String instancename, String protocol, HashMap<String, String> information) {
        this.Name = instancename;
        this.iAnAProtocol = iAnAProtocol;

    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getIANAProtocolName() {
        return iAnAProtocol;
    }
}
