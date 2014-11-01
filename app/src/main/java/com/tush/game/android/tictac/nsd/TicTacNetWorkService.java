package com.tush.game.android.tictac.nsd;

import android.net.wifi.p2p.nsd.WifiP2pServiceInfo;

/**
 * Created by nasif.noorudeen on 01-11-2014.
 */
public interface TicTacNetWorkService {
    String getName();
    String getIANAProtocolName();
    public WifiP2pServiceInfo getServiceInfo();
}
