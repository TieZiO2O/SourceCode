package cn.edu.nciae.community;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nciae.community.app.CommunityApp;
import cn.edu.nciae.community.domain.Merchants;
import cn.edu.nciae.community.domain.ShopImg;
import cn.edu.nciae.community.mycustom.ImageDownloaderTask;
import cn.edu.nciae.community.mycustom.MyDialog;
import cn.edu.nciae.community.mycustom.MyProgressDialog;
import cn.edu.nciae.community.mycustom.MyToast;
import cn.edu.nciae.community.net.Httphelper;
import cn.edu.nciae.community.utils.GsonUtils;
import cn.edu.nciae.community.utils.NetUtil;

import com.example.personal.R;

public class Business_InfoActivity extends Activity {
	String shangpin_position, class_position;
	int shangpin_position_int, class_position_int;
	ArrayList<String> images = null;// 用来暂存商家其他照片，只有当点击查看时才会起作用
	Handler handler = new Handler();
	RelativeLayout shangjia_xiangxi_title_back_rl;
	LinearLayout shangjia_xiangxi_shoucang_ll;
	ImageView iViewHead;
	TextView tViewName;
	TextView tViewInfo;
	TextView tViewKnow;
	TextView tViewAddress;
	String headName;
	String phone;
	String FLAG;
	String userid;
	String shopid;
	ImageView shoucang;
	int click = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.business_info);
		shangjia_xiangxi_title_back_rl = (RelativeLayout) findViewById(R.id.shangjia_xiangxi_title_back_rl);
		shoucang = (ImageView) findViewById(R.id.shangjia_xiangxi_shoucang_imageview);
		iViewHead = (ImageView) findViewById(R.id.shangjia_xiangxi_imageview);// 头像
		tViewName = (TextView) findViewById(R.id.shangjia_xiangxi_textview);// 商家名称
		tViewInfo = (TextView) findViewById(R.id.shangjia_xiangxi_shangpin_info);// 商家物品信息
		tViewKnow = (TextView) findViewById(R.id.shangjia_xiangxi_cus_know);// 顾客须知
		tViewAddress = (TextView) findViewById(R.id.shangjia_xiangxi_address);// 地址
		shangjia_xiangxi_shoucang_ll=(LinearLayout)findViewById(R.id.shangjia_xiangxi_shoucang_ll);
		Bundle bundle = this.getIntent().getExtras();// 接收传过来的数据
		phone = bundle.getString("phone");
		userid = bundle.getString("id");
		FLAG=bundle.getString("flag");
		if (FLAG.equals("from_business_all_user")) {
			//setInfo(bundle);
			shopid=bundle.getString("id",null);
			shangjia_xiangxi_shoucang_ll.setVisibility(View.VISIBLE);
			getDetailShopInfo(shopid);
		}
		else if(FLAG.equals("from_personal_mycollection"))
		{
			shopid=bundle.getString("id",null);
			shangjia_xiangxi_shoucang_ll.setVisibility(View.VISIBLE);
			shoucang.setBackgroundResource(R.drawable.shangjia_shoucang2);
			getDetailShopInfo(shopid);	
		}
		else if(FLAG.equals("from_fragment1_advertisement")) {

			String shopperid=bundle.getString("shopperid",null);
			getDetailShopInfo(shopperid);	
		}
		shangjia_xiangxi_title_back_rl
		.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	// 填充商家信息
	private void setInfo(Bundle bundle) {
		iViewHead.setImageResource(R.drawable.g11);// 先设置默认图片，后期获取了再更新;
		tViewName.setText(bundle.getString("name"));// 设置商家名称
		tViewInfo.setText(bundle.getString("info"));// 商家信息
		tViewKnow.setText(bundle.getString("cusKnow"));
		tViewAddress.setText(bundle.getString("address"));

		// 获取商家其他照片存起来
		images = bundle.getStringArrayList("images");

		if (bundle.getString("image") != null) {
			CommunityApp.object = Business_InfoActivity.class;
			new ImageDownloaderTask<Business_InfoActivity>(
					Business_InfoActivity.this).execute(new String[] { bundle
							.getString("image") });// 去SD卡和网络找
		}
	}

	// 从SDCard或网络获取图片后的回调方法，通知listview更新
	public void onComplete(String url, Bitmap bitmap) {
		iViewHead.setImageBitmap(bitmap);
	}

	// 获取Shangjia_ListActivity传递的商品id
	public void getIntentId() {
		Intent intent = getIntent();
		shangpin_position = intent.getStringExtra("shangpin_position");
		if (shangpin_position != null)
			shangpin_position_int = Integer.parseInt(shangpin_position);
		class_position = intent.getStringExtra("class_position");
		if (class_position != null)
			class_position_int = Integer.parseInt(class_position);
	}

	// 跳转到图文详情界面
	public void shangjia_xiangxi_tuwen(View view) {
		//new MyToast(getApplicationContext(), "图文详情", "暂未开放", 200);
		if (images.size()==0) {
			Toast.makeText(Business_InfoActivity.this, "商家暂未提供更多图片", 0).show();
			return;
		}
		Intent intent = new Intent(this, Business_ImagesWitcherActivity.class);
		intent.putStringArrayListExtra("images", images);
		startActivity(intent);
	}

	// 拨打商家电话
	public void shangjia_xiangxi_cell(View view) {
		if (phone.length() != 0) {
			Dialog dialog = null;
			MyDialog.Builder customBuilder = new
					MyDialog.Builder(Business_InfoActivity.this);
			customBuilder.setTitle("提醒")
			.setMessage("是否拨打商家电话？")
			.setNegativeButton("取消", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton("确定", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					if (phone.length() != 0) {
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:" + phone));
						startActivity(callIntent);
					} else {
						Toast.makeText(getBaseContext(), "拨打电话无效", 0).show();
					}
				}
			});
			dialog = customBuilder.create();
			dialog.show();
		} else {
			Toast.makeText(getBaseContext(), "拨打电话无效", 0).show();
		}
	}

	// 收藏图片点击时的事件,收藏或取消收藏
	@SuppressLint("ShowToast")
	public void shoucang(View v) {
		if (FLAG.equals("from_business_all_user")) {//正常进入,收藏

			if (CommunityApp.getUser() != null) {
				new Thread() {

					@Override
					public void run() {
						try {
							HttpPost httpPost = new HttpPost(getResources()
									.getString(R.string.base_url)
									+ "member/addcollection.do");

							ArrayList<NameValuePair> nvs = new ArrayList<NameValuePair>();
							NameValuePair nameValuePair = new BasicNameValuePair(
									"memberid", CommunityApp.getUserId());
							nvs.add(nameValuePair);
							nameValuePair = new BasicNameValuePair("userid", userid);
							nvs.add(nameValuePair);

							httpPost.setEntity(new UrlEncodedFormEntity(nvs,
									"utf-8"));
							final String result = Httphelper
									.getValueFromNet(httpPost);
							if (result.indexOf("success") == 0) {
								handler.post(new Runnable() {
									@Override
									public void run() {
										Toast.makeText(getBaseContext(), "收藏成功", 0)
										.show();
										shoucang.setBackgroundResource(R.drawable.shangjia_shoucang2);
										//									Business_InfoActivity.this.finish();
									}
								});
							} else if (result.indexOf("failed") == 0) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										Toast.makeText(getBaseContext(), "收藏失败", 0)
										.show();
									}
								});
							} else if (result.indexOf("already") == 0) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										// pb.setVisibility(View.INVISIBLE);
										Toast.makeText(getBaseContext(), "已经收藏过", 0)
										.show();
										shoucang.setBackgroundResource(R.drawable.shangjia_shoucang2);
									}
								});
							}

						} catch (Exception e) {
							e.printStackTrace();
							handler.post(new Runnable() {
								@Override
								public void run() {
									// pb.setVisibility(View.INVISIBLE);
									Toast.makeText(CommunityApp.context,
											"服务器去外星考察啦...", 0).show();
								}
							});
						}
					}
				}.start();
			} else {
				Intent intent = new Intent(Business_InfoActivity.this,
						Personal_LoginActivity.class);
				startActivity(intent);
				new MyToast(getApplicationContext(), "温馨提醒", "请先登录", Toast.LENGTH_SHORT);
			}

		}else if (FLAG.equals("from_personal_mycollection")){//从收藏进来的，取消收藏
			//			Toast.makeText(getApplicationContext(), "取消收藏", 0).show();
			Dialog dialog = null;
			MyDialog.Builder customBuilder = new
					MyDialog.Builder(Business_InfoActivity.this);
			customBuilder.setTitle("提示")
			.setMessage("确定要取消该收藏吗？")
			.setNegativeButton("取消", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton("确定", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					delMyCollectionCarinfo(userid,CommunityApp.getUser().getId());
				}
			});
			dialog = customBuilder.create();
			dialog.show();
		}
	}


	// 删除我的收藏
	public void delMyCollectionCarinfo(String shopperid, String memberid) {
		final String shopperId = shopperid;
		final String memberId = memberid;
		new Thread() {
			@Override
			public void run()// 小写
			{
				try {
					HttpPost httpPost = new HttpPost(getResources().getString(
							R.string.base_url)
							+ "member/delcollection.do");
					ArrayList<NameValuePair> nvs = new ArrayList<NameValuePair>();
					NameValuePair nameValuePair = new BasicNameValuePair(
							"memberid", memberId);
					nvs.add(nameValuePair);
					nameValuePair = new BasicNameValuePair("userid", shopperId);
					nvs.add(nameValuePair);

					httpPost.setEntity(new UrlEncodedFormEntity(nvs, "UTF-8"));
					String result = Httphelper.getValueFromNet(httpPost);

					if (result.indexOf("success") == 0) {
						handler.post(new Runnable() {

							@Override
							public void run() {

								Toast.makeText(getBaseContext(), "取消收藏成功", 0)
								.show();
								shangjia_xiangxi_shoucang_ll.setVisibility(View.INVISIBLE);
								//getShopinfo();
							}
						});
					} else if (result.indexOf("failed") == 0) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								// pb.setVisibility(View.INVISIBLE);
								Toast.makeText(getBaseContext(), "取消收藏失败", 0)
								.show();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					handler.post(new Runnable() {
						@Override
						public void run() {
							// pb.setVisibility(View.INVISIBLE);
							Toast.makeText(CommunityApp.context,
									"服务器去外星考察啦...", 0).show();
						}
					});

				}
			}
		}.start();

	}

	//获取收藏的商家信息
	public void getMyClooection(String shopid)
	{
		final String shopId=shopid;
		new Thread()
		{
			@Override
			public void run()
			{
				try {
					HttpPost httpPost = new HttpPost(getResources()
							.getString(R.string.base_url)
							+ "member/mem_selectsingleshopinfo.do");

					ArrayList<NameValuePair> nvs = new ArrayList<NameValuePair>();
					NameValuePair nameValuePair = new BasicNameValuePair(
							"shopid", shopId);
					nvs.add(nameValuePair);
					httpPost.setEntity(new UrlEncodedFormEntity(nvs,
							"utf-8"));
					final String result = Httphelper
							.getValueFromNet(httpPost);

					
					
					
					
					final JSONObject jsonObj = new JSONObject(result)
					.getJSONObject("mer");
					System.out.println("获取商家的收藏信息"+jsonObj);
					if (jsonObj!=null) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								try {
									tViewInfo.setText(jsonObj.getString("shopInfo"));
									tViewKnow.setText(jsonObj.getString("customerNotice"));
									tViewAddress.setText(jsonObj.getString("address"));
									//headName=(jsonObj.getString("shopLogo"));
									phone=jsonObj.getString("phone");
									//LoadImageThread(iViewHead,headName);
									//new LoadImageThread(iViewHead,headName).start();
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});

					}else {
						return;
					}

				} catch (Exception e) {
					e.printStackTrace();
					handler.post(new Runnable() {
						@Override
						public void run() {
							// pb.setVisibility(View.INVISIBLE);
							Toast.makeText(CommunityApp.context,
									"服务器去外星考察啦...", 0).show();
						}
					});
				}
			}
		}.start();
	}

	//获取商户详细信息
	public void getDetailShopInfo(String shopid)
	{
		final String shopId=shopid;

		MyProgressDialog.startProgressDialog(Business_InfoActivity.this, null);
		new Thread()
		{
			@Override
			public void run()
			{
				try {
					HttpPost httpPost = new HttpPost(getResources()
							.getString(R.string.base_url)
							+ "member/mem_selectsingleshopinfo.do");
					ArrayList<NameValuePair> nvs = new ArrayList<NameValuePair>();
					NameValuePair nameValuePair = new BasicNameValuePair(
							"shopid", shopId);
					nvs.add(nameValuePair);
					httpPost.setEntity(new UrlEncodedFormEntity(nvs,
							"utf-8"));
					final String result = Httphelper
							.getValueFromNet(httpPost);
					Merchants ms[] = null;
					ms=GsonUtils.gsonToMerchants(result);
					
					int len = ms.length;
					
					if (len==0) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								MyProgressDialog.stopProgressDialog();
								Toast.makeText(getApplicationContext(),
										"暂无数据", 0).show();
							}
						});
						return;
					}
					
					Merchants merchant=new Merchants();
					merchant=ms[0];
				
					if(merchant!=null)
					{
						ArrayList<ShopImg> listImgs = merchant.getImgUrl();
						final ArrayList<String> urls = new ArrayList<String>();
						for (ShopImg shopImg : listImgs) {
							urls.add(shopImg.getImgUrl());
						}
						
						System.out.println("商家详细图片-------url"+urls);
						
						final String RealName=merchant.getRealName();
						final String ShopInfo=merchant.getShopInfo();
						final String CustomerNotice=merchant.getCustomerNotice();
						final String Address=merchant.getAddress();
						final String Phonenum=merchant.getPhone();
						final String ShopLogo=merchant.getShopLogo();
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								try {
									MyProgressDialog.stopProgressDialog();
									tViewName.setText(RealName);
									tViewInfo.setText(ShopInfo);
									tViewKnow.setText(CustomerNotice);
									tViewAddress.setText(Address);
									phone=Phonenum;
									headName=ShopLogo;
									images=urls;
									new LoadImageThread(iViewHead,headName).start();
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}
						});
						
					}
					else {
						return;
					}

				} catch (Exception e) {
					e.printStackTrace();
					handler.post(new Runnable() {
						@Override
						public void run() {
							MyProgressDialog.stopProgressDialog();
							Toast.makeText(CommunityApp.context,
									"服务器去外星考察啦...", 0).show();
						}
					});
				}
			}
		}.start();
	}

	//异步图片加载
	@SuppressWarnings("unused")
	private class LoadImageThread extends Thread{
		private final ImageView iv;
		private final String imgName;
		public LoadImageThread(ImageView img,String name){
			this.iv=img;
			this.imgName=name;
		}
		@Override
		@SuppressLint("NewApi")
		public void run(){

			String imgUrl = null;
			//异步加载图片资源
			try {
				imgUrl=Business_InfoActivity.this.getResources().getString(R.string.base_url)+imgName.substring(1);
			} catch (Exception e) {
				imgUrl=Business_InfoActivity.this.getResources().getString(R.string.base_url)+"default.jpg";
			}

			//System.out.println("头像的Url------"+imgUrl);
			@SuppressWarnings("static-access")


			final Drawable drawable = NetUtil.asyncImageLoader.loadImageFromUrl(imgUrl);
			handler.post(new Runnable() {

				@Override
				public void run() {
					if(drawable!=null){
						iv.setImageDrawable(drawable);
					}
					else {
						iv.setImageResource(R.drawable.g11);
					}
				}
			}) ;
		}
	}

	// 返回图片按钮被点击
	public void onBackImageClick(View view) {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;

		} else {
			return super.onKeyDown(keyCode, event);
		}

	}
}
