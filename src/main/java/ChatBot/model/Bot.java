package ChatBot.model;

import ChatBot.service.Const;
import ChatBot.service.Const.ReplacementCode;

import java.util.Random;

import static ChatBot.service.Const.EXIT_INDEX;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 06/05/17 00:19
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Bot
{
    private DataBase db;
    
    public Bot ()
    {
        db = Const.readDb();
    }
    
    public String getBotAnswer (String input)
    {
        db = Const.readDb();
        
        //Removing punctuation (for now)
        while (input.matches("^.*[.,;! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
    
        //Needed to get in the While at first try
        String outputPhrase = input;
    
        //region --> Case order to stop
        if(db.findUserPhrase(input) == EXIT_INDEX) System.exit(0);
        //endregion
        
        //We do not want to repeat the input phrase
        while (outputPhrase.equalsIgnoreCase(input))
        {
            //Searching for match
            int inputPoolIndex = db.findUserPhrase(input);
            int randLinkIndex = new Random().nextInt(db.getLink(inputPoolIndex).length);
    
            //Selecting answer
            int outputPoolIndex = db.getLink(inputPoolIndex)[randLinkIndex];
            int randPhraseIndex = new Random().nextInt(db.getBotPhrasePool(outputPoolIndex).length);
    
            outputPhrase = db.getBotPhrase(outputPoolIndex, randPhraseIndex);
        }
    
        return replaceCode(outputPhrase);
    }
    
    public String getBotPhrase (int poolIndex)
    {
        db = Const.readDb();
    
        String botPhrase = db.getBotPhrase(poolIndex);
    
        return replaceCode(botPhrase);
    }
    
    private String replaceCode (String string)
    {
        for (ReplacementCode code : ReplacementCode.values())
        {
            if(string.contains(code.toString())) string = string.replace(code.toString(), code.getDate());
        }
        
        return string;
    }
}
