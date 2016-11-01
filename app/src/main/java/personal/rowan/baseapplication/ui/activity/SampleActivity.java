package personal.rowan.baseapplication.ui.activity;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import personal.rowan.baseapplication.R;
import personal.rowan.baseapplication.data.loader.LoaderFactory;
import personal.rowan.baseapplication.data.loader.LoaderId;
import personal.rowan.baseapplication.data.manager.DatabaseManager;
import personal.rowan.baseapplication.data.model.SampleObject;
import personal.rowan.baseapplication.ui.adapter.SampleAdapter;
import personal.rowan.baseapplication.ui.fragment.dialog.BaseDialogFragment;
import personal.rowan.baseapplication.ui.fragment.dialog.CreateSampleObjectDialogFragment;
import personal.rowan.baseapplication.ui.fragment.dialog.EditSampleObjectDialogFragment;

public class SampleActivity
		extends BaseActivity
		implements LoaderManager.LoaderCallbacks<List<SampleObject>> {

	@Inject
	DatabaseManager mDatabaseManager;
	@Inject
	LoaderFactory mLoaderFactory;

	private SampleAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		component().inject(this);
		setContentView(R.layout.activity_sample);
		setupViews();
		startLoader(LoaderId.SAMPLE, null, this);
	}

	private void setupViews() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.activity_sample_tb);
		setToolbar(toolbar, getString(R.string.activity_sample_title));

		RecyclerView rvSampleObjects = (RecyclerView) findViewById(R.id.activity_sample_rv);
		rvSampleObjects.setLayoutManager(new LinearLayoutManager(this));
		rvSampleObjects.setAdapter(mAdapter = new SampleAdapter());
		mAdapter.setContextMenuEnabled(true);
		mAdapter.setShouldContextMenuShowOnSingleClick(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_sample_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.activity_sample_add:
				new CreateSampleObjectDialogFragment()
						.show(this);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {
		int position = mAdapter.getContextSelectedPosition();
		SampleObject object = mAdapter.getItem(position);
		if(object != null) {
			switch (menuItem.getItemId()) {
				case R.id.listitem_sample_item_context_edit:
					Bundle args = new Bundle();
					args.putInt(EditSampleObjectDialogFragment.ARGS_POSITION, position);
					args.putString(EditSampleObjectDialogFragment.ARGS_SAMPLE_OBJECT_ID, object.getId());
					EditSampleObjectDialogFragment editSampleObjectDialogFragment = new EditSampleObjectDialogFragment();
					editSampleObjectDialogFragment.setArguments(args);
					editSampleObjectDialogFragment.show(this);
					break;
				case R.id.listitem_sample_item_context_delete:
					mDatabaseManager.deleteSampleObject(object.getId());
					mAdapter.removeItemAtPosition(position);
					break;
			}
		}
		return super.onContextItemSelected(menuItem);
	}

	@Override
	public void onDialogPositiveClick(BaseDialogFragment dialogFragment) {
		switch(dialogFragment.tag()) {
			case CreateSampleObjectDialogFragment.TAG:
				CreateSampleObjectDialogFragment createSampleObjectDialogFragment = (CreateSampleObjectDialogFragment) dialogFragment;
				SampleObject newObject = createSampleObjectDialogFragment.getSampleObject();
				if(newObject != null) {
					mDatabaseManager.saveSampleObject(newObject);
					mAdapter.insertItem(newObject);
				}
				break;
			case EditSampleObjectDialogFragment.TAG:
				EditSampleObjectDialogFragment editSampleObjectDialogFragment = (EditSampleObjectDialogFragment) dialogFragment;
				SampleObject editedObject = editSampleObjectDialogFragment.getSampleObject();
				if(editedObject != null) {
					mDatabaseManager.saveSampleObject(editedObject);
				}
				int position = editSampleObjectDialogFragment.getPosition();
				if(position >= 0 && position < mAdapter.getItemCount()) {
					mAdapter.changeItemAtPosition(editedObject, position);
				}
				break;
			default:
				super.onDialogPositiveClick(dialogFragment);
		}
	}

	@Override
	public Loader<List<SampleObject>> onCreateLoader(int id, Bundle args) {
		switch(id) {
			case LoaderId.SAMPLE:
				return mLoaderFactory.createSampleLoader(this, args);
		}

		return null;
	}

	@Override
	public void onLoadFinished(Loader<List<SampleObject>> loader, List<SampleObject> data) {
		switch(loader.getId()) {
			case LoaderId.SAMPLE:
				mAdapter.setData(data);
				break;
		}
	}

	@Override
	public void onLoaderReset(Loader<List<SampleObject>> loader) {
		mAdapter.setData(new ArrayList<SampleObject>());
	}

}
