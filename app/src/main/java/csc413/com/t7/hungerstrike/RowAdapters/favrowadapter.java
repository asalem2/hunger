package csc413.com.t7.hungerstrike.RowAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import csc413.com.t7.hungerstrike.R;
import csc413.com.t7.hungerstrike.RecipeStrings.RecipeTie;
import csc413.com.t7.hungerstrike.VolleySingleton;

/**
 * @author Anu Aggarwal
 */

public class favrowadapter extends ArrayAdapter
{
    RecipeTie recipe;
    com.android.volley.toolbox.ImageLoader mImageLoader;
        private List<RecipeTie> objectsmodelList;
        private int resource;
        private Context context;
        public favrowadapter(Context context, int resource, List<RecipeTie> objects)
        {
            super(context, resource, objects);
            objectsmodelList = objects;
            this.resource = resource;
            this.context = context;

        }
    static class ViewHolder
    {
        public TextView recipe_title;
        public NetworkImageView recipe_image;
        public TextView recipe_info;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;
        // reuse views
        if (rowView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.recipe_brief_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.recipe_title = (TextView) rowView.findViewById(R.id.recipe_row_name);
            viewHolder.recipe_image = (NetworkImageView) rowView.findViewById(R.id.recipe_row_image);
            viewHolder.recipe_info = (TextView) rowView.findViewById(R.id.recipe_row_info);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();

        recipe = objectsmodelList.get(position);

        // Get the ImageLoader through your singleton class.
        mImageLoader = VolleySingleton.getInstance(context).getImageLoader();

        holder.recipe_image.setImageUrl(recipe.getImage_url(), mImageLoader);
        /** Retrieves image for the recipe */

        holder.recipe_title.setText(recipe.getTitle());

        holder.recipe_info.setText(recipe.getRId());

        return rowView;
    }
}

