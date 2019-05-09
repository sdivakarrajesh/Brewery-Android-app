package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.DrinkDatabase;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.DrinkEntry;
import theblueorb.dev.com.dependencyinjectionwithdagger2.drinksRecyclerView.DrinkAdapter;

public class CoffeeActivity extends AppCompatActivity implements onItemsFetchedFromNetwork {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.ll)
    LinearLayout ll;
    List<Drink> drinks;
    @BindView(R.id.insertImgToDbButton)
    Button insertImgToDbButton;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.url)
    TextView url;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.drinks_recycler_view)
    RecyclerView recyclerView;

    private DrinkDatabase mDb;
    private RecyclerView.LayoutManager layoutManager;
    private DrinkAdapter drinkAdapter;

    private static final String TAG = CoffeeActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ButterKnife.bind(this);
        mDb = DrinkDatabase.getInstance(getApplicationContext());

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void fetchAndLoadViews() {
        APIClient.fetchCoffee(CoffeeActivity.this, this);
    }

    @OnClick({R.id.button, R.id.insertImgToDbButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button: {
                fetchAndLoadViews();
                break;
            }

            case R.id.insertImgToDbButton: {
                insertImageToDatabase();
                break;
            }
            case R.id.getFromDbButton: {
                loadDataFromFromDb();
            }

        }
    }

    private void loadDataFromFromDb() {

        List<DrinkEntry> entries = mDb.drinkDao().loadAllDrinks();
        name.setText(entries.get(0).getDrinkName());
        url.setText(entries.get(0).getDrinkImageURL());
        img.setImageBitmap(entries.get(0).getDrinkImage());

    }

    private void insertImageToDatabase() {

        InputStream inputStream = getResources().openRawResource(
                getResources().getIdentifier("water",
                        "raw", getPackageName()));
        Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
        DrinkEntry drinkEntry = new DrinkEntry("espresso", "http://google.com", myBitmap);

        mDb.drinkDao().insertDrink(drinkEntry);
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
