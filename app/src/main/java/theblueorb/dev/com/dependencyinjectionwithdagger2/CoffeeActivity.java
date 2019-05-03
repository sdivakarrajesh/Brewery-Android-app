package theblueorb.dev.com.dependencyinjectionwithdagger2;

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

public class CoffeeActivity extends AppCompatActivity implements onItemsFetchedFromNetwork{


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
        APIClient.fetchCoffee(CoffeeActivity.this,this );
    }


    @OnClick(R.id.button)
    public void onClick() {
        fetchAndLoadViews();
    }

    @Override
    public void onItemsFetched(List<Coffee> drinks) {
        if(drinks!=null){
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
        }
        else{
            Toast.makeText(CoffeeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }
}
