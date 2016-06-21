package csc413.com.t7.hungerstrike.FragmentsActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import csc413.com.t7.hungerstrike.R;
import csc413.com.t7.hungerstrike.RecipeStrings.RecipeTie;
import csc413.com.t7.hungerstrike.RowAdapters.favrowadapter;
import csc413.com.t7.hungerstrike.getrecipedetail;

/**
 * @author Anu Aggarwal
 */
public class favouriteFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ListView lvobjects;
    private TextView tv;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static favouriteFragment newInstance(int sectionNumber)
    {
        favouriteFragment fragment = new favouriteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public favouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favfrag, container, false);

        SharedPreferences settings;

        settings = getContext().getSharedPreferences("Product_Favorite",
                Context.MODE_PRIVATE);

        if (settings.contains("fav")) {

            List<RecipeTie> favorites;
            String jsonFavorites = settings.getString("fav", null);
            Gson gson = new Gson();
            RecipeTie[] favoriteItems = gson.fromJson(jsonFavorites,
                    RecipeTie[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<RecipeTie>(favorites);

            favrowadapter adapter = new favrowadapter(getActivity().getBaseContext(), R.layout.recipe_brief_layout, favorites);
            tv = (TextView) rootView.findViewById(R.id.textView_save);
            tv.setText("");
            lvobjects = (ListView)rootView.findViewById(R.id.listView);
            lvobjects.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            final List<RecipeTie> finalFavorites = favorites;
            lvobjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String[] allingredients = finalFavorites.get(position).getIngredientarr();

                    Bundle extraa = new Bundle();
                    extraa.putString("recipeId", finalFavorites.get(position).getRId());
                    extraa.putString("imageurl", finalFavorites.get(position).getImage_url());
                    extraa.putString("recipename", finalFavorites.get(position).getTitle());
                    extraa.putString("apiname", finalFavorites.get(position).getApiname());
                    extraa.putStringArray("ingredientarray", allingredients);
                    Intent getDetails = new Intent(getActivity(), getrecipedetail.class);
                    getDetails.putExtras(extraa);
                    startActivity(getDetails);

                }
            });
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences settings;

        settings = getContext().getSharedPreferences("Product_Favorite",
                Context.MODE_PRIVATE);

        if (settings.contains("fav")) {

            List<RecipeTie> favorites;
            String jsonFavorites = settings.getString("fav", null);
            Gson gson = new Gson();
            RecipeTie[] favoriteItems = gson.fromJson(jsonFavorites,
                    RecipeTie[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<RecipeTie>(favorites);

            favrowadapter adapter = new favrowadapter(getActivity().getBaseContext(), R.layout.recipe_brief_layout, favorites);
            tv = (TextView) getView().findViewById(R.id.textView_save);
            tv.setText("");
            lvobjects = (ListView)getView().findViewById(R.id.listView);
            lvobjects.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            final List<RecipeTie> finalFavorites = favorites;
            lvobjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    String[] allingredients = finalFavorites.get(position).getIngredientarr();

                    Bundle extraa = new Bundle();
                    extraa.putString("recipeId", finalFavorites.get(position).getRId());
                    extraa.putString("imageurl", finalFavorites.get(position).getImage_url());
                    extraa.putString("recipename", finalFavorites.get(position).getTitle());
                    extraa.putString("apiname", finalFavorites.get(position).getApiname());
                    extraa.putStringArray("ingredientarray", allingredients);
                    Intent getDetails = new Intent(getActivity(), getrecipedetail.class);
                    getDetails.putExtras(extraa);
                    startActivity(getDetails);

                }
            });
        }
    }

}
