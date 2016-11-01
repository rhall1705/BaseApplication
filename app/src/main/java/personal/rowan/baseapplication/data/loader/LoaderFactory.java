package personal.rowan.baseapplication.data.loader;

import android.content.Context;
import android.os.Bundle;

public class LoaderFactory {

	private static LoaderFactory sInstance;

	public static LoaderFactory getInstance() {
		if(sInstance == null) {
			sInstance = new LoaderFactory();
		}

		return sInstance;
	}

	private LoaderFactory() {}

	public SampleLoader createSampleLoader(Context context, Bundle args) {
		if(args == null) {
			return new SampleLoader(context);
		}

		String titleFilter = args.getString(SampleLoader.ARGS_TITLE_FILTER, null);
		String detailFilter = args.getString(SampleLoader.ARGS_DETAIL_FILTER, null);
		return new SampleLoader(context, titleFilter, detailFilter);
	}

}
