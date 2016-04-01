package com.seredasv.coloringbook.models

import android.graphics.Bitmap

data class CartoonModel(val filename: String, val originalBitmap: Bitmap, val scaledBitmap: Bitmap, val ratio: Double) {
}