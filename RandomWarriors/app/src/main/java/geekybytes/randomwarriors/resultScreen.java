package geekybytes.randomwarriors;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resultScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resultScreen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> picked_chars;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resultScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static resultScreen newInstance(String param1, String param2) {
        resultScreen fragment = new resultScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public resultScreen() {
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
        View inf = inflater.inflate(R.layout.fragment_result_screen, container, false);
        picked_chars=getArguments().getStringArrayList("picked_chars");
        TextView result = (TextView)inf.findViewById(R.id.text_result);
        Button play_again = (Button)inf.findViewById(R.id.button_play_again);
        Button go_home = (Button)inf.findViewById(R.id.button_go_home);
        TextView char1 = (TextView)inf.findViewById(R.id.text_character_1);
        TextView char2 = (TextView)inf.findViewById(R.id.text_character_2);
        TextView char3 = (TextView)inf.findViewById(R.id.text_character_3);
        ImageView resultView1 = (ImageView)inf.findViewById(R.id.result_character_1);
        ImageView resultView2 = (ImageView)inf.findViewById(R.id.result_character_2);
        ImageView resultView3 = (ImageView)inf.findViewById(R.id.result_character_3);

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), OfflineGameModeActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), GameActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        if (OfflineGameModeActivity.win_counter >=2){
            result.setText("You Won");
        }
        else{
            result.setText("You lose");
        }
        char1.setText(picked_chars.get(0));
        char2.setText(picked_chars.get(1));
        char3.setText(picked_chars.get(2));
        resultView1.setImageResource(getWinLoseImageSrc(OfflineGameModeActivity.winLoseObject[0]));
        resultView2.setImageResource(getWinLoseImageSrc(OfflineGameModeActivity.winLoseObject[1]));
        resultView3.setImageResource(getWinLoseImageSrc(OfflineGameModeActivity.winLoseObject[2]));

        return inf;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((OfflineGameModeActivity)activity).onSectionAttached(2);
    }

public int getWinLoseImageSrc(int val)
{
    if(val==0)
    return R.drawable.ic_close_white_36dp ;
        else
    return R.drawable.ic_check_white_36dp;
}
}
