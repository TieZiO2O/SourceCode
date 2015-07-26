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
import cn.edu.nciae.community.domain.Merchants;
import cn.edu.nciae.community.utils.AsyncImageLoader.ImageCallback;
import cn.edu.nciae.community.utils.NetUtil;

import com.example.personal.R;

public class ShopAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context context;
	private ListView listView;
	private ArrayList<Merchants> shopsimple = new ArrayList<Merchants>();

	public ShopAdapter(Context context, ArrayList<Merchants> shopsimple,
			ListView lv) {
		this.shopsimple = shopsimple;
		this.context = context;
		this.listView = lv;
		this.mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (shopsimple == null) {
			return 0;
		}
		return shopsimple.size();
	}

	public void setShops(ArrayList<Merchants> shopsimple) {
		this.shopsimple = shopsimple;
	}

	@Override
	public Object getItem(int position) {
		return shopsimple.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		Holder holder = null;
		TextView tvshopname = null;
		ImageView ivshopimg = null;
		if (convertView == null) {
			holder = new Holder();
			view = mInflater.inflate(R.layout.business_listitem, null);
			ivshopimg = (ImageView) view
					.findViewById(R.id.shangjia_listitem_name_image);
			tvshopname = (TextView) view
					.findViewById(R.id.shangjia_listitem_name_text);

			holder.tvshopname = tvshopname;
			holder.ivshopimg = ivshopimg;
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (Holder) view.getTag();
			tvshopname = holder.tvshopname;
			ivshopimg = holder.ivshopimg;
		}

		Merchants shopSimple = shopsimple.get(position);
		String imgUrl = null;
		Resources res = context.getResources();
		try {
			imgUrl = res.getString(R.string.base_url) + shopSimple.getShopLogo().substring(1);
		} catch (Exception e) {
			imgUrl=res.getString(R.string.base_url) +"default.png";
		}
		
		ivshopimg.setTag(imgUrl);
		Drawable cachedImage = NetUtil.asyncImageLoader.loadDrawable(imgUrl,
				new ImageCallback() {
					@Override
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl) {
						ImageView imageViewByTag = (ImageView) listView
								.findViewWithTag(imageUrl);
						if (imageViewByTag != null) {
							imageViewByTag.setImageDrawable(imageDrawable);
						}
					}
				});
		if (cachedImage==null) {
			ivshopimg.setImageResource(R.drawable.g11);
		} else {
			ivshopimg.setImageDrawable(cachedImage);
		}
		//ivshopimg.setImageResource(R.drawable.g14);
		tvshopname.setText(shopSimple.getRealName());
		return view;
	}

	private class Holder {
		public TextView tvshopname;
		public ImageView ivshopimg;

	}

}
