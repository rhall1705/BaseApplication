package personal.rowan.baseapplication.ui.adapter.viewholder;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;

import personal.rowan.baseapplication.BaseApplication;
import personal.rowan.baseapplication.dagger.ApplicationComponent;
import personal.rowan.baseapplication.ui.adapter.BaseRecyclerViewAdapter;

public abstract class BaseViewHolder<T>
		extends RecyclerView.ViewHolder
		implements View.OnCreateContextMenuListener, View.OnClickListener {

	private T mItem;
	protected BaseRecyclerViewAdapter<T> mAdapter;

	public BaseViewHolder(BaseRecyclerViewAdapter<T> adapter, View itemView) {
		super(itemView);
		mAdapter = adapter;
	}

	protected ApplicationComponent component() {
		return BaseApplication.getInstance().component();
	}

	public abstract void onBindView(T item);

	public void bindView(T item) {
		mItem = item;
		onBindView(mItem);
		itemView.setOnCreateContextMenuListener(mAdapter.isContextMenuEnabled() ? this : null);
	}

	protected void addClickListenerToViews(View... views) {
		for (View view : views) {
			if (view != null) {
				view.setOnClickListener(this);
			}
		}
	}

	protected void addClickListenerToViews(@IdRes int... viewIds) {
		for (int viewId : viewIds) {
			View view = itemView.findViewById(viewId);
			if (view != null) {
				view.setOnClickListener(this);
			}
		}
	}

	public void onItemClicked() {
		if(mAdapter.isContextMenuEnabled() && mAdapter.shouldContextMenuShowOnSingleClick()) {
			itemView.showContextMenu();
		}
	}

	@Override
	public void onClick(View view) {
		mAdapter.broadcastChildClick(this, getAdapterPosition(), view);
	}

	// Used in BaseRecyclerViewAdapter - override in viewholder impl
	@SuppressWarnings("UnusedParameters")
	public void onChildItemClicked(View view) {

	}

	// Override in viewholder impl
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

	}

}
