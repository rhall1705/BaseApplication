package personal.rowan.baseapplication.dagger;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import personal.rowan.baseapplication.BaseApplication;
import personal.rowan.baseapplication.data.loader.LoaderFactory;
import personal.rowan.baseapplication.data.manager.DatabaseManager;

@Module
public class ApplicationModule {

	private BaseApplication mApplication;

	public ApplicationModule(BaseApplication application) {
		mApplication = application;
	}

	@Provides
	@Singleton
	@NonNull
	BaseApplication providesPaperApplication() {
		return mApplication;
	}

	@Provides
	@Singleton
	@NonNull
	DatabaseManager providesDatabaseManager() {
		return DatabaseManager.getInstance(mApplication);
	}

	@Provides
	@Singleton
	@NonNull
	LoaderFactory providesLoaderFactory() {
		return LoaderFactory.getInstance();
	}

}
