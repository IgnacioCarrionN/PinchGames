package dev.carrion.pinchgames.ui.mainlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.carrion.pinchgames.R
import dev.carrion.pinchgames.domain.DomainEntity

class GameListAdapter(private val handler: OnAdapterInteractions) : PagedListAdapter<DomainEntity.GameShort, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = GameViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as GameViewHolder).bind(getItem(position), handler)


    override fun getItemId(position: Int): Long {
        currentList?.let {
            return it[position]?.id ?: 0L
        }
        return 0L
    }



    companion object {
        /**
         * Handles the DiffUtil callback to compare list on the RecyclerView and apply the changes.
         */
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<DomainEntity.GameShort>() {
            override fun areItemsTheSame(oldItem: DomainEntity.GameShort, newItem: DomainEntity.GameShort): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DomainEntity.GameShort, newItem: DomainEntity.GameShort): Boolean =
                oldItem == newItem
        }
    }

    class GameViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val cover: ImageView = view.findViewById(R.id.imgCoverDetails)

        private val name: TextView = view.findViewById(R.id.txtName)




        fun bind(game: DomainEntity.GameShort?, handler: OnAdapterInteractions){
            if(game == null){
                val resources = itemView.resources
                name.text = resources.getString(R.string.loading)
            }else{
                name.text = game.name
                view.setOnClickListener {
                    handler.onItemClicked(game.id)
                }
                if(game.cover != null){
                    Glide.with(view).load(game.cover).into(cover)
                } else {
                    Glide.with(view).load(R.drawable.ic_no_cover).into(cover)
                }
            }
        }



        companion object {
            /**
             * Create a new ViewHolder.
             * @property parent ViewGroup parent of this ViewHolder.
             * @property handler OnAdapterInteractions implementation to handle item clicks.
             * @return GameViewHolder instance.
             */
            fun create(parent: ViewGroup): GameViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_game, parent, false)
                return GameViewHolder(view)
            }
        }
    }
    /**
     * Interface to communicate with the parent fragment on item clicks.
     */
    interface OnAdapterInteractions {
        fun onItemClicked(id: Long)
    }
}

