package theblueorb.dev.com.dependencyinjectionwithdagger2.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import androidx.room.TypeConverter;

public class ImageConvertor {

    @TypeConverter
    public static String toBitmap(Bitmap image)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    @TypeConverter
    public static Bitmap toImage(String data){
        byte[] decodedByte = Base64.decode(data, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,      decodedByte.length);
    }

}
