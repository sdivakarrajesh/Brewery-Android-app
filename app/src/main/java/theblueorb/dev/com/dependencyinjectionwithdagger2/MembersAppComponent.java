package theblueorb.dev.com.dependencyinjectionwithdagger2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MembersDataModule.class)
public interface MembersAppComponent {
    void inject(MainActivity mainActivity);
}
