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
	ArrayList<String> images = null;// �����ݴ��̼�������Ƭ��ֻ�е�����鿴ʱ�Ż�������
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
		iViewHead = (ImageView) findViewById(R.id.shangjia_xiangxi_imageview);// ͷ��
		tViewName = (TextView) findViewById(R.id.shangjia_xiangxi_textview);// �̼�����
		tViewInfo = (TextView) findViewById(R.id.shangjia_xiangxi_shangpin_info);// �̼���Ʒ��Ϣ
		tViewKnow = (TextView) findViewById(R.id.shangjia_xiangxi_cus_know);// �˿���֪
		tViewAddress = (TextView) findViewById(R.id.shangjia_xiangxi_address);// ��ַ
		shangjia_xiangxi_shoucang_ll=(LinearLayout)findViewById(R.id.shangjia_xiangxi_shoucang_ll);
		Bundle bundle = this.getIntent().getExtras();// ���մ�����������
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

	// ����̼���Ϣ
	private void setInfo(Bundle bundle) {
		iViewHead.setImageResource(R.drawable.g11);// ������Ĭ��ͼƬ�����ڻ�ȡ���ٸ���;
		tViewName.setText(bundle.getString("name"));// �����̼�����
		tViewInfo.setText(bundle.getString("info"));// �̼���Ϣ
		tViewKnow.setText(bundle.getString("cusKnow"));
		tViewAddress.setText(bundle.getString("address"));

		// ��ȡ�̼�������Ƭ������
		images = bundle.getStringArrayList("images");

		if (bundle.getString("image") != null) {
			CommunityApp.object = Business_InfoActivity.class;
			new ImageDownloaderTask<Business_InfoActivity>(
					Business_InfoActivity.this).execute(new String[] { bundle
							.getString("image") });// ȥSD����������
		}
	}

	// ��SDCard�������ȡͼƬ��Ļص�������֪ͨlistview����
	public void onComplete(String url, Bitmap bitmap) {
		iViewHead.setImageBitmap(bitmap);
	}

	// ��ȡShangjia_ListActivity���ݵ���Ʒid
	public void getIntentId() {
		Intent intent = getIntent();
		shangpin_position = intent.getStringExtra("shangpin_position");
		if (shangpin_position != null)
			shangpin_position_int = Integer.parseInt(shangpin_position);
		class_position = intent.getStringExtra("class_position");
		if (class_position != null)
			class_position_int = Integer.parseInt(class_position);
	}

	// ��ת��ͼ���������
	public void shangjia_xiangxi_tuwen(View view) {
		//new MyToast(getApplicationContext(), "ͼ������", "��δ����", 200);
		if (images.size()==0) {
			Toast.makeText(Business_InfoActivity.this, "�̼���δ�ṩ����ͼƬ", 0).show();
			return;
		}
		Intent intent = new Intent(this, Business_ImagesWitcherActivity.class);
		intent.putStringArrayListExtra("images", images);
		startActivity(intent);
	}

	// �����̼ҵ绰
	public void shangjia_xiangxi_cell(View view) {
		if (phone.length() != 0) {
			Dialog dialog = null;
			MyDialog.Builder customBuilder = new
					MyDialog.Builder(Business_InfoActivity.this);
			customBuilder.setTitle("����")
			.setMessage("�Ƿ񲦴��̼ҵ绰��")
			.setNegativeButton("ȡ��", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton("ȷ��", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					if (phone.length() != 0) {
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:" + phone));
						startActivity(callIntent);
					} else {
						Toast.makeText(getBaseContext(), "����绰��Ч", 0).show();
					}
				}
			});
			dialog = customBuilder.create();
			dialog.show();
		} else {
			Toast.makeText(getBaseContext(), "����绰��Ч", 0).show();
		}
	}

	// �ղ�ͼƬ���ʱ���¼�,�ղػ�ȡ���ղ�
	@SuppressLint("ShowToast")
	public void shoucang(View v) {
		if (FLAG.equals("from_business_all_user")) {//��������,�ղ�

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
										Toast.makeText(getBaseContext(), "�ղسɹ�", 0)
										.show();
										shoucang.setBackgroundResource(R.drawable.shangjia_shoucang2);
										//									Business_InfoActivity.this.finish();
									}
								});
							} else if (result.indexOf("failed") == 0) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										Toast.makeText(getBaseContext(), "�ղ�ʧ��", 0)
										.show();
									}
								});
							} else if (result.indexOf("already") == 0) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										// pb.setVisibility(View.INVISIBLE);
										Toast.makeText(getBaseContext(), "�Ѿ��ղع�", 0)
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
											"������ȥ���ǿ�����...", 0).show();
								}
							});
						}
					}
				}.start();
			} else {
				Intent intent = new Intent(Business_InfoActivity.this,
						Personal_LoginActivity.class);
				startActivity(intent);
				new MyToast(getApplicationContext(), "��ܰ����", "���ȵ�¼", Toast.LENGTH_SHORT);
			}

		}else if (FLAG.equals("from_personal_mycollection")){//���ղؽ����ģ�ȡ���ղ�
			//			Toast.makeText(getApplicationContext(), "ȡ���ղ�", 0).show();
			Dialog dialog = null;
			MyDialog.Builder customBuilder = new
					MyDialog.Builder(Business_InfoActivity.this);
			customBuilder.setTitle("��ʾ")
			.setMessage("ȷ��Ҫȡ�����ղ���")
			.setNegativeButton("ȡ��", 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton("ȷ��", 
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


	// ɾ���ҵ��ղ�
	public void delMyCollectionCarinfo(String shopperid, String memberid) {
		final String shopperId = shopperid;
		final String memberId = memberid;
		new Thread() {
			@Override
			public void run()// Сд
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

								Toast.makeText(getBaseContext(), "ȡ���ղسɹ�", 0)
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
								Toast.makeText(getBaseContext(), "ȡ���ղ�ʧ��", 0)
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
									"������ȥ���ǿ�����...", 0).show();
						}
					});

				}
			}
		}.start();

	}

	//��ȡ�ղص��̼���Ϣ
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
					System.out.println("��ȡ�̼ҵ��ղ���Ϣ"+jsonObj);
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
									"������ȥ���ǿ�����...", 0).show();
						}
					});
				}
			}
		}.start();
	}

	//��ȡ�̻���ϸ��Ϣ
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
										"��������", 0).show();
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
						
						System.out.println("�̼���ϸͼƬ-------url"+urls);
						
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
									"������ȥ���ǿ�����...", 0).show();
						}
					});
				}
			}
		}.start();
	}

	//�첽ͼƬ����
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
			//�첽����ͼƬ��Դ
			try {
				imgUrl=Business_InfoActivity.this.getResources().getString(R.string.base_url)+imgName.substring(1);
			} catch (Exception e) {
				imgUrl=Business_InfoActivity.this.getResources().getString(R.string.base_url)+"default.jpg";
			}

			//System.out.println("ͷ���Url------"+imgUrl);
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

	// ����ͼƬ��ť�����
	public void onBackImageClick(View view) {
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ���¼����Ϸ��ذ�ť
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;

		} else {
			return super.onKeyDown(keyCode, event);
		}

	}
}
