package personal.rowan.baseapplication.ui.adapter;

import android.view.View;

import personal.rowan.baseapplication.R;
import personal.rowan.baseapplication.data.model.SampleObject;
import personal.rowan.baseapplication.ui.adapter.viewholder.BaseViewHolder;
import personal.rowan.baseapplication.ui.adapter.viewholder.SampleViewHolder;

public class SampleAdapter
		extends BaseRecyclerViewAdapter<SampleObject> {

	private static final int VIEW_TYPE_SAMPLE_OBJECT = 1;

	@Override
	public int getItemViewType(int position) {
		return VIEW_TYPE_SAMPLE_OBJECT;
	}

	@Override
	protected int getLayoutId(int viewType) {
		switch(viewType) {
			case VIEW_TYPE_SAMPLE_OBJECT:
				return R.layout.listitem_sample_item;
			default:
				return 0;
		}
	}

	@Override
	protected BaseViewHolder<SampleObject> buildViewHolder(int viewType, View view) {
		switch(viewType) {
			case VIEW_TYPE_SAMPLE_OBJECT:
				return new SampleViewHolder(this, view);
			default:
				return null;
		}
	}

}
