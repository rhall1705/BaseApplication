package personal.rowan.baseapplication.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Toast;

import personal.rowan.baseapplication.BaseApplication;
import personal.rowan.baseapplication.dagger.ApplicationComponent;
import personal.rowan.baseapplication.ui.activity.BaseActivity;
import personal.rowan.baseapplication.ui.fragment.dialog.BaseDialogFragment;

public abstract class BaseFragment
		extends Fragment
		implements BaseDialogFragment.IDialogCallbacks {

	protected ApplicationComponent component() {
		return BaseApplication.getInstance().component();
	}

	protected void startLoader(int loaderId, Bundle args, LoaderManager.LoaderCallbacks callbacks) {
		LoaderManager loaderManager = getLoaderManager();
		Loader loader = loaderManager.getLoader(loaderId);
		if(loader == null) {
			loaderManager.initLoader(loaderId, args, callbacks);
		} else {
			loaderManager.restartLoader(loaderId, args, callbacks);
		}
	}

	protected void notifyLoaderContentChanged(int loaderId) {
		Loader loader = getLoaderManager().getLoader(loaderId);
		if(loader != null) {
			loader.onContentChanged();
		}
	}

	protected void showMessage(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	protected void showProgressDialog(String title, String message) {
		Activity activity = getActivity();
		if(activity != null && activity instanceof BaseActivity) {
			((BaseActivity) activity).showProgressDialog(title, message);
		}
	}

	protected void dismissProgressDialog() {
		Activity activity = getActivity();
		if(activity != null && activity instanceof BaseActivity) {
			((BaseActivity) activity).dismissProgressDialog();
		}
	}

	// Override these methods if the fragment in question uses a BaseDialogFragment
	// Switch off of the dialogFragment parameter's tag
	// Call through to super in the default case
	@CallSuper
	@Override
	public void onDialogPositiveClick(BaseDialogFragment dialogFragment) {

	}

	@CallSuper
	@Override
	public void onDialogNegativeClick(BaseDialogFragment dialogFragment) {

	}

	@CallSuper
	@Override
	public void onDialogNeutralClick(BaseDialogFragment dialogFragment) {

	}

	@CallSuper
	@Override
	public void onDialogDismiss(BaseDialogFragment dialogFragment) {

	}

}
