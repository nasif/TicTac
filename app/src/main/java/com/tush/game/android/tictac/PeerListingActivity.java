package com.tush.game.android.tictac;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.tush.game.android.tictac.R;
import com.tush.game.android.tictac.wifip2p.TicTacWifiP2pManager;

public class PeerListingActivity extends Activity {


    private TicTacWifiP2pManager tictacWifiP2pManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer_listing);
        tictacWifiP2pManager= TicTacWifiP2pManager.init(PeerListingActivity.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.peer_listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tictacWifiP2pManager.register();

    }

    @Override
    protected void onPause() {
        super.onPause();
        tictacWifiP2pManager.unregister();
    }
}
