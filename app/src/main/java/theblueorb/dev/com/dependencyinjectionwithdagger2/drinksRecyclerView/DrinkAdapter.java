package theblueorb.dev.com.dependencyinjectionwithdagger2.drinksRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import theblueorb.dev.com.dependencyinjectionwithdagger2.APIClient;
import theblueorb.dev.com.dependencyinjectionwithdagger2.CoffeeActivity;
import theblueorb.dev.com.dependencyinjectionwithdagger2.Drink;
import theblueorb.dev.com.dependencyinjectionwithdagger2.R;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private List<Drink> drinks;
    private Context context;
    public DrinkAdapter(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinkAdapter.DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListItem = R.layout.drink_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachtoParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent,shouldAttachtoParentImmediately);
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        holder.drinkTextView.setText(drinks.get(position).getName());

        RequestOptions myOptions = new RequestOptions()
                .fitCenter();
//                .override(200, 200);

        Glide.with(context)
                .load(drinks.get(position).getUrl())
                .apply(myOptions)
                .into(holder.drinkImageView);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }


    public class DrinkViewHolder extends RecyclerView.ViewHolder{

        TextView drinkTextView;
        ImageView drinkImageView;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkImageView = (ImageView) itemView.findViewById(R.id.drink_image_rv);
            drinkTextView = (TextView) itemView.findViewById(R.id.drink_name_rv);
        }
    }
}
