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
 . Last Modified : 22/09/17 13:46
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This Class is the ChatBot's brain.<br> The methods allow you to make the ChatBot answer or apply the commands.<br> <br> __ Warning : Reads
 db __<br> __ Dependency : model.DataBase, service.Const __
 */
public class Bot
{
    private DataBase db = readDb();

    /**
     Constructor of the Bot.<br> Calls updateDb() to make sure the db attribute is initialized.
     */
    public Bot ()
    {
        //update db from .json File
        updateDb();
    }

    /**
     Returns the Bot's answer according to the User's [input].<br> <br> It will simplify the phrase at maximum : remove spaces, remove
     punctuation, etc.<br> The goal is to remove all differences to compare with UserPools from db.

     @param input The String input the User placed.
     @return The Bot's answer.
     */
    public String getBotAnswer (String input)
    {
        //update db from .json File
        updateDb();

        //region --> Removing punctuation
        while (input.matches("^.*[.,;! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
        //endregion

        //region --> Detecting if command line
        if (input.matches("^/.*")) return applyCommand(input.substring(1));
        //endregion

        //region --> Case order to stop
        if (db.findUserPhrase(input) == EXIT_INDEX) System.exit(0);
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

    /**
     Returns the Bot's answer according to the PoolIndex [poolIndex].<br>

     @param poolIndex Index of the pool to look into.
     @return The answer message found in the pool [poolIndex].
     */
    public String getBotPhrase (int poolIndex)
    {
        //update db from .json File
        updateDb();

        String botPhrase = db.getBotPhrase(poolIndex);

        return replaceCode(botPhrase);
    }

    /**
     Replaces a ReplacementCode Tag by the calculated value (i.e TIME_HOUR)

     @param string Message that has to be checked and replaced.
     @return The new String mssage with the Tags replaced by value.
     */
    private String replaceCode (String string)
    {
        for (ReplacementCode code : ReplacementCode.values())
        {
            if (string.contains(code.toString())) string = string.replace(code.toString(), code.getValue());
        }

        return string;
    }

    /**
     Applies the Command [command] to the System.

     @param command Command passed by the User.
     @return The result of the command : "Command executed" or "Command not found".
     */
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

    /**
     Updates the current db object with the values read from the .json file.<br> When called before accessing db, this method ensures that
     your db is up to date either if the file was or wasn't modified
     */
    private void updateDb ()
    {
        db = readDb();
    }
}
