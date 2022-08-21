package com.tkm.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class Fragment2 : Fragment() {

    private lateinit var mSb: SeekBar

    private val mViewModel: FragmentsViewModel by lazy {
        ViewModelProvider(requireActivity())[FragmentsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_2, container, false)
        mSb = rootView.findViewById(R.id.sb)

        mSb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mViewModel.valueData.value = progress
                }
            }
        })

        mViewModel.valueData.observe(requireActivity()) {
            mSb.progress = it
        }

        return rootView
    }
}