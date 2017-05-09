package ChatBot.controller;

import ChatBot.model.DataBase;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.util.Set;

import static ChatBot.service.Const.readDb;
import static ChatBot.service.Const.writeDb;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LearningController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 09/05/17 09:37
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LearningController
{
    public TextField txtNewUser;
    public TextField txtNewBot;
    public TextField txtAddUser;
    public TextField txtAddBot;
    
    public Spinner<Integer> spinnerAddUser;
    public Spinner<Integer> spinnerAddBot;
    public Spinner<Integer> spinnerLinksUser;
    public Spinner<Integer> spinnerLinksBot;
    
    private DataBase db = readDb();
    
    public void initialize ()
    {
        updateDb();
        
        Set<Integer> userKeys = db.getUserPhrasePools().keySet();
        Set<Integer> botKeys = db.getBotPhrasePools().keySet();
        
        initSpinner(spinnerLinksUser, getMinOf(userKeys), getMaxOf(userKeys));
        initSpinner(spinnerLinksBot, getMinOf(botKeys), getMaxOf(botKeys));
        initSpinner(spinnerAddUser, getMinOf(userKeys), getMaxOf(userKeys));
        initSpinner(spinnerAddBot, getMinOf(botKeys), getMaxOf(botKeys));
    }
    
    private void initSpinner (Spinner<Integer> spinner, int min, int max)
    {
        //The Spinner will now only accept integerValues between 0 and max.
        //The initialValue is set to 0.
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, 0));
        
        //The Spinner can't be modified by typing.
        //It can only be changed through the + and - buttons
        spinner.setEditable(false);
    }
    
    private int getMaxOf (Set<Integer> values)
    {
        int max = Integer.MIN_VALUE;
        
        for (int val : values)
        {
            if(val > max) max = val;
        }
        
        return max;
    }
    
    private int getMinOf (Set<Integer> values)
    {
        int min = Integer.MAX_VALUE;
        
        for (int val : values)
        {
            if(val < min) min = val;
        }
        
        return min;
    }
    
    public void btnNewPhrases_onAction ()
    {
        String trigger = txtNewUser.getText().trim();
        String answer = txtNewBot.getText().trim();
        
        DataBase db = readDb();
        
        //region --> Trigger
        int triggerPoolIndex = db.findUserPhrase(trigger);
        
        if(triggerPoolIndex == -1)
        {
            triggerPoolIndex = getMaxOf(db.getUserPhrasePools().keySet()) + 1;
            
            db.getUserPhrasePools().put(triggerPoolIndex, new String[]{trigger});
        }
        //endregion
        
        //region --> Answer
        int answerPoolIndex = db.findBotPhrase(answer);
        
        if(answerPoolIndex == -1)
        {
            answerPoolIndex = getMaxOf(db.getBotPhrasePools().keySet()) + 1;
            
            db.getBotPhrasePools().put(answerPoolIndex, new String[]{answer});
        }
        //endregion
        
        //region --> Link
        if(db.getLinks().containsKey(triggerPoolIndex))
        {
            db.addToLink(triggerPoolIndex, answerPoolIndex);
        }
        else
        {
            db.addLink(triggerPoolIndex, answerPoolIndex);
        }
        //endregion
        
        writeDb(db);
    }
    
    public void btnAddLinks_onAction ()
    {
        updateDb();
        
        db.addToLink(spinnerLinksUser.getValue(), spinnerLinksBot.getValue());
        
        writeDb(db);
    }
    
    public void btnAddUserPool_onAction ()
    {
        updateDb();
        
        Integer poolIndex = spinnerAddUser.getValue();
        String toAdd = txtAddUser.getText().trim();
        String[] pool = db.getUserPhrasePool(poolIndex);
        
        if(db.findInArray(toAdd, pool)) return;
        
        db.addToUserPool(toAdd, poolIndex);
        
        writeDb(db);
    }
    
    public void btnAddBotPool_onAction ()
    {
        updateDb();
        
        Integer poolIndex = spinnerAddBot.getValue();
        String toAdd = txtAddBot.getText().trim();
        String[] pool = db.getBotPhrasePool(poolIndex);
        
        if(db.findInArray(toAdd, pool)) return;
        
        db.addToBotPool(toAdd, poolIndex);
        
        writeDb(db);
    }
    
    private void updateDb ()
    {
        db = readDb();
    }
}
