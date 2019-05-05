package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import java.util.List;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import theblueorb.dev.com.dependencyinjectionwithdagger2.Drink;

@Dao
public interface DrinkDao {

    @Insert
    void insertDrink(DrinkEntry drink);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateDrink(DrinkEntry drink);

    @Delete
    void deleteDrink(DrinkEntry drink);

    @Query("select * from drinks order by drinkName")
    List<DrinkEntry> loadAllDrinks();

    @Query("delete from drinks")
    void deleteTable();
}
