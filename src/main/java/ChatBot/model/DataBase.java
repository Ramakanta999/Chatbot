package ChatBot.model;

import java.util.HashMap;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 02/05/17 20:08
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class DataBase
{
    private HashMap<Integer, String[]>  pools = new HashMap<>();
    private HashMap<Integer, Integer[]> links = new HashMap<>();
    
    //Constructors ==================================================
    public DataBase ()
    {
        setPools(new HashMap<Integer, String[]>());
        setLinks(new HashMap<Integer, Integer[]>());
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
    public int findPhrase (String phraseToFind)
    {
        for (int index : pools.keySet())
        {
            for (String phraseToCompare : pools.get(index))
            {
                if(phraseToFind.equalsIgnoreCase(phraseToCompare)) return index;
            }
        }
    
        //0 is the poolIndex of the not understanding phrases
        return 0;
    }
    
    //Override methods ==============================================
    @Override
    public String toString ()
    {
        return "pools : " + getPools().size() + " — links : " + getLinks().size();
    }
}
