package tam.pa.moviekuapp.helper

import android.app.ProgressDialog
import android.content.Context

class LoadingHelper(ctx: Context) {
    val progresDialog = ProgressDialog(ctx)
    fun startLoading(){
        progresDialog.setMessage("Please wait...")
        progresDialog.isIndeterminate = true
        progresDialog.setCancelable(false)
        progresDialog.show()
    }
    fun stopDialog(){
        progresDialog.dismiss()
    }
}