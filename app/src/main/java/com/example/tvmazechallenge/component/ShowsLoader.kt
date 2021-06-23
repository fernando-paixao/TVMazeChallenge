package com.example.tvmazechallenge.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.example.tvmazechallenge.fragments.ShowLoadFailFragment
import com.example.tvmazechallenge.model.Show
import com.example.tvmazechallenge.network.TvMazeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowsLoader(private val adapter: ShowRecyclerViewAdapter, private val items: MutableList<Show>, private val count: Int, private val fragmentManager: FragmentManager) {

    private val _this = this

    fun load(){
        TvMazeApi.retrofitService.getProperties().enqueue(
            object: Callback<List<Show>> {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(
                    call: Call<List<Show>>,
                    response: Response<List<Show>>
                ) {
                    if( response.body() != null ) {
                        val qtd = Integer.min(count, response.body()!!.size - 1);
                        for (i in 1..qtd) {
                            items.add(response.body()!![i])
                            adapter.notifyItemRangeInserted(i,1)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Show>>, t: Throwable) {
                    val errorMessage = "Failure: " + t.message
                    val showLoadFailFragment: ShowLoadFailFragment =
                        ShowLoadFailFragment.newInstance(errorMessage, _this)
                    showLoadFailFragment.show(
                        fragmentManager
                        ,"show_load_fail_fragment"
                    )
                }
            })
    }

    //fun createCallback() {

    //}

    fun onSuccess() {

    }

    fun onError() {

    }
}