package theblueorb.dev.com.dependencyinjectionwithdagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MembersDataModule {

    @Singleton
    @Provides
    MembersDataManager provideMemebersDataManager(){
        return new MembersDataManager();
    }
}
