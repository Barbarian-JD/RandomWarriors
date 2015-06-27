package geekybytes.randomwarriors;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OfflineGameplayActivity extends ActionBarActivity {
    ListView character_listview;
    private ArrayList<Character> characters;
    private ArrayAdapter<Character> listAdapter ;
    static int num_check = 0;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_gameplay);

        character_listview = (ListView) findViewById(R.id.listView1);

        characters = new ArrayList<Character>();
        characters.add(new Character("Knight"));
        characters.add(new Character("Archer"));
        characters.add(new Character("Ninja"));
        characters.add(new Character("Spearman"));
        characters.add(new Character("Berzerker"));
        characters.add(new Character("Psycho"));
        characters.add(new Character("Barbarian"));
        characters.add(new Character("Robo"));


        CustomAdapter adapter = new CustomAdapter(this, characters);
        character_listview.setAdapter(adapter);

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

class Character{
    String name;
    Boolean checked = false;
    CheckBox box;
    Character(String name){
        this.name = name;
    }

    void toggle_check(){
        if(checked==false){
            checked = true;
        }
        else{
            checked = false;
        }
    }

}

class CustomAdapter extends ArrayAdapter{
        ArrayList<Character> characters = null;
        Context context;

        CustomAdapter(Context context, ArrayList<Character> characters) {
            super(context,R.layout.offline_pick_characters_row,characters);
            this.context = context;
            this.characters = characters;
        }
@Override
public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.offline_pick_characters_row, null);
            //convertView = inflater.inflate(R.layout.offline_pick_characters_row, parent, false);
        }
        final Character mycharacter = characters.get(position);
        mycharacter.box = (CheckBox) convertView.findViewById(R.id.checkBox1);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        final View myview = convertView;
        characters.get(position).box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Character character = mycharacter;
                if(character.checked == false && OfflineGameplayActivity.num_check<3){
                    OfflineGameplayActivity.num_check++;
                    character.toggle_check();
                }
                else if(character.checked == true){
                    character.toggle_check();
                    OfflineGameplayActivity.num_check--;
                }
                if(OfflineGameplayActivity.num_check == 3){
                    for (int i=0; i<characters.size(); i++) {
                        if (characters.get(i).checked == false) {
                            Toast.makeText(getContext(), "Can't pick more " + characters.get(i).name, Toast.LENGTH_SHORT).show();
                            characters.get(i).box.setVisibility(myview.INVISIBLE);
                        }
                    }
                }
            }
        });
        name.setText(characters.get(position).name);
        return convertView;
        }
    }