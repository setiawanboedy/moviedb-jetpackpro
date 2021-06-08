package com.attafakkur.moviedbpro.utils

import android.view.View
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar

fun ProgressBar.showPB(){
    visibility = View.VISIBLE
}
fun ProgressBar.hidePB(){
    visibility = View.GONE
}

fun View.snack(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun refresh(swipeRefreshLayout: SwipeRefreshLayout, listener: (Any) -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        listener.also(listener)

        swipeRefreshLayout.isRefreshing = false
    }
}