package com.lukman.jetpackfinal.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukman.jetpackfinal.R
import com.lukman.jetpackfinal.databinding.FragmentTvBinding


class TvFragment : Fragment() {



    private var _tvFragmentBinding : FragmentTvBinding? = null

    private val tvFragmentBinding get() = _tvFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _tvFragmentBinding = FragmentTvBinding.inflate(LayoutInflater.from(context), container, false)
        return tvFragmentBinding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _tvFragmentBinding = null
    }

}