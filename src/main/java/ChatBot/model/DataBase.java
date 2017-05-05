package ChatBot.model;

import java.util.HashMap;
import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 05/05/17 23:02
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("WeakerAccess")
public class DataBase
{
    private HashMap<Integer, String[]>  userPhrasePools = new HashMap<>();
    private HashMap<Integer, String[]>  botPhreasePools = new HashMap<>();
    private HashMap<Integer, Integer[]> links           = new HashMap<>();
    
    //Constructors ==================================================
    public DataBase ()
    {
        userPhrasePools = new HashMap<>();
        botPhreasePools = new HashMap<>();
        links = new HashMap<>();
    }
    
    //Getters and Setters =======================================================
    
    public HashMap<Integer, String[]> getUserPhrasePools ()
    {
        return userPhrasePools;
    }
    
    public HashMap<Integer, String[]> getBotPhrasePools ()
    {
        return botPhreasePools;
    }
    
    public String[] getUserPhrasePool (int index)
    {
        return getUserPhrasePools().get(index);
    }
    
    public String[] getBotPhrasePool (int index)
    {
        return getBotPhrasePools().get(index);
    }
    
    public String getBotPhrase (int poolIndex, int phraseIndex)
    {
        return getBotPhrasePool(poolIndex)[phraseIndex];
    }
    
    public HashMap<Integer, Integer[]> getLinks ()
    {
        return links;
    }
    
    public Integer[] getLink (int index)
    {
        return getLinks().getOrDefault(index, new Integer[]{0});
    }
    
    //Utils =========================================================
    public int findUserPhrase (String toFind)
    {
        for (int index : getUserPhrasePools().keySet())
        {
            for (String toCompare : getUserPhrasePool(index))
            {
                String tmpToFind = toFind;
                tmpToFind = tmpToFind.replaceAll(" ", "");
                toCompare = toCompare.replaceAll(" ", "");
        
                //region --> Case where there is instant match
                if(tmpToFind.equalsIgnoreCase(toCompare))
                {
                    return index;
                }
                //endregion
        
                //region --> Case where there are multiple combinations
                String[] strings = toCompare.split("[\\[\\]]");
        
                tmpToFind = tmpToFind.replace("\\?", "\\?");
        
                for (String string : strings)
                {
                    tmpToFind = tmpToFind.replace(string, "");
                }
        
                if(tmpToFind.isEmpty()) return index;
                //endregion
            }
        }
        
        //0 is the poolIndex of the not understanding phrases
        return 0;
    }
    
    public int findBotPhrase (String toFind)
    {
        for (int index : getBotPhrasePools().keySet())
        {
            for (String toCompare : getBotPhrasePool(index))
            {
                if(toFind.equalsIgnoreCase(toCompare)) return index;
            }
        }
        
        return -1;
    }
    
    public String getBotPhrase (int poolIndex)
    {
        int poolSize = getBotPhrasePool(poolIndex).length;
        int phraseIndex = new Random().nextInt(poolSize);
    
        return getBotPhrase(poolIndex, phraseIndex);
    }
    
    //Override methods ==============================================
    @Override
    public String toString ()
    {
        return String.format("UserPhrasePools : %d — BotPhrasesPools : %d — links : %d",
                             getUserPhrasePools().size(),
                             getBotPhrasePools().size(),
                             getLinks().size());
    }
}
