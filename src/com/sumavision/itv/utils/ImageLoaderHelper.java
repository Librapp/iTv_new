package com.sumavision.itv.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImageLoaderHelper
{

	private ImageLoader imageLoader = ImageLoader.getInstance();

	public void loadImage(ImageView imageView, String url, int defalutPic)
	{

		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(defalutPic).showImageForEmptyUri(defalutPic).showImageOnFail(defalutPic).cacheInMemory().cacheOnDisc().build();
		imageLoader.displayImage(url, imageView, options, animateFirstListener);
	}

	private static final int animationDuration = 600;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener
	{

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
		{

			if (loadedImage != null)
			{
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				imageView.setImageBitmap(loadedImage);
				if (firstDisplay)
				{
					FadeInBitmapDisplayer.animate(imageView, animationDuration);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	public static void initImageLoader(Context context)
	{

		int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);

		MemoryCacheAware<String, Bitmap> memoryCache;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT)
		{
			memoryCache = new LruMemoryCache(memoryCacheSize);
		}
		else
		{
			memoryCache = new LRULimitedMemoryCache(memoryCacheSize);
		}
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).memoryCache(memoryCache).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging().build();
		ImageLoader.getInstance().init(config);
	}
}
