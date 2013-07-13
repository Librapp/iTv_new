package com.sumavision.itv.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.sumavision.itv.data.OtherCacheData;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

public class BitmapUtils
{

	public static byte[] bitmapToBytes(Bitmap bitmap)
	{

		if (bitmap == null)
		{
			return null;

		}
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		return os.toByteArray();

	}

	public static byte[] drawableToBytes(Drawable drawable)
	{

		if (drawable == null)
		{
			return null;

		}

		return bitmapToBytes(drawableToBitmap(drawable));

	}

	public static Bitmap rotateBitmap(Bitmap bitmapOrg, int angle)
	{

		// 获取这个图片的宽和高
		int width = bitmapOrg.getWidth();
		int height = bitmapOrg.getHeight();

		// //定义预转换成的图片的宽度和高度
		// int newWidth = 200;
		// int newHeight = 200;
		// //计算缩放率，新尺寸除原始尺寸
		// float scaleWidth = ((float) newWidth) / width;
		// float scaleHeight = ((float) newHeight) / height;
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		//
		// // 缩放图片动作
		// matrix.postScale(scaleWidth, scaleHeight);

		// 旋转图片 动作
		matrix.postRotate(angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, true);
		return resizedBitmap;
	}

	public static Bitmap drawableToBitmap(Drawable drawable)
	{

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static Bitmap resizeBitmap(Bitmap bitmap, int maxSize)
	{

		int srcWidth = bitmap.getWidth();
		int srcHeight = bitmap.getHeight();
		int width = maxSize;
		int height = maxSize;
		boolean needsResize = false;
		if (srcWidth > srcHeight)
		{
			if (srcWidth > maxSize)
			{
				needsResize = true;
				height = ((maxSize * srcHeight) / srcWidth);
			}
		}
		else
		{
			if (srcHeight > maxSize)
			{
				needsResize = true;
				width = ((maxSize * srcWidth) / srcHeight);
			}
		}
		if (needsResize)
		{
			Bitmap retVal = Bitmap.createScaledBitmap(bitmap, width, height, true);
			return retVal;
		}
		else
		{
			return bitmap;
		}
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
	{

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static boolean isFileExist(String folder, String path)
	{

		String folderPath = Environment.getExternalStorageDirectory() + File.separator + folder + File.separator;

		File file = new File(folderPath, path);
		if (file.exists())
		{
			return true;
		}
		return false;
	}

	public static void saveDrawable(Drawable drawable, String fileDir, String name)
	{

		// String folderPath = Environment.getExternalStorageDirectory()
		// + File.separator + fileDir;
		byte[] buffer = drawableToBytes(drawable);
		FileInfoUtils.writeFile(buffer, fileDir, name + ".jpg");
	}

	public static String decodeSampledBitmapFromFile(final String pathName)
	{

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);

		// 图片宽度
//		int oldPicWidth = options.outWidth;
		int oldPicHeight = options.outHeight;
//		final double scale = CommonUtils.round(oldPicWidth / oldPicHeight, 2);

		long size = FileInfoUtils.getFileSize(pathName);
		Log.e("RenderPicture-size", size + "");

		int ratio = (int) (oldPicHeight / (float) 200);
		if (ratio <= 0)
			ratio = 1;

		options.inSampleSize = ratio;

		options.inJustDecodeBounds = false;

		Bitmap b = BitmapFactory.decodeFile(pathName, options);
		Bitmap scaledBitmap = resizeBitmap(b, 300);
		b.recycle();
		b = null;
		System.gc();


		OtherCacheData.current().uploadPic = scaledBitmap;
		BitmapUtils.saveDrawable(new BitmapDrawable(scaledBitmap), "iTV+/videoUploaded", "uploaded");
		scaledBitmap.recycle();
		System.gc();

		return Environment.getExternalStorageDirectory() + File.separator + "iTV+/videoUploaded" + File.separator + "uploaded.jpg";
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth)
		{
			if (width > height)
			{
				inSampleSize = Math.round((float) height / (float) reqHeight);
			}
			else
			{
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

}
