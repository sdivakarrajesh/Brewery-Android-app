package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeActivity extends AppCompatActivity{


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.ll)
    LinearLayout ll;
    List<Coffee> drinks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ButterKnife.bind(this);

    }

    public void fetchAndLoadViews(){
        CoffeeAPIService coffeeAPIService = APIClient.getClient().create(CoffeeAPIService.class);
        Call<List<Coffee>> call = coffeeAPIService.fetchDrinks("espresso");
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                if (response.code() == 200) {
                    drinks = response.body();
                    for (Coffee drink : drinks) {
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
                    Toast.makeText(CoffeeActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                Toast.makeText(CoffeeActivity.this, "Failure" + t.fillInStackTrace(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.button)
    public void onClick() {
        fetchAndLoadViews();
    }

}
