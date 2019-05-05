package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "drinks")
public class DrinkEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String drinkName;
    private String drinkImageURL;
    private Bitmap drinkImage;

    @Ignore
    public DrinkEntry(int id, String drinkName, String drinkImageURL, Bitmap drinkImage) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkImageURL = drinkImageURL;
        this.drinkImage = drinkImage;
    }

    public DrinkEntry(String drinkName, String drinkImageURL, Bitmap drinkImage) {
        this.drinkName = drinkName;
        this.drinkImageURL = drinkImageURL;
        this.drinkImage = drinkImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkImageURL() {
        return drinkImageURL;
    }

    public void setDrinkImageURL(String drinkImageURL) {
        this.drinkImageURL = drinkImageURL;
    }

    public Bitmap getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkImage(Bitmap drinkImage) {
        this.drinkImage = drinkImage;
    }

}
