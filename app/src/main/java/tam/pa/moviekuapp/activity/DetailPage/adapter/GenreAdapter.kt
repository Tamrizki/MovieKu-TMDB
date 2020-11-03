package tam.pa.moviekuapp.activity.DetailPage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tam.pa.moviekuapp.R
import tam.pa.moviekuapp.model.GenreItem

class GenreAdapter(val ctx: Context, val list: List<GenreItem>?): RecyclerView.Adapter<GenreAdapter.viewHolder>() {
    class viewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvGenre: TextView = view.findViewById(R.id.tvGenre)
        fun bind(get: GenreItem) {
            tvGenre.text = get.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_list_genre, parent, false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(list!!.get(position))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}