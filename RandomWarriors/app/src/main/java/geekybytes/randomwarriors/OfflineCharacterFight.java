package geekybytes.randomwarriors;

import java.util.Random;

/**
 * Created by Arshdeep on 05-07-2015.
 */
public class OfflineCharacterFight {

    private String selectedChar = "";


    protected void  OfflineCharacterFight(String character )
    {
        this.selectedChar = character;
    }

    protected boolean fightSimulation()
    {
        Random randomGenerator = new Random();
        boolean win = false;

        int Randnum = randomGenerator.nextInt(101);
        int secondChar = randomGenerator.nextInt(10);

        CharacterMatrix charMatrix = new CharacterMatrix();
        Integer firstCharNumb = (Integer) charMatrix.characterList.get(selectedChar);
        double winProb = charMatrix.getWinningProbability(1,secondChar)*100;
        if (Randnum>winProb)
            win = true;

        return win;
    }
}
