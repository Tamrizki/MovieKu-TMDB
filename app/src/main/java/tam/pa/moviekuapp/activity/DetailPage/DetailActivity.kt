package tam.pa.moviekuapp.activity.DetailPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.activity.DetailPage.adapter.GenreAdapter
import tam.pa.moviekuapp.activity.DetailPage.adapter.TopRatedAdapter
import tam.pa.moviekuapp.activity.moreMovie.MoreMovieActivity
import tam.pa.moviekuapp.model.GenreItem
import tam.pa.moviekuapp.model.MovieListItem
import tam.pa.moviekuapp.model.SendListItem
import tam.pa.moviekuapp.model.response.DetailMovieResponse
import tam.pa.moviekuapp.network.ApiClient

class DetailActivity : AppCompatActivity(), IViewDetailMovie, View.OnClickListener {
    lateinit var ivThumbnail: ImageView
    lateinit var appBar: Toolbar
    lateinit var ibBack: ImageButton
    lateinit var btnMoreTopRated: ImageButton
    lateinit var rlPlay: RelativeLayout
    lateinit var tvTitle: TextView
    lateinit var ivFlag: ImageView
    lateinit var rvGenre: RecyclerView
    lateinit var tvRate: TextView
    lateinit var tvVote: TextView
    lateinit var tvDesc: TextView
    lateinit var rvTopRated: RecyclerView
    lateinit var presenter: DetailPresenter
    lateinit var iViewDetailMovie: IViewDetailMovie
    var listTopRated: ArrayList<SendListItem> = ArrayList()
    val apiCLient = ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        var idMovie = intent.getIntExtra("idMovie", 0)
        iViewDetailMovie = this
        presenter = DetailPresenter(iViewDetailMovie)
        presenter.onGetDetailMovie(idMovie.toString())
        presenter.onGetTopRated()
        btnMoreTopRated.setOnClickListener(this)
        ibBack.setOnClickListener(this)
        rlPlay.setOnClickListener(this)
    }

    override fun onGetDetail(data: DetailMovieResponse) {
        Picasso.get().load(apiCLient.BASE_URL_IMAGE+data.backdrop_path).error(R.drawable.ic_error).fit().into(ivThumbnail)
        var idCountry:String = data.original_language.toString()
        if (data.original_language.toString().equals("en")){
            idCountry = "us"
        }
        Picasso.get().load(apiCLient.getUrlFlag(idCountry)).error(R.drawable.ic_error).fit().into(ivFlag)
        tvTitle.text = data.title
        tvRate.text = data.vote_average.toString()
        tvVote.text = data.vote_count.toString()+" Vote"
        tvDesc.text = data.overview
        SetGenre(data.genres)
    }

    fun SetGenre(genres: List<GenreItem>?) {
        var genreAdapter = GenreAdapter(this, genres)
        rvGenre.setHasFixedSize(true)
        rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvGenre.adapter = genreAdapter
    }

    override fun onGetTopRated(list: List<MovieListItem>) {
        list?.forEach {
            listTopRated.add(SendListItem(it.id, it.backdrop_path, it.title, it.video, it.original_language))
        }
        val topAdapter = TopRatedAdapter(this, list)
        rvTopRated.setHasFixedSize(true)
        rvTopRated.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTopRated.adapter = topAdapter
    }

    override fun onError(msg: String) {
        Log.d("errorrrrr", msg)
    }

    override fun init() {
        ivThumbnail = findViewById(R.id.ivThumbnail)
        appBar = findViewById(R.id.appBar)
        ibBack = findViewById(R.id.ibBack)
        rlPlay = findViewById(R.id.rlPlay)
        tvTitle = findViewById(R.id.tvTitle)
        ivFlag = findViewById(R.id.ivFlag)
        rvGenre = findViewById(R.id.rvGenre)
        tvRate = findViewById(R.id.tvRate)
        tvVote = findViewById(R.id.tvVote)
        tvDesc = findViewById(R.id.tvDesc)
        rvTopRated = findViewById(R.id.rvTopRated)
        btnMoreTopRated = findViewById(R.id.btnMoreTopRated)
    }

    override fun onClick(view: View?) {
        if (view == btnMoreTopRated){
            var intent = Intent(this, MoreMovieActivity::class.java)
            intent.putExtra("title", "Top Rated")
            intent.putParcelableArrayListExtra("list", listTopRated)
            startActivity(intent)
        }
        else if (view == ibBack){
            onBackPressed()
        }
        else if (view == rlPlay){
            Toast.makeText(this, "Link is not available!", Toast.LENGTH_LONG).show()
        }
    }
}