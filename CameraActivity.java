package com.example.transporteescolar;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraActivity extends Activity

implements SurfaceHolder.Callback {


private Camera camera;

private SurfaceView surfaceView;



		@Override

			public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.camera);

   // camera.takePicture(null, null, jpeg);
    camera.takePicture(null, null, null);

    }
	

		@Override

		protected void onDestroy() {

    super.onDestroy();



    if (camera != null) {

        camera.release();

    }

}

			@Override

		protected void onPause() {

    super.onPause();



    if (camera != null) {

        camera.stopPreview();

    }

}



			@Override

		public void surfaceCreated(SurfaceHolder holder) {

    try {

        camera.setPreviewDisplay(holder);

        camera.startPreview();

    } catch (IOException e) {

        e.printStackTrace();

    }

}
			/*public void vai (){
				    camera = Camera.open();

				    surfaceView = (SurfaceView) findViewById(R.id.preview);

				    surfaceView.getHolder().addCallback(this);

				    PictureCallback jpeg = new PictureCallback(){
				    	@Override
				    	public void onPictureTaken(byte[] data, Camera camera){
				    		FileOutputStream fos = null;
				    		try{
				    			try{
				    				fos = new FileOutputStream(imageFile);
				    				fos.write(data);
				    			}finally{
				    				if (fos != null) {
				    					fos.close();
				    				}
				    			}
				    		}catch (IOException e){
				    			e.printStackTrace();
				    		}
				    	camera.startPreview()
				    	}
				}*/

			

	@Override

		public void surfaceChanged(SurfaceHolder holder, int format,

    int width, int height) {

    if (holder.getSurface() != null) {

        try {

            camera.stopPreview();

        } catch (Exception e) {

        }

   

        try {

            camera.setPreviewDisplay(holder);

            camera.startPreview();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

	//camera.takePicture(shutter, raw, jpeg);

	

	@Override

		public void surfaceDestroyed(SurfaceHolder holder) {

}

	
	
	
	
}


	    
	
	


