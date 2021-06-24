package com.example.tvmazechallenge.fragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tvmazechallenge.component.ShowsLoader
import com.example.tvmazechallenge.databinding.FragmentShowLoadFailListDialogBinding
import com.example.tvmazechallenge.model.Show
import retrofit2.Callback

const val ARG_ERROR_MESSAGE = "error_message"

class ShowLoadFailFragment(showsLoader: ShowsLoader) : BottomSheetDialogFragment() {
    private val _showsLoader: ShowsLoader = showsLoader
    private var _binding: FragmentShowLoadFailListDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowLoadFailListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textView3.text = arguments?.getString(ARG_ERROR_MESSAGE)
        binding.button.setOnClickListener {
            this.dismiss()
            _showsLoader.load()
        }
    }

    companion object {
        fun newInstance(errorMessage: String, param: ShowsLoader): ShowLoadFailFragment =
            ShowLoadFailFragment(param).apply {
                arguments = Bundle().apply {
                    putString(ARG_ERROR_MESSAGE, errorMessage)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}