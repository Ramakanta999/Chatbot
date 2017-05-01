package ChatBot.model;

import java.util.HashMap;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 01/05/17 14:07
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
        this.pools = new HashMap<>();
        this.links = new HashMap<>();
    }
    
    public DataBase (HashMap<Integer, String[]> pools, HashMap<Integer, Integer[]> links)
    {
        this.pools = pools;
        this.links = links;
    }
    
    public DataBase (DataBase db)
    {
        this.pools = db.getPools();
        this.links = db.getLinks();
    }
    
    //Getters =======================================================
    public HashMap<Integer, String[]> getPools ()
    {
        return pools;
    }
    
    protected void setPools (HashMap<Integer, String[]> newPools)
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
        return getLinks().getOrDefault(index, new Integer[]{-1});
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
        
        return -1;
    }
    
    //Override methods ==============================================
    @Override
    public String toString ()
    {
        return "pools : " + getPools().size() + " — links : " + getLinks().size();
    }
}
