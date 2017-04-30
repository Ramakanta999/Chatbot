package ChatBot.model;

import java.util.HashMap;

/*................................................................................................................................
 . Copyright (c)
 .
 . The DataBase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 30/04/17 12:48
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class DataBase
{
    private HashMap<Integer, String>    phrases = new HashMap<>();
    private HashMap<Integer, Integer[]> links   = new HashMap<>();
    
    public DataBase ()
    {
        this.phrases = new HashMap<>();
        this.links = new HashMap<>();
    }
    
    public DataBase (HashMap<Integer, String> phrases, HashMap<Integer, Integer[]> links)
    {
        this.phrases = phrases;
        this.links = links;
    }
    
    public HashMap<Integer, String> getPhrases ()
    {
        return phrases;
    }
    
    public HashMap<Integer, Integer[]> getLinks ()
    {
        return links;
    }
    
    @Override
    public String toString ()
    {
        return "phrases : " + getPhrases().size() + " — links : " + getLinks().size();
    }
}
