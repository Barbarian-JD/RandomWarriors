package geekybytes.randomwarriors;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class OfflineGameplayActivity extends ActionBarActivity {
    ArrayList<String> picked_chars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_gameplay);


        picked_chars = getIntent().getStringArrayListExtra("picked_chars");
        TextView dum = (TextView)findViewById(R.id.charname);
        dum.setText("HELLO " + picked_chars.get(picked_chars.size()-1));
        if (picked_chars.size() < 3) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent i = new Intent(getApplicationContext(), OfflineCharacterPickFragment.class);
                    i.putExtra("picked_chars", picked_chars);
                    startActivity(i);
                    finish();

                }
            }, 2000);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_offline_gameplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

