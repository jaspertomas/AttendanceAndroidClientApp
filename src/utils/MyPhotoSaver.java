package utils;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class MyPhotoSaver {
	public static String DEBUG_TAG="MyPhotoSaver";

  public static Boolean save(byte[] data, File pictureFileDir, Context context) {
//
//    //if can't get picture directory, do nothing
//    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
//
//      Log.d(DEBUG_TAG, "Can't create directory to save image.");
//      Toast.makeText(context, "Can't create directory to save image.",
//          Toast.LENGTH_LONG).show();
//      return false;
//
//    }

    //formulate filename with current date
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
//    String date = dateFormat.format(new Date());
//    String photoFile = "Picture_" + date + ".jpg";
//    String filename = pictureFileDir.getPath() + File.separator + photoFile;
    String photoFile = "temp.jpg";
    String filename = pictureFileDir.getPath() + File.separator + photoFile;
    File pictureFile = new File(filename);

    //save image to disk
    try {
      FileOutputStream fos = new FileOutputStream(pictureFile);
      fos.write(data);
      fos.close();
      return true;
    } catch (Exception error) {
      Log.d(DEBUG_TAG, "File" + filename + "not saved: "
          + error.getMessage());
      return false;
    }
  }

  public static File getDir(Context context) {
//	  File dir=new File(context.getFilesDir() + "/");
	  
//	    return Environment.getDataDirectory();
	  return new File(context.getFilesDir() + "/");
//    File sdDir = Environment
//      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//    return new File(sdDir, "CameraAPIDemo");
  }
} 