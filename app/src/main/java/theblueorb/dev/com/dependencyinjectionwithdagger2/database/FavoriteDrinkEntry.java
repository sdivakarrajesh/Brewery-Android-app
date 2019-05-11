package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "drinks")
public class FavoriteDrinkEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String drinkName;
    private String drinkImageURL;
    private Bitmap drinkImage;
    private String drinkType;

    @Ignore
    public FavoriteDrinkEntry(int id, String drinkName, String drinkImageURL, Bitmap drinkImage, String drinkType) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkImageURL = drinkImageURL;
        this.drinkImage = drinkImage;
        this.drinkType = drinkType;
    }

    public FavoriteDrinkEntry(String drinkName, String drinkImageURL, Bitmap drinkImage, String drinkType) {
        this.drinkName = drinkName;
        this.drinkImageURL = drinkImageURL;
        this.drinkImage = drinkImage;
        this.drinkType = drinkType;
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

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

}
