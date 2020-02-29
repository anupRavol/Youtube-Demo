package com.example.youtubedemo

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubedemo.data.Item
import com.example.youtubedemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PopularListAdapter.PopularListEvents {
    override fun onPostClicked(item: Item) {
        startActivity(YoutubeActivity.getIntent(this@MainActivity, item))
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PopularListViewModel

    private var errorSnackbar: Snackbar? = null

    private lateinit var popularListAdapter: PopularListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.postList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(PopularListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        post_list.layoutManager = LinearLayoutManager(this)

        popularListAdapter = PopularListAdapter(this)
        post_list.adapter = popularListAdapter

        viewModel.popularPostList.observe(
            this,
            Observer { popularListAdapter.setPopularVideos(it) })
        viewModel.loadPopularList()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
