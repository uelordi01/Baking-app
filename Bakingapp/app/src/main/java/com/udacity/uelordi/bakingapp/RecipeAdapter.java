package com.udacity.uelordi.bakingapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.uelordi.bakingapp.content.Recipe;
import com.udacity.uelordi.bakingapp.database.RecipeColumns;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uelordi on 15/06/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    private static final String TAG = "RecipeAdapter";
    private RecipeItemClickListener _recipeItemClickCallback;
    Cursor mData;
    private Context mContext;

    public interface RecipeItemClickListener {
        void onRecipeItemClick(long id);
    }

    public RecipeAdapter(Context context,
                         RecipeItemClickListener _recipeItemClickCallback) {
        mContext = context;
        this._recipeItemClickCallback = _recipeItemClickCallback;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(mContext).inflate(R.layout.recipe_item_list, parent, false);
        return new RecipeHolder(item);
    }


    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {

        mData.moveToPosition(position);
        holder.mRecipeTitle.setText(mData.getString(mData.getColumnIndex(RecipeColumns.NAME)));

    }
    public void setCursor(Cursor cursor) {
        mData = cursor;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.getCount();
        }
        return count;
    }

    class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_recipe_name)
        TextView mRecipeTitle;
        @BindView(R.id.tv_recipe_image)
        ImageView mRecipeImage;
        public RecipeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mData.moveToPosition(adapterPosition);
            long idRecipe = mData.getLong(mData.getColumnIndex(RecipeColumns._ID));
            _recipeItemClickCallback.onRecipeItemClick(idRecipe);
        }
    }
}
