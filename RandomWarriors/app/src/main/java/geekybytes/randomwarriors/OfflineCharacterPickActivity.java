package geekybytes.randomwarriors;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OfflineCharacterPickActivity extends ActionBarActivity {
    private ArrayList<Character> characters;
    //private RadioGroup rg_offline;
    private LinearLayout llGroup;
    ArrayList<String> picked_chars;
    static int num_check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_character_pick);
        //rg_offline = (RadioGroup)findViewById(R.id.radio_group_offline);
        final Button pick = (Button)findViewById(R.id.button_pick_offline);
        TextView header = (TextView) findViewById(R.id.offline_pick_header);
        picked_chars = getIntent().getStringArrayListExtra("picked_chars");

        if (picked_chars == null){
            picked_chars = new ArrayList<String>();
        }

        if (picked_chars.size() == 0){
            header.setText("Pick 1st Warrior");
        }
        else if (picked_chars.size() == 1){
            header.setText("Pick 2nd Warrior");
        }
        else if (picked_chars.size() == 2){
            header.setText("Pick 3rd Warrior");
        }

        characters = new ArrayList<Character>();
        characters.add(new Character("Knight"));
        characters.add(new Character("Archer"));
        characters.add(new Character("Ninja"));
        characters.add(new Character("Spearman"));
        characters.add(new Character("Berzerker"));
        characters.add(new Character("Psycho"));
        characters.add(new Character("Barbarian"));
        characters.add(new Character("Robo"));

        for(int i=0; i<picked_chars.size(); i++){
            findObject(characters, picked_chars.get(i)).enabled = false;
        }
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int picked_id = -1;
                for (int i=0; i<characters.size(); i++){
                    if(characters.get(i).box.rb.isChecked()){
                        picked_id = i;
                        break;
                    }
                }

                if (picked_id == -1){
                    Toast.makeText(getApplicationContext(), "Please pick one character to battle", Toast.LENGTH_SHORT).show();
                }
                else {
                    Character picked_char = characters.get(picked_id);
                    picked_chars.add(picked_char.getName());
                    Intent i = new Intent(getApplicationContext(), OfflineGameplayActivity.class);
                    i.putStringArrayListExtra("picked_chars", picked_chars);
                    startActivity(i);
                    finish();
                }
            }
        });


        llGroup = (LinearLayout) findViewById(R.id.llgroup);
        for(int i=0; i<characters.size(); i++){
            characters.get(i).box = new MyRadioButton(this);
            characters.get(i).box.setText(characters.get(i).getName());

            if (characters.get(i).enabled == false) {
                characters.get(i).box.setClickable(false);
            }
            llGroup.addView(characters.get(i).box.getView());
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
    private Character findObject(ArrayList<Character> chars, String name){
        for(int i=0; i<chars.size(); i++){
            if (chars.get(i).getName().equals(name)){
                return chars.get(i);
            }
        }
        return null;
    }
}

class Character{
    String name;
    Boolean enabled;
    MyRadioButton box;
    Character(String name){
        this.name = name;
        this.enabled = true;
    }
    String getName(){
        return this.name;
    }
}
class MyRadioButton implements View.OnClickListener{

    private ImageView iv;
    private TextView tv;
    RadioButton rb;
    private View view;

    public MyRadioButton(Context context) {

        view = View.inflate(context, R.layout.offline_pick_characters_row, null);
        rb = (RadioButton) view.findViewById(R.id.radioButton1);
        tv = (TextView) view.findViewById(R.id.textView1);
        iv = (ImageView) view.findViewById(R.id.imageView1);

        view.setOnClickListener(this);
        rb.setOnCheckedChangeListener(null);

    }


    public View getView() {
        return view;
    }

    @Override
    public void onClick(View v) {

        boolean nextState = !rb.isChecked();

        if (nextState) {

            LinearLayout lGroup = (LinearLayout) view.getParent();
            if (lGroup != null) {
                int child = lGroup.getChildCount();
                for (int i = 0; i < child; i++) {
                    ((RadioButton) lGroup.getChildAt(i).findViewById(R.id.radioButton1)).setChecked(false);
                }
            }

            rb.setChecked(nextState);
        }
    }

    public void setImage(Bitmap b){
        iv.setImageBitmap(b);
    }

    public void setText(String text){
        tv.setText(text);
    }

    public void setChecked(boolean isChecked){
        rb.setChecked(isChecked);
    }

    public void setClickable(Boolean status){
        view.setAlpha(0.4f);
        rb.setVisibility(View.GONE);
        view.setClickable(status);
    }

}