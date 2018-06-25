package com.costular.marvelheroes.presentation.herodetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.activity_hero_detail.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Marvel Hero Detail
 */
class HeroDetailActivity : AppCompatActivity() {
    companion object {
        const val PARAM_HEROE = "heroe"
    }

    lateinit var heroDetailViewModel: HeroDetailViewModel

    // On Create
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)

        /* set */
        setContentView(R.layout.activity_hero_detail)

        /* set */
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        /* setup */
        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        setUpViewModel(hero)

        /* fill */
        hero?.let { fillHeroData(it) }
    }

    // Setup ViewModel
    private fun setUpViewModel(hero: MarvelHeroEntity?) {
        heroDetailViewModel = ViewModelProviders.of(this).get(HeroDetailViewModel::class.java)
        bindEvents(hero)
        heroDetailViewModel.onLikeUpdated(false) // TODO: hero.isLiked
    }

    // Bind LiveData Events
    private fun bindEvents(hero: MarvelHeroEntity?) {

        /* set */
        heroDetailViewModel.likeState.observe(this, Observer { isLiked ->
            isLiked?.let {
                showIsLiked(it)
            }
        })

        /* set */
        heroDetailLikeButton.setOnClickListener {
            // TODO: hero.isLiked
            heroDetailViewModel.onLikeUpdated(!(heroDetailViewModel.likeState.value!!))
        }
    }
    private fun showIsLiked(isLiked: Boolean) {
        heroDetailLikeButton.setImageResource(if (isLiked) R.drawable.ic_heartitem_enabled else R.drawable.ic_heartitem)
    }

    // Fill Hero Data
    private fun fillHeroData(hero: MarvelHeroEntity) {

        // Set 'heroDetailImage' with Entity's photoUrl:
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        /* set */
        heroDetailName.text         = hero.name
        heroDetailRealName.text     = hero.realName
        heroDetailHeight.text       = hero.height
        heroDetailPower.text        = hero.power
        heroDetailAbilities.text    = hero.abilities
    }

    // Handle Back Home Pressed
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}