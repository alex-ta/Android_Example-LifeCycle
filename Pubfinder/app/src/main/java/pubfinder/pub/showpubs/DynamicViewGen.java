package pubfinder.pub.showpubs;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.*;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by User on 05.10.2016.
 */

public class DynamicViewGen<TYPE> {

    private HashMap<String, Method> methodsMap;


    public DynamicViewGen(Class<TYPE> clazz) {
        this.methodsMap = new HashMap<String, Method>();
        Method[] methods = clazz.getDeclaredMethods();
        // get all getter methods from a class
        for(Method m: methods){
            if(m.getName().contains("get")) {
                methodsMap.put(m.getName(),m);
            }
        }
    }

    public ViewGroup getView(TYPE object, Context ctx){
        LinearLayout entry = new LinearLayout(ctx);
        entry.setOrientation(LinearLayout.VERTICAL);
        for(String key : methodsMap.keySet()){
            try {
                String attName = key.replace("get","");
                TextView attNameText = new TextView(ctx);
                attNameText.setText(attName);
                String att = methodsMap.get(key).invoke(object) + "";
                TextView attText = new TextView(ctx);
                attText.setText(att);
                LinearLayout line = new LinearLayout(ctx);
                entry.addView(line, new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                line.addView(attNameText);
                ((LayoutParams)attNameText.getLayoutParams()).weight = 1;
                line.addView(attText);
                ((LayoutParams)attText.getLayoutParams()).weight = 1;

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

}
