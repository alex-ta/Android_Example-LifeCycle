package com.example.user.activitylivecycle;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.11.2016.
 */

public class AbstractDataActivity extends Activity {

    public AbstractDataActivity(){
        this.className = this.getClass().getName()+"Stored";
    }

    private Bundle bundle;
    private String className;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.bundle = savedInstanceState;
    }

    public void saveStringArray(String [] array){
        bundle.putStringArray(className+"_StringArray",array);
    }

    public String[] loadStringArray(){
        return bundle.getStringArray(className+"_StringArray");
    }

    public void saveString(String...array){
        bundle.putStringArray(className+"_Strings", array);
    }

    public String[] loadString(){
        return bundle.getStringArray(className+"_Strings");
    }

    public void saveInteger(int...array){
        bundle.putIntArray(className+"_Integer", array);
    }

    public int[] getInteger(){
        return bundle.getIntArray(className+"_Integer");
    }

    public void saveIntegerArray(int[] array){
        bundle.putIntArray(className+"_IntegerArray", array);
    }

    public int[] getIntegerArray(){
        return bundle.getIntArray(className+"_IntegerArray");
    }

    public void saveList (ArrayList list){
        bundle.putSerializable(className+"_List", list);
    }

    public ArrayList getList (){
       return (ArrayList) bundle.getSerializable(className+"_List");
    }

}
