package utils;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyBitmapHelper {
	public static Bitmap drawTextToBitmap(Context gContext, 
			  Bitmap bitmap, 
			  String gText) {
			  Resources resources = gContext.getResources();
			  float scale = resources.getDisplayMetrics().density;
			 
			  android.graphics.Bitmap.Config bitmapConfig =
			      bitmap.getConfig();
			  // set default bitmap config if none
			  if(bitmapConfig == null) {
			    bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
			  }
			  // resource bitmaps are imutable, 
			  // so we need to convert it to mutable one
			  bitmap = bitmap.copy(bitmapConfig, true);
			 
			  Canvas canvas = new Canvas(bitmap);
			  // new antialised Paint
			  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			  // text color - #3D3D3D
			  paint.setColor(Color.WHITE);
			  // text size in pixels
			  paint.setTextSize((int) (30 * scale));
			  // text shadow
			  paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
			 
			  // draw text to the Canvas center
			  Rect bounds = new Rect();
			  paint.getTextBounds(gText, 0, gText.length(), bounds);
			  int x = Double.valueOf((bitmap.getWidth() - bounds.width())*0.8).intValue();
			  int y = Double.valueOf((bitmap.getHeight() + bounds.height())*0.9).intValue();
			 
			  canvas.drawText(gText, x, y, paint);
			 
			  return bitmap;
			}
	public static byte[] getByteArrayFromBitmap(Bitmap bmp)
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 90, stream);
		byte[] byteArray = stream.toByteArray();		  
		return byteArray;
	}
}
