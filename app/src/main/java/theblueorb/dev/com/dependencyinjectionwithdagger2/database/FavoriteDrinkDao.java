package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDrinkDao {

    @Insert
    void insertDrink(FavoriteDrinkEntry drink);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDrink(FavoriteDrinkEntry drink);

    @Delete
    void deleteDrink(FavoriteDrinkEntry drink);

    @Query("select * from drinks order by drinkName")
    List<FavoriteDrinkEntry> loadAllDrinks();

    @Query("delete from drinks")
    void deleteTable();
}
