package ChatBot.model;

import ChatBot.service.Const;
import ChatBot.service.Const.CommandLine;
import ChatBot.service.Const.ReplacementCode;

import java.util.Random;

import static ChatBot.service.Const.CommandLine.valueOf;
import static ChatBot.service.Const.EXIT_INDEX;
import static ChatBot.service.Const.ReplacementCode.CHATBOT_NAME;
import static ChatBot.service.Const.ReplacementCode.USER_NAME;
import static ChatBot.service.Const.readDb;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 09/05/17 08:36
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Bot
{
    private DataBase db = readDb();
    
    public Bot ()
    {
        db = readDb();
    }
    
    public String getBotAnswer (String input)
    {
        db = readDb();
    
        //region --> Removing punctuation
        while (input.matches("^.*[.,;! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
        //endregion
    
        //region --> Detecting if command line
        if(input.matches("^/.*")) return applyCommand(input.substring(1));
        //endregion
        
        //region --> Case order to stop
        if(db.findUserPhrase(input) == EXIT_INDEX) System.exit(0);
        //endregion
    
        //Needed to get in the While at first try
        String outputPhrase = input;
        
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
        db = readDb();
        
        String botPhrase = db.getBotPhrase(poolIndex);
    
        return replaceCode(botPhrase);
    }
    
    private String replaceCode (String string)
    {
        for (ReplacementCode code : ReplacementCode.values())
        {
            if(string.contains(code.toString())) string = string.replace(code.toString(), code.getValue());
        }
        
        return string;
    }
    
    private String applyCommand (String command)
    {
        try
        {
            String[] split = command.split(":", 2);
            
            CommandLine commandLine = valueOf(split[0].trim());
            
            switch (commandLine)
            {
                case ChangeBotName:
                    CHATBOT_NAME.setValue(split[1].trim());
                    break;
                case ChangeUserName:
                    USER_NAME.setValue(split[1].trim());
                    break;
                case Exit:
                    System.exit(0);
                    break;
                case Help:
                    return Const.getHelpMessage();
            }
            
            return "Command executed";
        }
        catch (IllegalArgumentException iae)
        {
            iae.printStackTrace();
            return "Command not found";
        }
    }
}
