package com.example.tvmazechallenge.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvmazechallenge.component.ShowRecyclerViewAdapter
import com.example.tvmazechallenge.component.ShowsLoader
import com.example.tvmazechallenge.databinding.FragmentShowsListBinding
import com.example.tvmazechallenge.model.Show
import com.example.tvmazechallenge.network.TvMazeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.min
import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 */
class ShowsFragment : Fragment() {

    private var columnCount = 1
    private var count = 25

    private var _binding: FragmentShowsListBinding? = null
    private val binding get() = _binding!!

    val items: MutableList<Show> = ArrayList()
    lateinit var showsLoader: ShowsLoader;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowsListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = ShowRecyclerViewAdapter(items)
                showsLoader = ShowsLoader(adapter as ShowRecyclerViewAdapter, items, count, super.getChildFragmentManager());
                showsLoader.load();
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_COUNT = "count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ShowsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putInt(ARG_COUNT, count)
                }
            }
    }
}