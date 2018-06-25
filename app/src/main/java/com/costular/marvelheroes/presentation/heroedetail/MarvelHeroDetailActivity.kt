package com.costular.marvelheroes.presentation.heroedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.activity_hero_detail.*

/**
 * Marvel Hero Detail
 */
class MarvelHeroDetailActivity : AppCompatActivity() {
    companion object {
        const val PARAM_HEROE = "heroe"
    }

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

        /* fill */
        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let { fillHeroData(it) }
    }
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

        /* set */
        val likeImageBtn = findViewById(R.id.like_image_button) as ImageButton
        likeImageBtn.setImageResource(R.drawable.ic_heartitem)
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