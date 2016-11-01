package personal.rowan.baseapplication.data.loader;

import android.support.annotation.IntDef;

// Add a new LoaderId IntDef for new loaders introduced
@IntDef({
		LoaderId.SAMPLE
})
public @interface LoaderId {
	int SAMPLE = 0;
}
