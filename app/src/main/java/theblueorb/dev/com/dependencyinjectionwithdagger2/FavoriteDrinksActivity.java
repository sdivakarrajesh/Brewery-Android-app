package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class FavoriteDrinksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_drinks);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.e(this.toString(), "changing transition");
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        return super.onOptionsItemSelected(item);
    }
}
