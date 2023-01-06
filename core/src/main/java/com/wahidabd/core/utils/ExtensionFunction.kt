package com.wahidabd.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import coil.transition.CrossfadeTransition

fun ImageView.loadImageUrl(url: String) =
    this.load(url) {
        crossfade(true)
        transitionFactory(CrossfadeTransition.Factory())
    }

fun Fragment.showToast(msg: String) =
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

fun ProgressBar.setProgress(state: Boolean) =
    if (state) this.visibility = View.VISIBLE
    else this.visibility = View.GONE