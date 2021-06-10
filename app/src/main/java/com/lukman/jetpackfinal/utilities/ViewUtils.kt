package com.lukman.jetpackfinal.utilities

import android.widget.ProgressBar
import androidx.core.view.isVisible


fun ProgressBar.isShown(flag: Boolean){
    this.isVisible = flag
}