package geekybytes.randomwarriors;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Arshdeep on 05-07-2015.
 */
public class CharacterMatrix {



    public HashMap charList = new HashMap ();
    charList.SimpleEntry = ("Knight",0);
    charList.put("Archer",1);
    charList.put("Ninja",2);
    charList.put("Spearman",3);
    charList.put("Wizard",4);
    charList.put("Berserker",5);
    charList.put("Psycho",6);
    charList.put("Barbarian",7);
    charList.put("Robot",8);

    Hashtable characterList = new Hashtable();

    double charTable[][] = {
        { 0.50, 0.80, 0.60, 0.20, 0.40, 0.40, 0.58, 0.40, 0.60 },
        { 0.20, 0.50, 0.30, 0.80, 0.40, 0.60, 0.58, 0.20, 0.20 },
        { 0.40, 0.70, 0.50, 0.60, 0.70, 0.80, 0.58, 0.60, 0.60 },
        { 0.80, 0.20, 0.20, 0.50, 0.30, 0.20, 0.58, 0.20, 0.40 },
        { 0.60, 0.60, 0.30, 0.70, 0.50, 0.80, 0.58, 0.80, 0.20 },
        { 0.60, 0.40, 0.20, 0.80, 0.20, 0.50, 0.58, 0.80, 0.80 },
        { 0.42, 0.42, 0.42, 0.42, 0.42, 0.42, 0.50, 0.42, 0.42 },
        { 0.60, 0.80, 0.40, 0.80, 0.20, 0.20, 0.58, 0.50, 0.60 },
        { 0.40, 0.60, 0.40, 0.60, 0.80, 0.20, 0.58, 0.20, 0.50 }
    };

    public double getWinningProbability(int firstCharIndex,int secondCharIndex)
    {
        return charTable[firstCharIndex][secondCharIndex];
    }
}
