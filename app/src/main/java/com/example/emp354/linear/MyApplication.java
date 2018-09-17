package com.example.emp354.linear;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context)
    {

/*

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(false) // default
                .cacheOnDisk(false) // default
                */
/*.preProcessor(...)
		        .postProcessor(...)
		        .extraForDownloader(...)*//*

		        .considerExifParams(false) // default
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
            .bitmapConfig(Bitmap.Config.ARGB_8888) // default
            */
/*.decodingOptions(...)*//*

		    .displayer(new SimpleBitmapDisplayer()) // default
            .handler(new Handler()) // default
            .build();


            File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                */
/*.taskExecutor(...)
		.taskExecutorForCachedImages(...)*//*

		.threadPoolSize(3) // default
            .threadPriority(Thread.NORM_PRIORITY - 2) // default
            .tasksProcessingOrder(QueueProcessingType.FIFO) // default
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .memoryCacheSizePercentage(13) // default
            .diskCache(new UnlimitedDiskCache(cacheDir)) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
            .imageDownloader(new BaseImageDownloader(context)) // default
            */
/*.imageDecoder(new BaseImageDecoder())*//*
 // default
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
            .writeDebugLogs()
            .build();


*/



        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher_round)
                .showImageOnFail(R.mipmap.ic_launcher)
                .resetViewBeforeLoading(true) // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.EXACTLY) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();


// This configuration tuning is custom. You can tune every option, you may tune some of them,
// or you can create default configuration by
// ImageLoaderConfiguration.createDefault(this);
// method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.defaultDisplayImageOptions(options);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB



// Apply configuration
        ImageLoader.getInstance().init(config.build());


    }
}
