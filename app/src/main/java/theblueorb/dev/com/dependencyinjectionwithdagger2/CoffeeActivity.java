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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.DrinkDatabase;
import theblueorb.dev.com.dependencyinjectionwithdagger2.database.DrinkEntry;

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

    private DrinkDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ButterKnife.bind(this);

        mDb = DrinkDatabase.getInstance(getApplicationContext());
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
            case R.id.getFromDbButton:{
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
                TextView tv = new TextView(CoffeeActivity.this);
                tv.setText(drink.getName());
                ll.addView(tv);
                ImageView imgView = new ImageView(CoffeeActivity.this);
                URL imgURL = null;
                try {
                    imgURL = new URL(APIClient.BASE_URL + drink.getUrl());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                RequestOptions myOptions = new RequestOptions()
                        .fitCenter()
                        .override(200, 200);

                Glide.with(CoffeeActivity.this)
                        .load(imgURL)
                        .apply(myOptions)
                        .into(imgView);
                ll.addView(imgView);
                Log.e(CoffeeActivity.this.toString(), drink.getName() + imgURL);

            }
            Toast.makeText(CoffeeActivity.this, "Successfully fetched details", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CoffeeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }


}
