package ChatBot;

import ChatBot.model.DataBase;
import ChatBot.service.Const;

import java.util.Random;
import java.util.Scanner;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 30/04/17 15:04
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class App
{
    public static void main (String[] args)
    {
        DataBase db = Const.readDb();
    
        Scanner scanner = new Scanner(System.in);
    
        String input = scanner.nextLine().trim();
    
        while (!input.equalsIgnoreCase("stop"))
        {
            Integer phraseKey = -1;
        
            for (Integer key : db.getPhrases().keySet())
            {
                if(db.getPhrases().get(key).equalsIgnoreCase(input)) phraseKey = key;
            }
        
            if(db.getLinks().containsKey(phraseKey))
            {
                int returnPhrase = db.getLinks().get(phraseKey)[new Random().nextInt(db.getLinks().get(phraseKey).length)];
                System.out.println(db.getPhrases().get(returnPhrase));
            }
        
            input = scanner.nextLine().trim();
        }
    }
}
