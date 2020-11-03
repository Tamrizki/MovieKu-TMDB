package tam.pa.moviekuapp.dialog.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.model.SendListItem
import tam.pa.moviekuapp.network.ApiClient

class SearchAdapter(val list: ArrayList<SendListItem>?): RecyclerView.Adapter<SearchAdapter.viewHolder>() {
    class viewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val apiCLient = ApiClient

        fun bind(get: SendListItem?) {
            tvTitle.text = get?.title
            Picasso.get().load(apiCLient.BASE_URL_IMAGE+get?.backdrop_path).placeholder(R.drawable.ic_direct_download).error(R.drawable.ic_error).fit().into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_big, parent, false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}