package cn.edu.nciae.community.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.edu.nciae.community.domain.Advertisement;
import cn.edu.nciae.community.mycustom.ListViewForScrollView;
import cn.edu.nciae.community.utils.AsyncImageLoader.ImageCallback;
import cn.edu.nciae.community.utils.NetUtil;

import com.example.personal.R;

public class AdvertisementAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context context;
	private ListViewForScrollView listView;
	private ArrayList<Advertisement> ads = new ArrayList<Advertisement>();

	public AdvertisementAdapter(Context context, ArrayList<Advertisement> ads,
			ListViewForScrollView lv) {
		this.ads = ads;
		this.context = context;
		this.listView = lv;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (ads == null) {
			return 0;
		}
		return ads.size();
	}

	public void setShops(ArrayList<Advertisement> ads) {
		this.ads = ads;
	}

	@Override
	public Object getItem(int position) {
		return ads.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		Holder holder = null;
		TextView tvadsname = null;
		ImageView ivadsimg = null;
		TextView tvadscontent=null;
		if (convertView == null) {
			holder = new Holder();
			view = mInflater.inflate(R.layout.business_list_listitem, null);
			ivadsimg = (ImageView) view
					.findViewById(R.id.iv_advertisement_name);
			tvadsname = (TextView) view
					.findViewById(R.id.tv_advertisement_name);
			tvadscontent=(TextView)view.findViewById(R.id.tv_advertisement_content);
			holder.tvadsname = tvadsname;
			holder.ivadsimg = ivadsimg;
			holder.tvadscontent=tvadscontent;
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (Holder) view.getTag();
			tvadsname = holder.tvadsname;
			ivadsimg = holder.ivadsimg;
			tvadscontent=holder.tvadscontent;
		}

		Advertisement aDS = ads.get(position);
		String imgUrl = null;
		Resources res = context.getResources();
		try {
			imgUrl = res.getString(R.string.base_url) + aDS.getAdImagURL().substring(1);
		} catch (Exception e) {
			imgUrl=res.getString(R.string.base_url) +"default.png";
		}
		
		ivadsimg.setTag(imgUrl);
		Drawable cachedImage = NetUtil.asyncImageLoader.loadDrawable(imgUrl,
				new ImageCallback() {
					@Override
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl) {
						ImageView imageViewByTag = (ImageView)listView
								.findViewWithTag(imageUrl);
						if (imageViewByTag != null) {
							imageViewByTag.setImageDrawable(imageDrawable);
						}
					}
				});
		if (cachedImage==null) {
			ivadsimg.setImageResource(R.drawable.g11);
		} else {
			ivadsimg.setImageDrawable(cachedImage);
		}
		//ivadsimg.setImageResource(R.drawable.g14);
		tvadsname.setText(aDS.getAdTitle());
		tvadscontent.setText(aDS.getAdContent());
		return view;
	}

	private class Holder {
		public TextView tvadsname;
		public ImageView ivadsimg;
		public TextView tvadscontent;

	}

	
	
}
