package personal.rowan.baseapplication.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import personal.rowan.baseapplication.BaseApplication;
import personal.rowan.baseapplication.dagger.ApplicationComponent;
import personal.rowan.baseapplication.ui.fragment.dialog.BaseDialogFragment;

public abstract class BaseActivity
		extends AppCompatActivity
		implements BaseDialogFragment.IDialogCallbacks {

	private ProgressDialog mProgressDialog;

	protected ApplicationComponent component() {
		return BaseApplication.getInstance().component();
	}

	protected void startLoader(int loaderId, Bundle args, LoaderManager.LoaderCallbacks callbacks) {
		LoaderManager loaderManager = getSupportLoaderManager();
		Loader loader = loaderManager.getLoader(loaderId);
		if (loader == null) {
			loaderManager.initLoader(loaderId, args, callbacks);
		} else {
			loaderManager.restartLoader(loaderId, args, callbacks);
		}
	}

	protected void notifyLoaderContentChanged(int loaderId) {
		Loader loader = getSupportLoaderManager().getLoader(loaderId);
		if (loader != null) {
			loader.onContentChanged();
		}
	}

	protected void showToastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void showProgressDialog(String title, String message) {
		mProgressDialog = ProgressDialog.show(this, title, message, true);
	}

	public void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissProgressDialog();
	}

	protected void setToolbar(Toolbar toolbar, String title) {
		setToolbar(toolbar, title, false);
	}

	protected void setToolbar(Toolbar toolbar, String title, boolean setUpButton) {
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		setTitle(title);
		if(setUpButton && actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	// Override this method for specific onUpPressed behavior
	protected void onUpPressed() {
		onBackPressed();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onUpPressed();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	// Override these methods if the activity in question uses a BaseDialogFragment
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