package com.costular.marvelheroes.presentation.heroeslist

import android.app.VoiceInteractor
import android.arch.lifecycle.Observer
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.FavoritesManager
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.item_hero.view.*

/**
 * Marvel Heroes List Adapter
 */
typealias OnLikeUpdated = (MarvelHeroEntity, Boolean) -> Unit
typealias OnClick = (MarvelHeroEntity, ImageView) -> Unit

class HeroesListAdapter(val activity: AppCompatActivity, val favoritesManager: FavoritesManager, val onLikeUpdated: OnLikeUpdated, val clickListener: OnClick): RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>() {
    private lateinit var context: Context

    private var data: MutableList<MarvelHeroEntity> = mutableListOf()

    // Creates ViewHolder from 'item_hero' Layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        context = parent.context!!
        return HeroesViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_hero, parent, false)
        )
    }

    // Data Management
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) = holder.bind(data[position])

    // Swap Current Data with New Data
    fun swapData(data: List<MarvelHeroEntity>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    // Clear Current Data
    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    /** Marvel Hero Item View Stuff */
    inner class HeroesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        // Bind MarvelHeroEntity Data to ItemView
        fun bind(item: MarvelHeroEntity) = with(itemView) {
            kotlin.with(itemView) {

                // Set 'heroImage' with Entity's photoUrl:
                Glide.with(context)
                        .asBitmap()
                        .load(item.photoUrl)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                resource?.let { loadColorsFromBitmap(it) }
                                return false
                            }
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                        })
                        .into(heroImage)

                /* bind */
                showIsLiked(favoritesManager.isLiked(item))
                bindEvents(item)

                /* set */
                heroTitle.text = item.name
                setOnClickListener { clickListener(item, heroImage) }
            }
        }
        fun bindEvents(hero: MarvelHeroEntity) {

            /* set */
            val likeState = favoritesManager.likeState(hero)
            likeState.removeObservers(activity)
            likeState.observe(activity, Observer { isLiked ->
                isLiked?.let {
                    showIsLiked(it)
                }
            })

            /* set */
            val heroListLikeButton = itemView.findViewById(R.id.heroListItemLikeButton) as ImageButton
            heroListLikeButton.setOnClickListener {
                onLikeUpdated(hero, !favoritesManager.isLiked(hero))
            }
        }
        fun showIsLiked(isLiked: Boolean) {
            val heroListLikeButton = itemView.findViewById(R.id.heroListItemLikeButton) as ImageButton
            heroListLikeButton.setImageResource(if (isLiked) R.drawable.ic_heartitem_enabled else R.drawable.ic_heartitem)
        }
        fun loadColorsFromBitmap(bitmap: Bitmap) {
            with(itemView) {
                Palette.from(bitmap).generate { palette ->
                    val vibrant = palette.vibrantSwatch
                    vibrant?.let {
                        heroTitle.setBackgroundColor(vibrant.rgb)
                        heroTitle.setTextColor(vibrant.bodyTextColor)
                    }
                }
            }
        }
    }
}