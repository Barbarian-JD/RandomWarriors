package geekybytes.randomwarriors;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class OfflineCharacterFight {

    protected static ArrayList fight_simulation(String selected_player_character, ArrayList used_bot_chars){
        Random randomGenerator = new Random();
        boolean victory;
        Boolean flag = true;
        int bot_char = 0;
        while(flag){
            bot_char = randomGenerator.nextInt(9);   // 9 characters
            if (used_bot_chars.contains(bot_char)){
                continue;
            }
            flag = false;
        }
        int randnum = randomGenerator.nextInt(101);
        int selected_player_num = 0;
        CharacterMatrix char_matrix = new CharacterMatrix();
        char_matrix.initialize_charcter_list();
        char_matrix.initialize_FightEnvironment();
        for (int i=0; i<char_matrix.charList.size(); i++) {
            if(char_matrix.charList.get(i).equals(selected_player_character)){
                selected_player_num = i;
                break;
            }
        }
        int battlefield = randomGenerator.nextInt(6);
        double winProb = char_matrix.getWinningProbability(selected_player_num, bot_char, battlefield)*100;
        if (randnum>winProb) {
            victory = true;
        }
        else{
            victory = false;
        }
        ArrayList battle_result = new ArrayList();
        battle_result.add(victory);
        battle_result.add(bot_char);
        battle_result.add(char_matrix.charList.get(bot_char));
        battle_result.add(battlefield);

        return battle_result;
    }
}
