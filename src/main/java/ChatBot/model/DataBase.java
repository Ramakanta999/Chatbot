package ChatBot.model;

import ChatBot.service.Const;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 09/05/17 09:06
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
        for (int poolIndex : getUserPhrasePools().keySet())
        {
            boolean found = findInArray(toFind, getUserPhrasePool(poolIndex));
    
            if(found) return poolIndex;
        }
    
        //poolIndex of the not understanding phrases
        return Const.NOT_FOUND;
    }
    
    public int findBotPhrase (String toFind)
    {
        for (int poolIndex : getBotPhrasePools().keySet())
        {
            boolean found = findInArray(toFind, getBotPhrasePool(poolIndex));
    
            if(found) return poolIndex;
        }
        
        return -1;
    }
    
    public boolean findInArray (String toFind, String[] pool)
    {
        for (String toCompare : pool)
        {
            String tmpToFind = toFind;
            tmpToFind = tmpToFind.replaceAll(" ", "");
            toCompare = toCompare.replaceAll(" ", "");
            
            //region --> Case where there is instant match
            if(tmpToFind.equalsIgnoreCase(toCompare))
            {
                return true;
            }
            //endregion
            
            //region --> Case where there are multiple combinations
            String[] strings = toCompare.split("[\\[\\]]");
            
            tmpToFind = tmpToFind.replace("\\?", "\\?");
            
            for (String string : strings)
            {
                tmpToFind = tmpToFind.replace(string, "");
            }
    
            if(tmpToFind.isEmpty()) return true;
            //endregion
        }
    
        //Found in this array
        return false;
    }
    
    public String getBotPhrase (int poolIndex)
    {
        int poolSize = getBotPhrasePool(poolIndex).length;
        int phraseIndex = new Random().nextInt(poolSize);
    
        return getBotPhrase(poolIndex, phraseIndex);
    }
    
    public void addToUserPool (String toAdd, int poolIndex)
    {
        String[] oldPool = getUserPhrasePool(poolIndex);
        int newSize = oldPool.length + 1;
        
        String[] newPool = Arrays.copyOf(oldPool, newSize);
        
        newPool[newSize - 1] = toAdd;
        
        getUserPhrasePools().put(poolIndex, newPool);
    }
    
    public void addToBotPool (String toAdd, int poolIndex)
    {
        String[] oldPool = getBotPhrasePool(poolIndex);
        int newSize = oldPool.length + 1;
        
        String[] newPool = Arrays.copyOf(oldPool, newSize);
        
        newPool[newSize - 1] = toAdd;
        
        getBotPhrasePools().put(poolIndex, newPool);
    }
    
    public void addToLink (int key, int value)
    {
        Integer[] oldArray = getLink(key);
        int newSize = oldArray.length + 1;
        
        Integer[] newArray = Arrays.copyOf(oldArray, newSize);
        
        newArray[newSize - 1] = value;
        
        getLinks().put(key, newArray);
    }
    
    public void addLink (int key, int value)
    {
        addLink(key, new Integer[]{value});
    }
    
    public void addLink (int key, Integer[] values)
    {
        getLinks().put(key, values);
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
