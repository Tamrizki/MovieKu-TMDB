package tam.pa.moviekuapp.activity.HomePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.activity.HomePage.adapter.PopularAdapter
import tam.pa.moviekuapp.activity.HomePage.adapter.UpcomingAdapter
import tam.pa.moviekuapp.activity.HomePage.adapter.ViewPagerAdapter
import tam.pa.moviekuapp.activity.moreMovie.MoreMovieActivity
import tam.pa.moviekuapp.dialog.search.SearchingMovie
import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.model.SendListItem

class HomeActivity : AppCompatActivity(), IViewHome, View.OnClickListener {
    lateinit var iview: IViewHome
    lateinit var presenter: HomePresenter
    lateinit var pagerAdapter: ViewPagerAdapter
    lateinit var vpMovie: ViewPager
    lateinit var tlMovie: TabLayout
    lateinit var rvUpcoming: RecyclerView
    lateinit var rvPopular: RecyclerView
    lateinit var etSearch: EditText
    lateinit var btnSearch: ImageButton
    lateinit var btnMoreTopRated: ImageButton
    lateinit var btnMoreUpcoming: ImageButton
    lateinit var btnLoadMore: Button
    lateinit var upcomingAdapter: UpcomingAdapter
    lateinit var popularAdapter: PopularAdapter
    lateinit var dialog: SearchingMovie
    var page: Int = 1
    var listTopRated: ArrayList<SendListItem> = ArrayList()
    var listUpcoming: ArrayList<SendListItem> = ArrayList()
    var listSearch: ArrayList<SendListItem> = ArrayList()
    var listMovie: ArrayList<MovieListItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        iview = this
        presenter = HomePresenter(iview, this)
        presenter.onGetListMovie("1")
        presenter.onGetTopRated()
        presenter.onGetUpcoming()
        btnMoreTopRated.setOnClickListener(this)
        btnMoreUpcoming.setOnClickListener(this)
        btnSearch.setOnClickListener(this)
        btnLoadMore.setOnClickListener(this)
    }

    override fun onGetListMovie(list: List<MovieListItem>?) {
        list?.let { listMovie.addAll(it) }
        popularAdapter = PopularAdapter(this, listMovie)
        rvPopular.setHasFixedSize(true)
        rvPopular.layoutManager = LinearLayoutManager(this)
        rvPopular.adapter = popularAdapter
    }

    override fun onGetTopRated(list: List<MovieListItem>?) {
        list?.forEach {
            listTopRated.add(SendListItem(it.id, it.backdrop_path, it.title, it.video, it.original_language))
        }
        pagerAdapter = ViewPagerAdapter(this, list)
        vpMovie.adapter = pagerAdapter
        tlMovie.setupWithViewPager(vpMovie)
        tlMovie.setSelectedTabIndicator(0)
    }

    override fun onGetUpcoming(list: List<MovieListItem>?) {
        list?.forEach {
            listUpcoming.add(SendListItem(it.id, it.backdrop_path, it.title, it.video, it.original_language))
        }
        upcomingAdapter = UpcomingAdapter(this, list)
        rvUpcoming.setHasFixedSize(true)
        rvUpcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvUpcoming.adapter = upcomingAdapter
    }

    override fun onGetSearch(list: List<MovieListItem>?) {
        list?.forEach {
            listSearch.add(SendListItem(it.id, it.backdrop_path, it.title, it.video, it.original_language))
        }
        dialog = SearchingMovie(this, listSearch, etSearch.text.toString())
        dialog.show()
    }

    override fun onError(msg: String) {
        Log.d("errorMsg", msg)
    }

    override fun init() {
        vpMovie = findViewById(R.id.vpMovie)
        tlMovie = findViewById(R.id.tlMovie)
        rvUpcoming = findViewById(R.id.rvUpcoming)
        rvPopular = findViewById(R.id.rvPopular)
        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        btnMoreTopRated = findViewById(R.id.btnMoreTopRated)
        btnMoreUpcoming = findViewById(R.id.btnMoreUpcoming)
        btnLoadMore = findViewById(R.id.btnLoadMore)
    }

    override fun onClick(view: View?) {
        if (view == btnMoreTopRated){
            var intent = Intent(this, MoreMovieActivity::class.java)
            intent.putExtra("title", "Top Rated")
            intent.putParcelableArrayListExtra("list", listTopRated)
            startActivity(intent)
        }else if (view == btnMoreUpcoming){
            var intent = Intent(this, MoreMovieActivity::class.java)
            intent.putExtra("title", "Upcoming")
            intent.putParcelableArrayListExtra("list", listUpcoming)
            startActivity(intent)
        }else if(view == btnSearch){
            if (!etSearch.text.toString().isNullOrBlank()){
                listSearch.clear()
                presenter.onSearchMovie(etSearch.text.toString())
            }else{
                etSearch.error = "form must be filled!"
                etSearch.requestFocus()
            }
        }else if (view == btnLoadMore){
            page++
            presenter.onGetListMovie(page.toString())
        }
    }
}