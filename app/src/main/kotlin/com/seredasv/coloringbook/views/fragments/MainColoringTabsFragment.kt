package com.seredasv.coloringbook.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seredasv.coloringbook.R

class MainColoringTabsFragment : Fragment() {
    private val FILE_NAME: String = "file_name"

    companion object {
        fun newInstance(fileName: String): MainColoringTabsFragment {
            val fragment = MainColoringTabsFragment()

            val args = Bundle()
            args.putString("file_name", fileName)
            fragment.arguments = args

            return fragment
        }

        fun newInstance(): MainColoringTabsFragment {
            return MainColoringTabsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.fragment_main_coloring_tabs, container, false)

        findView(view)

        return view
    }

    private fun findView(view: View) {

    }
}