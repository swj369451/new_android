package com.sm130.application130.u;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.sm130.application130.R;

public class MyBitmapUtils {

	private NetCacheUtils mNetCacheUtils;
	private LocalCacheUtils mLocalCacheUtils;
	private MemoryCacheUtils mMemoryCacheUtils;

	public MyBitmapUtils() {
		mMemoryCacheUtils = new MemoryCacheUtils();
		mLocalCacheUtils = new LocalCacheUtils();
		mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
	}

	public void display(ImageView imageView, String url) {
		// 设置默认图片
		imageView.setImageResource(R.drawable.pic_item_list_default);

		// 优先从内存中加载图片, 速度最快, 不浪费流量
		Bitmap bitmap = mMemoryCacheUtils.getMemoryCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从内存加载图片啦");
			return;
		}

		// 其次从本地(sdcard)加载图片, 速度快, 不浪费流量
		bitmap = mLocalCacheUtils.getLocalCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从本地加载图片啦");

			// 写内存缓存
			mMemoryCacheUtils.setMemoryCache(url, bitmap);
			return;
		}

		// 最后从网络下载图片, 速度慢, 浪费流量
		mNetCacheUtils.getBitmapFromNet(imageView, url);
	}

}
