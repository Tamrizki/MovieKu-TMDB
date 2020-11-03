package tam.pa.moviekuapp.activity.moreMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.activity.moreMovie.adapter.MoreListAdapter
import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.model.SendListItem

class MoreMovieActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var ibBack: ImageButton
    lateinit var tvTitlePage: TextView
    lateinit var rvList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_movie)
        val title = intent.getStringExtra("title")
        val list = intent.getParcelableArrayListExtra<SendListItem>("list")
        init()
        tvTitlePage.text = title
        val adapter = MoreListAdapter(this, list!!)
        rvList.setHasFixedSize(true)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter
        ibBack.setOnClickListener(this)
    }

    private fun init() {
        ibBack = findViewById(R.id.ibBack)
        tvTitlePage = findViewById(R.id.tvTitlePage)
        rvList = findViewById(R.id.rvList)
    }

    override fun onClick(view: View?) {
        if (view == ibBack){
            onBackPressed()
        }
    }
}