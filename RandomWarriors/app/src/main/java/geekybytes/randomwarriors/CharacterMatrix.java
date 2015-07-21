package geekybytes.randomwarriors;

import java.util.HashMap;
import java.util.Hashtable;

public class CharacterMatrix {



    HashMap charList;
    static HashMap fightEnvironment;
    static HashMap battlefield_image_resource;


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
    void initialize_FightEnvironment() {
        fightEnvironment= new HashMap ();
        fightEnvironment.put(0, "Desert");
        fightEnvironment.put(1, "Plains");
        fightEnvironment.put(2, "Forests");
        fightEnvironment.put(3, "Cemetry");
        fightEnvironment.put(4, "Ship");
        fightEnvironment.put(5, "Mountain");

        battlefield_image_resource = new HashMap();
        battlefield_image_resource.put(0, R.drawable.desert_dontknow);
        battlefield_image_resource.put(1, R.drawable.fields_notsure);
        battlefield_image_resource.put(2, R.drawable.forest_free);
        battlefield_image_resource.put(3, R.drawable.cemetery);
        battlefield_image_resource.put(4, R.drawable.ship);
        battlefield_image_resource.put(5, R.drawable.mountain_dontknow);



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



    double fightEnvTable[][] = {
            { -0.05, 0.05, 0.00, 0.05, 0.00, -0.05, 0.00, 0.00, 0.00 },
            { 0.10, 0.00, -0.10, 0.00, 0.00, 0.00, 0.00, 0.00, +0.10 },
            { 0.00, -0.10, 0.10, -0.05,0.00, 0.00, 0.00, 0.10, 0.00 },
            { -0.10, 0.00, 0.05, 0.00, 0.10, 0.00, 0.00, -0.10, 0.00 },
            { 0.00, -0.05, 0.00, 0.00, 0.00, 0.05, 0.00, 0.00, -0.10 },
            { 0.05, +0.10, -0.05, 0.00, -0.10, 0.00, 0.00, 0.00, 0.00 }
    };

    public double getWinningProbability(int firstCharIndex,int secondCharIndex,int envIndex)
    {
        if (firstCharIndex != secondCharIndex)
        {
            System.out.println("index 1 is "+firstCharIndex + " and index 2 is "+ secondCharIndex );
            System.out.println("Value is: " + (charTable[firstCharIndex][secondCharIndex] + fightEnvTable[envIndex][firstCharIndex] - fightEnvTable[envIndex][secondCharIndex]));
            return charTable[firstCharIndex][secondCharIndex] + fightEnvTable[envIndex][firstCharIndex] - fightEnvTable[envIndex][secondCharIndex];
        }
        else
            return 0.5;
    }
}
