package com.tush.game.android.tictac.wifip2p;

/**
 * Created by Nasif on 29-Oct-14.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.tush.game.android.tictac.util.LogUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * A BroadcastReceiver that notifies of important Wi-Fi p2p events.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Activity mActivity;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       Activity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                LogUtil.LogDebug("Wifi P2P state is enabled");
            } else {
                LogUtil.LogDebug("Wifi P2P state is disabled");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            LogUtil.LogDebug("getting this intent"+WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
            WifiP2pDeviceList deviceList=intent.getParcelableExtra(WifiP2pManager.EXTRA_P2P_DEVICE_LIST);
            Collection<WifiP2pDevice> list= deviceList.getDeviceList();
            Iterator<WifiP2pDevice>itr= list.iterator();
            while (itr.hasNext()){
                WifiP2pDevice device=itr.next();
                LogUtil.LogDebug(device.deviceAddress);
                LogUtil.LogDebug(device.deviceName);
                LogUtil.LogDebug(device.primaryDeviceType);
                LogUtil.LogDebug(device.secondaryDeviceType);
                LogUtil.LogDebug(device.isServiceDiscoveryCapable());
                LogUtil.LogDebug(device.status);
                LogUtil.LogDebug(device.wpsDisplaySupported());
                LogUtil.LogDebug(device.wpsKeypadSupported());
                LogUtil.LogDebug(device.wpsPbcSupported());
            }
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            LogUtil.LogDebug("getting this intent"+WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
            NetworkInfo networkinfo= intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            WifiP2pInfo p2pinfo=intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);
            if(p2pinfo!=null){
                LogUtil.LogDebug(p2pinfo.isGroupOwner);
                LogUtil.LogDebug(p2pinfo.groupFormed);
                LogUtil.LogDebug(p2pinfo.groupOwnerAddress);
            }
            intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_GROUP);
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}
