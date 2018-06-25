package com.costular.marvelheroes.presentation.util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.herodetail.HeroDetailActivity

/**
 * Navigator Utility
 */
class Navigator {

    // Goto MarvelHeroDetail Activity
    fun goToHeroDetail(activity: Activity, hero: MarvelHeroEntity, image: View) {
        val intent = Intent(activity, HeroDetailActivity::class.java).apply {
            putExtra(HeroDetailActivity.PARAM_HEROE, hero)
        }

        /* start */
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image,
                ViewCompat.getTransitionName(image))
        activity.startActivity(intent, options.toBundle())
    }
}