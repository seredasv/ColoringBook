package com.seredasv.coloringbook.helpers

import android.content.Context
import android.widget.Toast

public class ToastHelper {
    companion object {
        fun showToastShort(context: Context, message: String): Unit {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showToastLong(context: Context, message: String): Unit {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}