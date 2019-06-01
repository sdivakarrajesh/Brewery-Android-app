package theblueorb.dev.com.dependencyinjectionwithdagger2.network;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import theblueorb.dev.com.dependencyinjectionwithdagger2.models.Drink;
import theblueorb.dev.com.dependencyinjectionwithdagger2.onItemsFetchedFromNetworkListener;

public class APIClient {
    public static final String BASE_URL = "https://starbucks-coffee-api.herokuapp.com/";
    public static Retrofit retrofit = null;
    static List<Drink> drinks = new ArrayList<Drink>();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void fetchCoffee(final Context context, final onItemsFetchedFromNetworkListener listener, String drinkType, String drinkLimit) {

        CoffeeAPIService coffeeAPIService = APIClient.getClient().create(CoffeeAPIService.class);
        Call<List<Drink>> call;
        if (drinkType == null || drinkType.length() == 0) {
            drinkType = "espresso";
        }
        if (drinkLimit.equals("") || drinkLimit.equals("No Limit")) {
            call = coffeeAPIService.fetchDrinks(drinkType, 0);
        } else {
            call = coffeeAPIService.fetchDrinks(drinkType, Integer.parseInt(drinkLimit));
        }

        call.enqueue(new Callback<List<Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {
                if (response.code() == 200) {
                    drinks = response.body();
                    listener.onItemsFetched(drinks);
                } else {
                    listener.onItemsFetched(null);
                }
            }

            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                Toast.makeText(context, "Network call Failure" + t.fillInStackTrace(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}