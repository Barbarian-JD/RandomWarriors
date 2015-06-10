package geekybytes.randomwarriors;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends ActionBarActivity {

    private AsyncTask connection;
    private TextView result;
    static TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        status = (TextView) findViewById(R.id.view_status);
        Button connect = (Button) findViewById(R.id.button_connect);
        Button clear = (Button) findViewById(R.id.button_clear);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection = new check_connection("http://randomwarriors.byethost.com/somephp").execute(null, null, null);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("");
            }
        });
    }

    class check_connection extends AsyncTask<String, String, String> {
        public boolean running = true;
        HttpResponse response;
        private InputStream is;
        String host;

        public check_connection(String host) {
            this.host = host;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onCancelled() {
            running = false;
        }

        @Override
        protected String doInBackground(String... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(host);
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            try {
                if (running) {
                    httppost.setEntity(new UrlEncodedFormEntity(null));

                    // Execute HTTP Post Request
                    response = httpclient.execute(httppost);
                    if (response != null) {
                        is = response.getEntity().getContent();

                    }
                }

            } catch (Exception e) {
                System.out.println(e);
                // TODO Auto-generated catch block
            }
            return null;
        }

        @Override
        protected void onPostExecute(String Result) {
            if (running) {
                if (running) {
                    Reader reader = new InputStreamReader(is);
                    int status = -1;
                    try {
                        JsonParser parser = new JsonParser();
                        JsonObject data = parser.parse(reader).getAsJsonObject();

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        Type Integer = new TypeToken<Integer>() {
                        }.getType();
                        if (running)
                            status = gson.fromJson(data.get("success"), Integer);          //EDIT THIS LINE FWHICH DATA NEEDED FROM JSON
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(status==1){
                        HomeActivity.status.setText("Connection WORKING");
                    }
                    else if(status == 0){
                        HomeActivity.status.setText("CONNECTION FAILED");
                    }
                }
            }
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_home, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}


