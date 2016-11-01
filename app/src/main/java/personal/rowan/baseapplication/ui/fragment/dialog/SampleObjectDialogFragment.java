package personal.rowan.baseapplication.ui.fragment.dialog;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import personal.rowan.baseapplication.R;
import personal.rowan.baseapplication.data.model.SampleObject;

public abstract class SampleObjectDialogFragment
		extends BaseDialogFragment {

	protected EditText etTitle;
	protected EditText etDetail;

	@Override
	protected int layoutResource() {
		return R.layout.dialog_fragment_sample_object;
	}

	@Override
	protected void inflateLayout(View view) {
		etTitle = (EditText) view.findViewById(R.id.dialog_fragment_sample_object_title_et);
		etDetail = (EditText) view.findViewById(R.id.dialog_fragment_sample_object_detail_et);
	}

	@Override
	protected String positiveButtonText() {
		return getString(R.string.dialog_fragment_sample_object_btn_positive);
	}

	@Override
	protected void afterShown() {
		final Button positiveButton = getPositiveButton();
		positiveButton.setEnabled(isValid());
		TextWatcher textWatcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				boolean enabled = isValid();
				positiveButton.setEnabled(enabled);
				positiveButton.setAlpha(enabled ? 1 : .5f);
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		};
		etTitle.addTextChangedListener(textWatcher);
		etDetail.addTextChangedListener(textWatcher);
	}

	private boolean isValid() {
		String title = getTitle();
		String detail = getDetail();
		return !TextUtils.isEmpty(title) && !TextUtils.isEmpty(detail);
	}

	protected String getTitle() {
		return etTitle.getText().toString();
	}

	protected String getDetail() {
		return etDetail.getText().toString();
	}

	public SampleObject getSampleObject() {
		String title = getTitle();
		String detail = getDetail();
		if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(detail)) {
			return new SampleObject(title, detail);
		}

		return null;
	}

}
