package theblueorb.dev.com.dependencyinjectionwithdagger2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.usernameEt)
    TextInputEditText usernameEt;
    @BindView(R.id.passwordEt)
    TextInputEditText passwordEt;
    @BindView(R.id.signInBtn)
    Button signInBtn;

    @Inject
    MembersDataManager membersDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        App.getApp().getMembersAppComponent().inject(this);

    }

    @OnClick(R.id.signInBtn)
    public void onClick() {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(membersDataManager.isAMember(new Member(username,password))){
            Toast.makeText(MainActivity.this,"Access Granted" ,Toast.LENGTH_SHORT ).show();

        }
        else {
            Toast.makeText(MainActivity.this,"Access Denied" ,Toast.LENGTH_SHORT ).show();
        }
    }
}
