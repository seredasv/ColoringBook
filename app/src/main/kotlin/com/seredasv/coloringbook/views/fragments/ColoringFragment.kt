package com.seredasv.coloringbook.views.fragments

import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.seredasv.coloringbook.R
import com.seredasv.coloringbook.presenters.coloring_presenter.ColoringPresenter
import com.seredasv.coloringbook.presenters.coloring_presenter.ColoringPresenterImpl

class ColoringFragment : Fragment() {
    private val FILE_NAME = "file_name"

    private lateinit var ivGif: ImageView
    private lateinit var paint: Paint
    private lateinit var fileName: String
    private lateinit var coloringPresenter: ColoringPresenter

    companion object {
        fun newInstance(fileName: String): ColoringFragment {
            val fragment = ColoringFragment()

            val args = Bundle()
            args.putString("file_name", fileName)
            fragment.arguments = args

            return fragment
        }

        fun newInstance(): ColoringFragment {
            return ColoringFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        coloringPresenter = ColoringPresenterImpl(getActivity())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.fragment_main_coloring_tabs, container, false)

        findView(view)

        return view
    }

    private fun findView(view: View) {

    }
}