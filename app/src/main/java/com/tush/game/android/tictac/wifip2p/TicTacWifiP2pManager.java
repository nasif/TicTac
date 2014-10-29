package com.tush.game.android.tictac.wifip2p;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by Nasif on 29-Oct-14.
 */
public class TicTacWifiP2pManager implements  WifiP2pManager.ChannelListener {

    private Context mCtx;
    private WifiP2pManager mwifip2pmanager = null;
    private WifiP2pManager.Channel mChannel = null;
    private static TicTacWifiP2pManager mtictacwifiManager = null;

    public synchronized static TicTacWifiP2pManager init(Activity activity) {
        if (mtictacwifiManager == null) {
            mtictacwifiManager = new TicTacWifiP2pManager(activity);
        }
        return mtictacwifiManager;
    }

    private TicTacWifiP2pManager(Context ctx) {
        this.mCtx = ctx;
        mwifip2pmanager = (WifiP2pManager) mCtx.getSystemService(Context.WIFI_P2P_SERVICE);
        initializewifiP2pmanager();

    }
    private void initializewifiP2pmanager() {
        mChannel = mwifip2pmanager.initialize(mCtx, mCtx.getMainLooper(), null);
    }

    @Override
    public void onChannelDisconnected() {
        initializewifiP2pmanager();
    }
}
