package csc413.com.t7.hungerstrike;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * @author Mohammad/Mardan Anwar
 */

public class Preferences extends Activity
{
    private Context context;

    CheckBox Vegetarian, LowFat, Vegan;
    View.OnClickListener checkBoxListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_layout);

        DisplayMetrics display_metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display_metrics);

        int width = display_metrics.widthPixels;
        int height = display_metrics.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Vegetarian = (CheckBox) findViewById(R.id.OFCheckBox);
        LowFat = (CheckBox) findViewById(R.id.OFCheckBox);
        Vegan = (CheckBox) findViewById(R.id.VeganCheckBox);

        /** Storing boolean values for preferences */
        final SharedPreferences preference = getSharedPreferences("pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preference.edit();

        final boolean checkboxstate1 = preference.getBoolean("vegetarian", false);
        Vegetarian.setChecked(checkboxstate1);

        final boolean checkboxstate2 = preference.getBoolean("lowfat", false);
        LowFat.setChecked(checkboxstate2);

        final boolean checkboxstate3 = preference.getBoolean("vegan", false);
        Vegan.setChecked(checkboxstate3);

        checkBoxListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.PFCheckBox:
                        if(Vegetarian.isChecked())
                        {
                            editor.putBoolean("vegetarian", true);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Vegetarian is checked", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            editor.putBoolean("vegetarian", false);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Vegetarian is unchecked", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.OFCheckBox:
                        if(LowFat.isChecked())
                        {
                            editor.putBoolean("lowfat", true);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Low Fat is checked", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            editor.putBoolean("lowfat", false);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Low Fat is unchecked", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.VeganCheckBox:
                        if(Vegan.isChecked())
                        {
                            editor.putBoolean("vegan", true);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Vegan is checked", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            editor.putBoolean("vegan", false);
                            editor.commit();
                            Toast.makeText(getBaseContext(), "Vegan is unchecked", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };
        Vegetarian.setOnClickListener(checkBoxListener);
        LowFat.setOnClickListener(checkBoxListener);
        Vegan.setOnClickListener(checkBoxListener);
    }
}
