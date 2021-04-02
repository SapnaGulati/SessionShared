package com.example.sharedpreferencesession.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharedpreferencesession.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var pb: ProgressBar
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        pb = root.findViewById(R.id.progressBar)
        rv = root.findViewById(R.id.recyclerView)
        return root
    }
    private val request = ServiceBuilder.buildService(TmdbEndPoints::class.java)
    private val call = request.getMovies(getString(R.string.api_key))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        call.enqueue(object:  Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                    pb.visibility = View.GONE
                    rv.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(activity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
