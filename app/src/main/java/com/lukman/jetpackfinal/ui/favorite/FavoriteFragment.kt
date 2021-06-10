package com.lukman.jetpackfinal.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukman.jetpackfinal.R
import com.lukman.jetpackfinal.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {



    private var _favoriteFragmentBinding : FragmentFavoriteBinding? = null
    private val favoriteFragmentBinding get() = _favoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _favoriteFragmentBinding = FragmentFavoriteBinding.inflate(LayoutInflater.from(context), container, false)
        return favoriteFragmentBinding?.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _favoriteFragmentBinding = null
    }


}