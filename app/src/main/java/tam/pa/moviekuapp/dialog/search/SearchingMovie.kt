package tam.pa.moviekuapp.dialog.search

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.dialog.search.adapter.SearchAdapter
import tam.pa.moviekuapp.model.SendListItem

class SearchingMovie(val ctx: Context, val list: ArrayList<SendListItem>, val caption: String)
    : BottomSheetDialog(ctx, R.style.BottomSheetDialogTheme) {
    lateinit var adapter: SearchAdapter
    lateinit var tvSearch: TextView
    lateinit var rvList: RecyclerView
    lateinit var tvNotFound: TextView
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.custom_search_dialog)
        init()
        list.size
        tvSearch.text = caption
        if (list.size <= 0){
            tvNotFound.visibility = View.VISIBLE
        }else{
            adapter = SearchAdapter(list)
            rvList.setHasFixedSize(true)
            rvList.layoutManager = LinearLayoutManager(ctx)
            rvList.adapter = adapter
        }
    }
    fun init(){
        tvSearch = findViewById(R.id.tvSearch)!!
        rvList = findViewById(R.id.rvList)!!
        tvNotFound = findViewById(R.id.tvNotFound)!!
    }
}
