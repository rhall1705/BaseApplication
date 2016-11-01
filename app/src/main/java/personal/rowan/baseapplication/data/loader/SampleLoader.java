package personal.rowan.baseapplication.data.loader;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.baseapplication.data.manager.DatabaseManager;
import personal.rowan.baseapplication.data.model.SampleObject;

public class SampleLoader
		extends BaseLoader<List<SampleObject>> {

	public static final String ARGS_TITLE_FILTER = "ARGS_TITLE_FILTER";
	public static final String ARGS_DETAIL_FILTER = "ARGS_DETAIL_FILTER";

	@Inject
	DatabaseManager mDatabaseManager;

	private String mTitleFilter;
	private String mDetailFilter;

	public SampleLoader(Context context) {
		this(context, null, null);
	}

	public SampleLoader(Context context, String titleFilter, String detailFilter) {
		super(context);
		component().inject(this);
		mTitleFilter = titleFilter;
		mDetailFilter = detailFilter;
	}

	@Override
	public List<SampleObject> loadInBackground() {
		// If the application loads data remotely from network, implement that synchronously here
		// The sample application does not, so it accesses the cache directly

		return getCache();
	}

	@Override
	protected void saveToCache(List<SampleObject> data) {
		mDatabaseManager.saveSampleObjects(data);
	}

	@Override
	protected List<SampleObject> getCache() {
		if(!TextUtils.isEmpty(mTitleFilter)) {
			return mDatabaseManager.loadSampleObjectsByTitle(mTitleFilter);
		} else if(!TextUtils.isEmpty(mDetailFilter)) {
			return mDatabaseManager.loadSampleObjectsByDetail(mDetailFilter);
		}

		return mDatabaseManager.loadSampleObjects();
	}

	@Override
	protected boolean cacheInvalid() {
		List<SampleObject> cache = getCache();
		return cache == null ||
				cache.isEmpty();
	}

	public static class ArgsBuilder
			extends BaseArgsBuilder {

		public ArgsBuilder() {
			super();
		}

		public ArgsBuilder setTitleFilter(String titleFilter) {
			writeString(ARGS_TITLE_FILTER, titleFilter);
			return this;
		}

		public ArgsBuilder setDetailFilter(String detailFilter) {
			writeString(ARGS_DETAIL_FILTER, detailFilter);
			return this;
		}

	}

}
