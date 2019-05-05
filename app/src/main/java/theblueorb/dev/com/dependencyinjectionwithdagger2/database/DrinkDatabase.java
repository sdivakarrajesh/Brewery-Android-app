package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {DrinkEntry.class},version = 1,exportSchema = false)
@TypeConverters(ImageConvertor.class)
public abstract class DrinkDatabase extends RoomDatabase {

    private static final String TAG = DrinkDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "drinks_db";
    private static final Object LOCK = new Object();
    private static DrinkDatabase instance;

    public static DrinkDatabase getInstance(Context context){
        if (instance==null){
            synchronized (LOCK){
                Log.e(TAG,"Creating new database");
                instance = Room.databaseBuilder(context.getApplicationContext(), DrinkDatabase.class,DrinkDatabase.DATABASE_NAME )
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.e(TAG,"Getting database instance");
        return instance;

    }

    public abstract DrinkDao drinkDao();
}
