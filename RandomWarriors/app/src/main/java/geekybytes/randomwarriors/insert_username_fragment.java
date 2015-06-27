package geekybytes.randomwarriors;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link insert_username_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class insert_username_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProgressBar progress;
    private EditText username;

    private String email;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment insert_username.
     */
    // TODO: Rename and change types and number of parameters
    public static insert_username_fragment newInstance(String param1, String param2) {
        insert_username_fragment fragment = new insert_username_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public insert_username_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf =  inflater.inflate(R.layout.fragment_insert_username, container, false);

        TextView message = (TextView)inf.findViewById(R.id.view_welcome_message);
        username = (EditText)inf.findViewById(R.id.edit_username);
        Button submit = (Button)inf.findViewById(R.id.button_submit);
        progress = (ProgressBar)inf.findViewById(R.id.myprogressbar);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        email = prefs.getString("email", "");

        message.setText("Welcome " + email);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("email", email));
                list.add(new BasicNameValuePair("username", username.getText().toString()));
                new add_username("http://randomwarriors.byethost7.com/add_username.php", list).execute(null, null, null);
            }
        });
        return inf;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class add_username extends AsyncTask<String, String, String> {
        public boolean running = true;
        HttpResponse response;
        private InputStream is;
        String host;
        ArrayList list;
        public add_username(String host, ArrayList l) {
            this.host = host;
            this.list = l;
        }

        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
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
                    httppost.setEntity(new UrlEncodedFormEntity(list));


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
                    Reader reader = new InputStreamReader(is);
                    int status = 0;
                    String message = "";
                    try {
                        JsonParser parser = new JsonParser();
                        JsonObject data = parser.parse(reader).getAsJsonObject();

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();

                        Type Integer = new TypeToken<Integer>() {}.getType();
                        Type String = new TypeToken<String>() {}.getType();

                            status = gson.fromJson(data.get("success"), Integer);          //EDIT THIS LINE FOR WHICH DATA NEEDED FROM JSON
                            message = gson.fromJson(data.get("message"), String);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(status == 1){
                        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                        SharedPreferences.Editor editor = saved_values.edit();
                        editor.putString("username", username.getText().toString());
                        editor.apply();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), GameActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                }

            progress.setVisibility(View.GONE);
        }
    }
}
