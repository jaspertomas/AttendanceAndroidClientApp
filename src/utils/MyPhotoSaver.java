package utils;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class MyPhotoSaver {
	public static String DEBUG_TAG = "MyPhotoSaver";
	private static String dateTimeString = "";
	private static String pictureFileShortName = "";
	private static String pictureFileFullName = "";
	private static File pictureFile = null;

	public static void reset() {
		dateTimeString="";
		pictureFileShortName = "";
		pictureFileFullName = "";
		pictureFile = null;
	}

	public static String getPictureFileShortName() {
		return pictureFileShortName;
	}

	public static String getPictureFileFullName() {
		return pictureFileFullName;
	}

	public static File getPictureFile() {
		return pictureFile;
	}

	// public static String getDateTimeString() {
	// return dateTimeString;
	// }
	public static String getDateTimeStringToFilename() {
		return dateTimeString.replace(" ", "-").replace(":", "-") + ".jpg";
	}

	// public static void setDateTimeString(String dateTimeString) {
	// MyPhotoSaver.dateTimeString = dateTimeString;
	// }

	public static Boolean save(byte[] data, File pictureFileDir,
			String _filename, Context context, String datetimestring) {
		//
		// //if can't get picture directory, do nothing
		// if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
		//
		// Log.d(DEBUG_TAG, "Can't create directory to save image.");
		// Toast.makeText(context, "Can't create directory to save image.",
		// Toast.LENGTH_LONG).show();
		// return false;
		//
		// }

		// formulate filename with current date
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		// String date = dateFormat.format(new Date());
		// String photoFile = "Picture_" + date + ".jpg";
		// String filename = pictureFileDir.getPath() + File.separator +
		// photoFile;
		// String photoFile = PictureDataHolder.getFilename();
		pictureFileShortName = _filename;
		pictureFileFullName = pictureFileDir.getPath() + File.separator
				+ _filename;
		pictureFile = new File(pictureFileFullName);
		MyPhotoSaver.dateTimeString = datetimestring;

		// save image to disk
		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
			return true;
		} catch (Exception error) {
			Log.d(DEBUG_TAG,
					"File" + _filename + "not saved: " + error.getMessage());
			return false;
		}
	}

	public static File getDir(Context context) {
		// File dir=new File(context.getFilesDir() + "/");

		// this gives
		// /mnt/sdcard/Android/data/com.itforhumanity.attendance/files/camera
		// return context.getExternalFilesDir(context.CAMERA_SERVICE);

		// this gives /data
		// return Environment.getDataDirectory();

		// this gives something like /data/data/com.itforhumanity.attendance/
		return new File(context.getFilesDir() + "/");

		// this gives /mnt/sdcard/Pictures/CameraAPIDemo
		// File sdDir = Environment
		// .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		// return new File(sdDir, "CameraAPIDemo");
	}
	
	public static Bitmap scaleBitmap(Bitmap bm) {
		int maxHeight=1080;
		int maxWidth=1080;
	    int width = bm.getWidth();
	    int height = bm.getHeight();

	    Log.v("Pictures", "Width and height are " + width + "--" + height);

	    //if picture is smaller than max dimensions, do nothing
	    if(width<maxWidth && height<maxHeight){
	    	return bm;
	    } else if (width > height) {
	        // landscape
	        float ratio = (float) width / maxWidth;
	        width = maxWidth;
	        height = (int)(height / ratio);
	    } else if (height > width) {
	        // portrait
	        float ratio = (float) height / maxHeight;
	        height = maxHeight;
	        width = (int)(width / ratio);
	    } else {
	        // square
	        height = maxHeight;
	        width = maxWidth;
	    }
//	    Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);

	    bm = Bitmap.createScaledBitmap(bm, width, height, true);
	    return bm;
	}	
}