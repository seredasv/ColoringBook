package com.seredasv.coloringbook.presenters.coloring_presenter

import android.graphics.Bitmap
import android.graphics.Paint
import com.seredasv.coloringbook.views.custom_views.FillView

interface ColoringPresenter {
    fun getPaint(): Paint
    fun getBitmapFromStorage(): Bitmap
    fun getFillView(fileName: String, statusBarHeight: Int): FillView
}