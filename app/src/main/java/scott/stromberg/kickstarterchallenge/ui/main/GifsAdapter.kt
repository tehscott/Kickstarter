package scott.stromberg.kickstarterchallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import scott.stromberg.kickstarterchallenge.R
import scott.stromberg.kickstarterchallenge.databinding.GifListItemBinding
import scott.stromberg.kickstarterchallenge.model.Gif

class GifsAdapter(val onGifClicked: (find: Gif) -> Unit) : RecyclerView.Adapter<GifsAdapter.GifViewHolder>() {
    var items = listOf<Gif>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(GifListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onGifClicked(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class GifViewHolder(val viewBinding: GifListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        private var gif: Gif? = null

        fun bind(gif: Gif) {
            this.gif = gif

            Glide.with(viewBinding.root)
                .load(gif.url)
                .placeholder(R.drawable.ic_image)
                .into(viewBinding.imageView)
        }
    }
}