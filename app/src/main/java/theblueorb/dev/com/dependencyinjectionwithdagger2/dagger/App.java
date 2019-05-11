package theblueorb.dev.com.dependencyinjectionwithdagger2.dagger;

import android.app.Application;

import theblueorb.dev.com.dependencyinjectionwithdagger2.DaggerMembersAppComponent;

public class App extends Application {
    private static App app;
    private MembersAppComponent membersAppComponent;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        membersAppComponent = DaggerMembersAppComponent.builder()
                                .membersDataModule(new MembersDataModule())
                                .build();
    }

    public MembersAppComponent getMembersAppComponent() {
        return this.membersAppComponent;
    }
}
