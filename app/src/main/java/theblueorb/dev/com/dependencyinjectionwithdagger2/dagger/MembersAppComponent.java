package theblueorb.dev.com.dependencyinjectionwithdagger2.dagger;

import javax.inject.Singleton;

import dagger.Component;
import theblueorb.dev.com.dependencyinjectionwithdagger2.MainActivity;

@Singleton
@Component(modules = MembersDataModule.class)
public interface MembersAppComponent {
    void inject(MainActivity mainActivity);
}
