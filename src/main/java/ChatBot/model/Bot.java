package ChatBot.model;

import ChatBot.service.Const;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 02/05/17 23:02
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
    
    public String getAnswer (String input)
    {
        db = Const.readDb();
        
        //Removing punctuation (for now)
        while (input.matches("^.*[.,;! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
    
        //Needed to get in the While at first try
        String outputPhrase = input;
    
        //We do not want to repeat the input phrase
        while (outputPhrase.equalsIgnoreCase(input))
        {
            //Searching for match
            int inputPoolIndex = db.findPhrase(input);
            int randLinkIndex = new Random().nextInt(db.getLink(inputPoolIndex).length);
        
            //Selecting answer
            int outputPoolIndex = db.getLink(inputPoolIndex)[randLinkIndex];
            int randPhraseIndex = new Random().nextInt(db.getPool(outputPoolIndex).length);
        
            outputPhrase = db.getPhrase(outputPoolIndex, randPhraseIndex);
        }
    
        return outputPhrase;
    }
    
    public String getQuestion ()
    {
        return db.getQuestion();
    }
    
    public String getQuestion (int poolIndex)
    {
        return db.getQuestion(poolIndex);
    }
}
