package personal.rowan.baseapplication.ui.fragment.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import javax.inject.Inject;

import personal.rowan.baseapplication.R;
import personal.rowan.baseapplication.data.manager.DatabaseManager;
import personal.rowan.baseapplication.data.model.SampleObject;

public class EditSampleObjectDialogFragment
		extends SampleObjectDialogFragment {

	public static final String TAG = "EditSampleObjectDialogFragment";
	public static final String ARGS_SAMPLE_OBJECT_ID = "ARGS_SAMPLE_OBJECT_ID";
	public static final String ARGS_POSITION = "ARGS_POSITION";

	@Inject
	DatabaseManager mDatabaseManager;

	private SampleObject mSampleObject;
	private int mPosition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		component().inject(this);

		Bundle args = getArguments();
		if (args != null) {
			mPosition = args.getInt(ARGS_POSITION);
			String sampleObjectId = args.getString(ARGS_SAMPLE_OBJECT_ID);
			mSampleObject = mDatabaseManager.loadSampleObjectById(sampleObjectId);
			if(mSampleObject == null) {
				Toast.makeText(getContext(), getString(R.string.dialog_fragment_edit_sample_object_error), Toast.LENGTH_SHORT).show();
				dismiss();
			}
		}
	}

	@Override
	protected void afterShown() {
		super.afterShown();
		etTitle.setText(mSampleObject.getTitle());
		etDetail.setText(mSampleObject.getDetail());
	}

	@Override
	protected String title() {
		return getString(R.string.dialog_fragment_edit_sample_object_title);
	}

	@Override
	public String tag() {
		return TAG;
	}

	@Override
	public SampleObject getSampleObject() {
		String title = getTitle();
		String detail = getDetail();
		if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(detail)) {
			mSampleObject.setTitle(title);
			mSampleObject.setDetail(detail);
		}

		return mSampleObject;
	}

	public int getPosition() {
		return mPosition;
	}

}
