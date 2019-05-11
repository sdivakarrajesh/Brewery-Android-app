package theblueorb.dev.com.dependencyinjectionwithdagger2;

import java.util.List;

import theblueorb.dev.com.dependencyinjectionwithdagger2.models.Drink;

public interface onItemsFetchedFromNetworkListener {
    void onItemsFetched(List<Drink> drinks);
}
