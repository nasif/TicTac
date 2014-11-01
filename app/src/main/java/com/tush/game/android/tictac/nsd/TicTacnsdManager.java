package com.tush.game.android.tictac.nsd;

import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceInfo;

import com.tush.game.android.tictac.util.P2PConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nasif.noorudeen on 01-11-2014.
 */
public class TicTacnsdManager {

    private WifiP2pManager mManager = null;
    private WifiP2pManager.Channel mChannel=null;
    private Map<Integer, TicTacNetWorkService> serviceMap = null;
    private AtomicInteger integer=null;

    public TicTacnsdManager() {
        integer=new AtomicInteger();
    }

    public int addService(HashMap<String, String> information, int type,String instancename,String protocol){
        int serviceId=integer.incrementAndGet();
        TicTacNetWorkService service=null;
        if (type == P2PConstants.P2P_SERVICE_UPNP) {
            service=new TicTacUpnPService(instancename,protocol,information);
        }
        if (type == P2PConstants.P2P_SEREVICE_BONJOUR) {
            service=new TicTacDnsdServices(instancename,protocol,information);
        }
        serviceMap.put(serviceId,service);
        mManager.addLocalService(mChannel, service.getServiceInfo(), new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onFailure(int arg0) {

            }
        });

        return serviceId;
    }

    public void removeService(int serviceid) {
        mManager.removeLocalService(mChannel,serviceMap.get(serviceid).getServiceInfo(),new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onFailure(int arg0) {

            }
        });
    }

    public void clearAllServices() {
        mManager.clearLocalServices();
    }
}
