package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {FavoriteDrinkEntry.class}, version = 1, exportSchema = false)
@TypeConverters(ImageConvertor.class)
public abstract class FavoriteDrinksDatabase extends RoomDatabase {

    private static final String TAG = FavoriteDrinksDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "favorite_drinks_db";
    private static final Object LOCK = new Object();
    private static FavoriteDrinksDatabase instance;

    public static FavoriteDrinksDatabase getInstance(Context context) {
        if (instance==null){
            synchronized (LOCK){
                Log.e(TAG,"Creating new database");
                instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteDrinksDatabase.class, FavoriteDrinksDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.e(TAG,"Getting database instance");
        return instance;

    }

    public abstract FavoriteDrinkDao drinkDao();
}
