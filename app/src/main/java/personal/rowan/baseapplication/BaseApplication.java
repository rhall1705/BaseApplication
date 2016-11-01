package personal.rowan.baseapplication;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import personal.rowan.baseapplication.dagger.ApplicationComponent;
import personal.rowan.baseapplication.dagger.ApplicationModule;

public class BaseApplication
		extends Application {

	private static BaseApplication instance;

	private ApplicationComponent mApplicationComponent;

	public static synchronized BaseApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		mApplicationComponent = createComponent();
		registerActivityLifecycleCallbacks(new LifecycleHandler());
	}

	@Singleton
	@Component(modules = ApplicationModule.class)
	public interface BaseComponent
			extends ApplicationComponent {
	}

	protected ApplicationComponent createComponent() {
		return DaggerBaseApplication_BaseComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	public ApplicationComponent component() {
		return mApplicationComponent;
	}

}
