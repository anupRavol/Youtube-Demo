package com.example.youtubedemo

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubedemo.Constants.API_KEY
import com.example.youtubedemo.data.Item
import com.example.youtubedemo.data.VideoListResponse
import com.example.youtubedemo.di.DaggerViewModelComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularListViewModel() : ViewModel(){

    constructor(test : Boolean = true):this(){
        injected = true
    }

    @Inject
    lateinit var networkRepository :NetworkRepository

    private var subscription: Disposable? = null
    val loadingVisibility by lazy {  MutableLiveData<Int>() }
    val errorMessage by lazy { MutableLiveData<Int>() }
    val errorClickListener = View.OnClickListener { loadPopularList() }

    private var injected = false

    fun inject(){
        if(!injected)
            DaggerViewModelComponent.create().inject(this)
    }

    val popularPostList : MutableLiveData<List<Item>> = MutableLiveData()

    fun loadPopularList(){
        inject()
        val map :HashMap<String, String> = HashMap()
            map.put("part", "snippet,contentDetails,statistics")
            map.put("chart", "mostPopular")
            map.put("maxResults", "20")
            map.put("regionCode", "IN")
            map.put("key", API_KEY)

        subscription = networkRepository.getPopularPosts(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePopularListStart() }
            .doOnTerminate { onRetrievePopularListFinish() }
            .subscribe(
                { result -> onRetrievePopularListSuccess(result) },
                { error -> onRetrievePopularListError() }
            )
    }

    private fun onRetrievePopularListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePopularListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePopularListSuccess(response: VideoListResponse){
        popularPostList.postValue(response.items)
    }

    private fun onRetrievePopularListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }
}