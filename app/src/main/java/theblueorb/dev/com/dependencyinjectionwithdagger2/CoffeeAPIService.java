package theblueorb.dev.com.dependencyinjectionwithdagger2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoffeeAPIService {

@GET("api/search")
    Call<List<Coffee>> fetchDrinks(@Query("drink") String drink);

    @GET("api/search")
    Call<List<Coffee>> fetchDrinks(@Query("drink") String drink, @Query("limit") int limit);
}
