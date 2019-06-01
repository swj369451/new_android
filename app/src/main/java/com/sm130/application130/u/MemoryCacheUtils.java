package com.sm130.application130.u;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCacheUtils {

	// private HashMap<String, Bitmap> mMemoryCache = new HashMap<String,
	// Bitmap>();
	// private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new
	// HashMap<String, SoftReference<Bitmap>>();

	private LruCache<String, Bitmap> mMemoryCache;

	public MemoryCacheUtils() {
		// LruCache 可以将最近最少使用的对象回收掉, 从而保证内存不会超出范围
		// Lru: least recentlly used 最近最少使用算法
		long maxMemory = Runtime.getRuntime().maxMemory();// 获取分配给app的内存大小
		System.out.println("maxMemory:" + maxMemory);

		mMemoryCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {

			// 返回每个对象的大小
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// int byteCount = value.getByteCount();
				int byteCount = value.getRowBytes() * value.getHeight();// 计算图片大小:每行字节数*高度
				return byteCount;
			}
		};
	}

	/**
	 * 写缓存
	 */
	public void setMemoryCache(String url, Bitmap bitmap) {
		// mMemoryCache.put(url, bitmap);
		// SoftReference<Bitmap> soft = new SoftReference<Bitmap>(bitmap);//
		// 使用软引用将bitmap包装起来
		// mMemoryCache.put(url, soft);
		mMemoryCache.put(url, bitmap);
	}

	/**
	 * 读缓存
	 */
	public Bitmap getMemoryCache(String url) {
		// SoftReference<Bitmap> softReference = mMemoryCache.get(url);
		//
		// if (softReference != null) {
		// Bitmap bitmap = softReference.get();
		// return bitmap;
		// }

		return mMemoryCache.get(url);
	}
}
