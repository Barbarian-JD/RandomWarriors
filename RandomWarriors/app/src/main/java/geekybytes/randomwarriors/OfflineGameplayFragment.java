package geekybytes.randomwarriors;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class OfflineGameplayFragment extends Fragment {
    ArrayList<String> picked_chars;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_offline_gameplay, container, false);
        picked_chars=getArguments().getStringArrayList("picked_chars");
        TextView player = (TextView)inf.findViewById(R.id.character_name_player);
        TextView bot = (TextView)inf.findViewById(R.id.character_name_bot);
        ImageView player_img = (ImageView) inf.findViewById(R.id.character_image_player);
        ImageView bot_img = (ImageView) inf.findViewById(R.id.character_image_bot);

        Animation anim_player = new TranslateAnimation(player_img.getLeft()-400, player_img.getLeft() + 100, player_img.getTop(), player_img.getTop());
        Animation anim_bot = new TranslateAnimation(bot_img.getRight()+400, bot_img.getRight()-100, player_img.getTop(), player_img.getTop());
        anim_player.setDuration(2000);anim_bot.setDuration(2000);
        anim_player.setFillAfter(true);anim_bot.setFillAfter(true);
        anim_player.setFillEnabled(true);anim_bot.setFillEnabled(true);
        player_img.startAnimation(anim_player);
        bot_img.startAnimation(anim_bot);
        ArrayList battle_result = OfflineCharacterFight.fight_simulation(picked_chars.get(picked_chars.size()-1), OfflineGameModeActivity.used_cpu_chars);
        Boolean battle = (Boolean)battle_result.get(0);
        int bot_char_num = (int)battle_result.get(1);
        String bot_char_name = (String)battle_result.get(2);
        OfflineGameModeActivity.used_cpu_chars.add(bot_char_num);
        player.setText(picked_chars.get(picked_chars.size()-1));
        bot.setText(bot_char_name);
        if (battle == true){
            OfflineGameModeActivity.win_counter++;
            Toast.makeText(getActivity(), "You win", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "You lose", Toast.LENGTH_SHORT).show();
        }
        if (picked_chars.size() < 3) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Bundle bundle=new Bundle();
                    bundle.putStringArrayList("picked_chars", picked_chars);
                    OfflineCharacterPickFragment fragobj=new OfflineCharacterPickFragment();
                    fragobj.setArguments(bundle);
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container_offlinegame, fragobj).commit();
                }
            }, 3000);
        }
        else{
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Bundle bundle=new Bundle();
                    bundle.putStringArrayList("picked_chars", picked_chars);
                    resultScreen fragobj=new resultScreen();
                    fragobj.setArguments(bundle);
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.container_offlinegame, fragobj).commit();
                }
            }, 3000);
        }
        return  inf;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((OfflineGameModeActivity)activity).onSectionAttached(1);
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

