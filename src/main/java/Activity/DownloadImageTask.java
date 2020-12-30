package Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * to set image from url ot imageview
 * new DownloadImageTask(imageView).execute(photoUrl);
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        private Context context;
        private ProgressDialog dialog;
public DownloadImageTask(ImageView bmImage/*,Context ctx*/) {
      //  context = ctx;
      //  dialog = new ProgressDialog(ctx);
        this.bmImage = bmImage;
        }
    /*    protected void onPreExecute() {
                super.onPreExecute();
                dialog.setMessage("Loading...");
                dialog.show();

        }*/
protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
        InputStream in = new java.net.URL(urldisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
        Log.e("Error", e.getMessage());
        e.printStackTrace();
        }
        return mIcon11;
        }

protected void onPostExecute(Bitmap result) {

       /* if(dialog != null && dialog.isShowing())
                dialog.dismiss();*/

        bmImage.setImageBitmap(result);
        }


}