package theblueorb.dev.com.dependencyinjectionwithdagger2.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import theblueorb.dev.com.dependencyinjectionwithdagger2.models.Drink;

public interface CoffeeAPIService {

@GET("api/search")
    Call<List<Drink>> fetchDrinks(@Query("drink") String drink);

    @GET("api/search")
    Call<List<Drink>> fetchDrinks(@Query("drink") String drink, @Query("limit") int limit);
}
