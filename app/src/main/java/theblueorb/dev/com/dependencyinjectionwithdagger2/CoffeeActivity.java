package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.FavoriteDrinkEntry;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.FavoriteDrinksDatabase;
import theblueorb.dev.com.dependencyinjectionwithdagger2.drinksRecyclerView.DrinkAdapter;
import theblueorb.dev.com.dependencyinjectionwithdagger2.models.Drink;
import theblueorb.dev.com.dependencyinjectionwithdagger2.network.APIClient;

public class CoffeeActivity extends AppCompatActivity implements onItemsFetchedFromNetworkListener {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.ll)
    LinearLayout ll;
    List<Drink> drinks;
    @BindView(R.id.insertDummyFavDrinks)
    Button insertDummyFavDrinksButton;

    @BindView(R.id.drinks_recycler_view)
    RecyclerView recyclerView;

    private FavoriteDrinksDatabase mDb;
    private RecyclerView.LayoutManager layoutManager;
    private DrinkAdapter drinkAdapter;

    private static final String TAG = CoffeeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ButterKnife.bind(this);
        mDb = FavoriteDrinksDatabase.getInstance(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void fetchAndLoadViews() {
        APIClient.fetchCoffee(CoffeeActivity.this, this);
    }

    @OnClick({R.id.button, R.id.insertDummyFavDrinks})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button: {
                fetchAndLoadViews();
                break;
            }

            case R.id.insertDummyFavDrinks: {
                insertImageToDatabase();
                break;
            }

        }
    }

    //TODO(1) modify favorite drinks dummy data insertion
    private void insertImageToDatabase() {

        InputStream inputStream = getResources().openRawResource(
                getResources().getIdentifier("water",
                        "raw", getPackageName()));
        Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
        FavoriteDrinkEntry drinkEntry = new FavoriteDrinkEntry("espresso", "http://google.com", myBitmap, "coffee");
        FavoriteDrinkEntry drinkEntry2 = new FavoriteDrinkEntry("espresso2", "http://google.com", myBitmap, "coffee");
        FavoriteDrinkEntry drinkEntry3 = new FavoriteDrinkEntry("espresso3", "http://google.com", myBitmap, "coffee");
        mDb.drinkDao().insertDrink(drinkEntry);
        mDb.drinkDao().insertDrink(drinkEntry2);
        mDb.drinkDao().insertDrink(drinkEntry3);

    }

    @Override
    public void onItemsFetched(List<Drink> drinks) {

        if (drinks != null) {

            for (Drink drink : drinks) {
                URL imgURL = null;
                try {
                    imgURL = new URL(APIClient.BASE_URL + drink.getUrl());
                    drink.setUrl(imgURL.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "" + drink.getName() + " " + imgURL);

            }

            drinkAdapter = new DrinkAdapter(drinks);
            recyclerView.setAdapter(drinkAdapter);
            Toast.makeText(CoffeeActivity.this, "Successfully fetched details", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CoffeeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }


}
