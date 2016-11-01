package personal.rowan.baseapplication.ui.fragment.dialog;

import personal.rowan.baseapplication.R;

public class CreateSampleObjectDialogFragment
		extends SampleObjectDialogFragment {

	public static final String TAG = "CreateSampleObjectDialogFragment";

	@Override
	protected String title() {
		return getString(R.string.dialog_fragment_create_sample_object_title);
	}

	@Override
	public String tag() {
		return TAG;
	}

}
