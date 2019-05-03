package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://starbucks-coffee-api.herokuapp.com/";
    public static Retrofit retrofit = null;
    static List<Coffee> drinks = new ArrayList<Coffee>();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void fetchCoffee(final Context context, final onItemsFetchedFromNetwork listener) {

        CoffeeAPIService coffeeAPIService = APIClient.getClient().create(CoffeeAPIService.class);
        Call<List<Coffee>> call = coffeeAPIService.fetchDrinks("espresso");
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                if (response.code() == 200) {
                    drinks = response.body();
                    listener.onItemsFetched(drinks);
                } else {
                    listener.onItemsFetched(null);
                }
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                Toast.makeText(context, "Network call Failure" + t.fillInStackTrace(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}