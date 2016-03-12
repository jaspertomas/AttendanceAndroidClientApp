package utils;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

   private SurfaceHolder holdMe;
   private Camera theCamera;

   public ShowCamera(Context context,Camera camera) {
      super(context);
      theCamera = camera;
      holdMe = getHolder();
      holdMe.addCallback(this);
   }

   @Override
   public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	   Log.i("ShowCamera","surfaceChanged()");
   }

   @Override
   public void surfaceCreated(SurfaceHolder holder) {
	   Log.i("ShowCamera","surfaceCreated()");
      try   {
         theCamera.setPreviewDisplay(holder);
         theCamera.startPreview(); 
      } catch (NullPointerException e) {
   	   Log.i("ShowCamera","surfaceCreated():nullpointerexception");
      } catch (IOException e) {
      	   Log.i("ShowCamera","surfaceCreated():ioexception");
      } catch (Exception e) {
     	   Log.i("ShowCamera","surfaceCreated():exception");
      }
   }

   @Override
   public void surfaceDestroyed(SurfaceHolder arg0) {
	   Log.i("ShowCamera","surfaceDestroyed()");
   }

}
