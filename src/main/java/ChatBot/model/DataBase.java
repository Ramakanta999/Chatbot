package ChatBot.model;

import java.util.HashMap;
import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 04/05/17 22:56
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("WeakerAccess")
public class DataBase
{
    private HashMap<Integer, String[]>  pools = new HashMap<>();
    private HashMap<Integer, Integer[]> links = new HashMap<>();
    
    //Constructors ==================================================
    public DataBase ()
    {
        setPools(new HashMap<>());
        setLinks(new HashMap<>());
    }
    
    //Getters =======================================================
    public HashMap<Integer, String[]> getPools ()
    {
        return pools;
    }
    
    public void setPools (HashMap<Integer, String[]> newPools)
    {
        this.pools = newPools;
    }
    
    public String[] getPool (int index)
    {
        return getPools().get(index);
    }
    
    public String getPhrase (int poolIndex, int phraseIndex)
    {
        return getPool(poolIndex)[phraseIndex];
    }
    
    public HashMap<Integer, Integer[]> getLinks ()
    {
        return links;
    }
    
    public void setLinks (HashMap<Integer, Integer[]> newLinks)
    {
        this.links = newLinks;
    }
    
    public Integer[] getLink (int index)
    {
        return getLinks().getOrDefault(index, new Integer[]{0});
    }
    
    //Utils =========================================================
    public int findPhrase (String toFind)
    {
        for (int index : pools.keySet())
        {
            if(index > 0)
            {
                for (String toCompare : pools.get(index))
                {
                    String tmpToFind = toFind;
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
        }
        
        //0 is the poolIndex of the not understanding phrases
        return 0;
    }
    
    public String getQuestion ()
    {
        int index = 0;
        int indexRange = 0;
        
        //region --> Getting Amount of keys that are < 0
        for (int i : pools.keySet())
        {
            if(i < 0) indexRange++;
        }
        //endregion
        //region --> Getting rand index in the pools list (only if < 0)
        while (index >= 0)
        {
            index = -(new Random().nextInt(indexRange));
        }
        //endregion
        
        return getQuestion(index);
    }
    
    public String getQuestion (int poolIndex)
    {
        int poolSize = getPool(poolIndex).length;
        
        return getPool(poolIndex)[new Random().nextInt(poolSize)];
    }
    
    //Override methods ==============================================
    @Override
    public String toString ()
    {
        return "pools : " + getPools().size() + " — links : " + getLinks().size();
    }
}
