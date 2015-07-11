package geekybytes.randomwarriors;

import java.util.HashMap;
import java.util.Hashtable;

public class CharacterMatrix {



    HashMap charList;

    void initialize_charcter_list() {
        charList= new HashMap ();
        charList.put(0, "Knight");
        charList.put(1, "Archer");
        charList.put(2, "Ninja");
        charList.put(3, "Spearman");
        charList.put(4, "Wizard");
        charList.put(5, "Berserker");
        charList.put(6, "Psycho");
        charList.put(7, "Barbarian");
        charList.put(8, "Robo");
    }
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
