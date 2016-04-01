package com.seredasv.coloringbook.presenters.coloring_presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import com.seredasv.coloringbook.views.custom_views.FillView

class ColoringPresenterImpl : ColoringPresenter {
    private var context: Context
    private lateinit var paint: Paint

    constructor(context: Context) {
        this.context = context
    }

    override fun getPaint(): Paint {
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 1f

        return paint
    }

    override fun getBitmapFromStorage(): Bitmap {
        throw UnsupportedOperationException()
    }

    override fun getFillView(fileName: String, statusBarHeight: Int): FillView {
        throw UnsupportedOperationException()
    }
}