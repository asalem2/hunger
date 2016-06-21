package csc413.com.t7.hungerstrike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;
import java.util.List;

/** @author Mohammad/Anu Aggarwal */

public class GroceryList extends AppCompatActivity
{
    TextView tv ;
    String pp = "";
    String x = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        DisplayMetrics display_metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display_metrics);

        int width = display_metrics.widthPixels;
        int height = display_metrics.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Intent intent = getIntent();
        List<String> urlstring = intent.getStringArrayListExtra("ingredients");
        tv = (TextView)findViewById(R.id.textView_inglist);
        for (int i = 0; i<urlstring.size();i++) {
            pp = pp + urlstring.get(i) + "\n";
        }
        tv.setText(pp);
    }
}

