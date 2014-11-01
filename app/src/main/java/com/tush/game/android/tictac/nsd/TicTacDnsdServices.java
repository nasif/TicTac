package com.tush.game.android.tictac.nsd;

import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pServiceInfo;

import java.util.HashMap;

/**
 * Created by nasif.noorudeen on 01-11-2014.
 */
public class TicTacDnsdServices implements TicTacNetWorkService {


    private String Name;
    private String iAnAProtocol;
    private WifiP2pServiceInfo serviceInfo;

    public TicTacDnsdServices(String instancename, String protocol, HashMap<String, String> information) {
        this.Name = instancename;
        this.iAnAProtocol = protocol;
        serviceInfo=WifiP2pDnsSdServiceInfo.newInstance(this.Name,protocol,information);
    }



    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getIANAProtocolName() {
        return iAnAProtocol;
    }

    @Override
    public WifiP2pServiceInfo getServiceInfo() {
        return serviceInfo;
    }
}
