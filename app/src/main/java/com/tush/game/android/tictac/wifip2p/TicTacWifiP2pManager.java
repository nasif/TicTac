package com.tush.game.android.tictac.wifip2p;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

import com.tush.game.android.tictac.util.LogUtil;
import com.tush.game.android.tictac.util.P2PConstants;
import com.tush.game.android.tictac.util.P2PConstants;

/**
 * Created by Nasif on 29-Oct-14.
 */
public class TicTacWifiP2pManager implements WifiP2pManager.ChannelListener {

    private Activity mCtx;
    private WifiP2pManager mwifip2pmanager = null;
    private WifiP2pManager.Channel mChannel = null;
    private static TicTacWifiP2pManager mtictacwifiManager = null;
    private IntentFilter mIntentFilter=null;
    private WiFiDirectBroadcastReceiver wiFiDirectBroadcastReceiver=null;
    private int p2pdiscoveryerrorcode= P2PConstants.P_2_P_ENUM_NO_ERROR;


    public synchronized static TicTacWifiP2pManager init(Context context) {
        if(!(context instanceof Activity) ){
            throw  new RuntimeException("context must be an instance of Actiivty. Wont' support application context");
        }
        if (mtictacwifiManager == null) {
            mtictacwifiManager = new TicTacWifiP2pManager(context);
        }
        return mtictacwifiManager;
    }

    private TicTacWifiP2pManager(Context ctx) {
        this.mCtx =(Activity)ctx;
        initializeIntentFilter();
        mwifip2pmanager = (WifiP2pManager) mCtx.getSystemService(Context.WIFI_P2P_SERVICE);
        initializewifiP2pmanager();
    }


    private void initializewifiP2pmanager() {
        mChannel = mwifip2pmanager.initialize(mCtx, mCtx.getMainLooper(), null);
        wiFiDirectBroadcastReceiver=new WiFiDirectBroadcastReceiver(mwifip2pmanager,mChannel,mCtx);
    }

    private void initializeIntentFilter() {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    public void register() {
        if(wiFiDirectBroadcastReceiver==null||mIntentFilter==null)
            throw  new IllegalStateException("P2pmanager is not intialized");
        mCtx.registerReceiver(wiFiDirectBroadcastReceiver,mIntentFilter);
        mwifip2pmanager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                LogUtil.LogDebug("Success fully ");
            }
            @Override
            public void onFailure(int reasonCode) {
                LogUtil.LogError("onFailure");
            }
        });

    }

    public void unregister() {
        if(wiFiDirectBroadcastReceiver==null||mIntentFilter==null)
        throw  new IllegalStateException("P2pmanager is not intialized");
      mCtx.unregisterReceiver(wiFiDirectBroadcastReceiver);
    }


    @Override
    public void onChannelDisconnected() {
        unregister();
        initializewifiP2pmanager();
        register();
    }


}
