package net.oschina.app.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.oschina.app.R;
import net.oschina.app.base.ListBaseAdapter;
import net.oschina.app.bean.SearchResult;
import net.oschina.app.util.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchAdapter extends ListBaseAdapter<SearchResult> {

	@SuppressLint("InflateParams")
	@Override
	protected View getRealView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null || convertView.getTag() == null) {
			convertView = getLayoutInflater(parent.getContext()).inflate(
					R.layout.list_cell_news, null);
			vh = new ViewHolder(convertView);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		SearchResult item = (SearchResult) mDatas.get(position);

		vh.title.setText(item.getTitle());
		 
		if (item.getDescription() == null || StringUtils.isEmpty(item.getDescription())) {
			vh.description.setVisibility(View.GONE);
		} else {
			vh.description.setVisibility(View.VISIBLE);
			vh.description.setText(item.getDescription());
		}
		
		if (!StringUtils.isEmpty(item.getAuthor())) {
			vh.source.setText(item.getAuthor());
		} else {
			vh.source.setVisibility(View.GONE);
		}
		
		if (!StringUtils.isEmpty(item.getPubDate())) {
			vh.time.setText(StringUtils.friendly_time(item.getPubDate()));
		} else {
			vh.time.setVisibility(View.GONE);
		}
		
		vh.tip.setVisibility(View.GONE);
		vh.comment_count.setVisibility(View.GONE);
		return convertView;
	}

	static class ViewHolder {
		@Bind(R.id.tv_title)TextView title;
		@Bind(R.id.tv_description)TextView description;
		@Bind(R.id.tv_source)TextView source;
		@Bind(R.id.tv_time)TextView time;
		@Bind(R.id.tv_comment_count)TextView comment_count;
		@Bind(R.id.iv_tip)ImageView tip;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
