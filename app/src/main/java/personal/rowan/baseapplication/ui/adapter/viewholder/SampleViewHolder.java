package personal.rowan.baseapplication.ui.adapter.viewholder;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import personal.rowan.baseapplication.R;
import personal.rowan.baseapplication.data.model.SampleObject;
import personal.rowan.baseapplication.ui.adapter.BaseRecyclerViewAdapter;

public class SampleViewHolder
		extends BaseViewHolder<SampleObject> {

	private TextView tvTitle;
	private TextView tvDetail;

	public SampleViewHolder(BaseRecyclerViewAdapter<SampleObject> adapter, View itemView) {
		super(adapter, itemView);
		tvTitle = (TextView) itemView.findViewById(R.id.listitem_sample_item_title_tv);
		tvDetail = (TextView) itemView.findViewById(R.id.listitem_sample_item_detail_tv);
	}

	@Override
	public void onBindView(SampleObject item) {
		tvTitle.setText(item.getTitle());
		tvDetail.setText(item.getDetail());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, R.id.listitem_sample_item_context_edit, Menu.NONE, R.string.listitem_sample_item_context_edit);
		menu.add(Menu.NONE, R.id.listitem_sample_item_context_delete, Menu.NONE, R.string.listitem_sample_item_context_delete);
	}

}
