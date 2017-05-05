package ChatBot.controller;

import ChatBot.model.DataBase;
import ChatBot.service.Const;
import javafx.scene.control.TextField;

import java.util.Set;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LearningController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 05/05/17 19:30
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LearningController
{
    public TextField txtTrigger;
    public TextField txtAnswer;
    
    public void btnAdd_onAction ()
    {
        String trigger = txtTrigger.getText().trim();
        String answer = txtAnswer.getText().trim();
        
        DataBase db = Const.readDb();
        
        int triggerPoolIndex = db.findUserPhrase(trigger);
        
        if(triggerPoolIndex == 0)
        {
            int newIndex = getMaxOf(db.getUserPhrasePools().keySet());
            
            db.getUserPhrasePools().put(newIndex, new String[]{trigger});
        }
        
        int answerPoolIndex = db.findBotPhrase(answer);
        
        if(answerPoolIndex == -1)
        {
            int newIndex = getMaxOf(db.getBotPhrasePools().keySet());
            
            db.getBotPhrasePools().put(newIndex, new String[]{answer});
        }
        
        Const.writeDb(db);
    }
    
    private int getMaxOf (Set<Integer> values)
    {
        int max = 0;
        
        for (int val : values)
        {
            if(val > max) max = val;
        }
        
        return max;
    }
}
