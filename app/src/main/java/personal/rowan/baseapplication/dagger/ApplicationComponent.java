package personal.rowan.baseapplication.dagger;

import javax.inject.Singleton;

import dagger.Component;
import personal.rowan.baseapplication.data.loader.SampleLoader;
import personal.rowan.baseapplication.ui.activity.SampleActivity;
import personal.rowan.baseapplication.ui.fragment.dialog.EditSampleObjectDialogFragment;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

	// Activity
	void inject(SampleActivity sampleActivity);

	// Dialog
	void inject(EditSampleObjectDialogFragment editSampleObjectDialogFragment);

	// Loader
	void inject(SampleLoader sampleLoader);

}
