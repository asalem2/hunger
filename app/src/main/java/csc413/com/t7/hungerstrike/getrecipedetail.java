package csc413.com.t7.hungerstrike;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import csc413.com.t7.hungerstrike.RecipeStrings.RecipeTie;
/**
 * @author Anu Aggarwal
 */
public class getrecipedetail extends AppCompatActivity {
    String[] ingredients = new String[50];
    String recipeId = null;
    String recipename = null;
    String imageurl = null;
    String apiname = null;
    String sourceurl =null;
    int p =0;
    Context context;
    RecipeTie rtobj = new RecipeTie();
    public List<RecipeTie> rtobjList = new ArrayList<>();
    Button save;
    public getrecipedetail() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get recipe deatil through intent bundle retrieval
        Bundle b=this.getIntent().getExtras();

        recipeId = b.getString("recipeId");
        recipename = b.getString("recipename");
        imageurl = b.getString("imageurl");
        apiname = b.getString("apiname");
        String api1 = "yummly";
        ingredients = b.getStringArray("ingredientarray");
        setContentView(R.layout.activity_getrecipedetail);
        String urlstring=null;

        if(apiname.equalsIgnoreCase(api1)){

            urlstring = "http://api.yummly.com/v1/api/recipe/"+recipeId+"?_app_id=ed502541&_app_key=0f46aa8f65b15b601ab91354337ccd44";
        }
        else {
            recipeId = recipeId.replaceAll("#","%23");
            urlstring = "https://api.edamam.com/search?r="+recipeId+"&app_id=5b5cdfb6&app_key=62bb003f040913e957cf81070e39d44b";
        }
        new CallAPI().execute(urlstring);

    }

    // button click for save recipe
    public void saverecipeid(View view){
        Toast.makeText(getBaseContext(), "recipe saved", Toast.LENGTH_SHORT).show();

        rtobj.setTitle(recipename);
        rtobj.setImage_url(imageurl);
        rtobj.setApiname(apiname);
        rtobj.setIngredientarr(ingredients);
        rtobj.setSourceurl(sourceurl);
        rtobj.setRId(recipeId);
        rtobjList.add(rtobj);

        // saving recipe through shared preferences
        SharedPreferences settings = getBaseContext().getSharedPreferences("Product_Favorite", context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(rtobjList);

        editor.putString("fav", jsonFavorites);

        editor.commit();
    }

    // button click for creating instant shopping list
    public void addingredientstocart(View view)
    {
        final ArrayList<String> ingre = new ArrayList<>();

        for (int i = 0; i <p;i++){
            ingre.add(i,ingredients[i]);
        }
        Button grocBtn = (Button) view.findViewById(R.id.shoppingcartbutton);
        grocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getrecipedetail.this, GroceryList.class);
                intent.putStringArrayListExtra("ingredients",ingre);
                startActivity(intent);
            }
        });
    }


    private class CallAPI extends AsyncTask<String, String, String>

    {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String resultstring = null;

        @Override
        protected String doInBackground(String... params) {

            URL url = null;

            try {
                /**reading API data and storing it in buffer */

                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultstring = buffer.toString();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {

                if(apiname.equalsIgnoreCase("yummly"))
                {
                    JSONObject parentobject = new JSONObject(resultstring);
                    sourceurl = parentobject.getJSONObject("source").getString("sourceRecipeUrl");
                }
                else {
                    JSONArray parentobject = new JSONArray(resultstring);
                    sourceurl = parentobject.getJSONObject(0).getString("url");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // returns json string
            return sourceurl;
        }
        @Override
        protected void onPostExecute(String result) {
            // if no data available
            if (result==(null)) {
                // do nothing
            }
            /** display parsed data */
            else {
                final TextView recipe_name= (TextView)findViewById(R.id.recipename);
                final TextView recipe_ingredients = (TextView)findViewById(R.id.textView_ingredients);
                final ImageView recipe_image = (ImageView)findViewById(R.id.imageView_image);
                final WebView recipe_url = (WebView)findViewById(R.id.webView_recipe);
                // set recipe name
                recipe_name.setText(recipename);
                //set recipe ingredients
                String ing = "";

                for(int j = 0;j<ingredients.length;j++)
                {
                    if(ingredients[j]!=null){
                        ing =ing+ ingredients[j] + "\n";
                        p=j+1;}

                }
                recipe_ingredients.setText(ing);

                // get image from image url
                final ProgressBar progressBar =(ProgressBar)findViewById(R.id.progressBar2);

                ImageLoader.getInstance().displayImage(imageurl, recipe_image, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(view.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressBar.setVisibility(view.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(view.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressBar.setVisibility(view.GONE);
                    }
                });

                // get recipe source url and assigb it to webview
                recipe_url.setWebViewClient(new WebViewClient() {
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        Toast.makeText(getBaseContext(), description, Toast.LENGTH_SHORT).show();
                    }
                });
                recipe_url.loadUrl(result);

            }
        }
    }
}
