package math.sevakkalpesh.com.boilerplate_mvp.util.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kalpesh on 08/06/2016.
 */
public class Dialogs{


    private Dialogs() {
        //Empty
    }
        public static ProgressDialog pDialog;


    /**
     * Showing Dialog
     * */
   public static void showDialog(final Context context, String msg){
        // we set this to 0
                pDialog = new ProgressDialog(context);
                pDialog.setMessage(msg);
                pDialog.setIndeterminate(false);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setCancelable(false);
                pDialog.show();

        }

    /**
     * Dismiss Dialog
     * */
    public static void dismissDialog()
    {
        if(pDialog!=null){
            pDialog.dismiss();
        }
    }
}
