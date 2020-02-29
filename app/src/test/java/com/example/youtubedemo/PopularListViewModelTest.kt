package com.example.youtubedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.youtubedemo.data.Item
import com.example.youtubedemo.data.PageInfo
import com.example.youtubedemo.data.VideoListResponse
import com.example.youtubedemo.di.DaggerViewModelComponent
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule




class PopularListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Mock
    lateinit var networkRepository: NetworkRepository

    var popularListViewModel= PopularListViewModel(true)



    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        val immidiate = object :Scheduler(){
            override fun createWorker(): Worker {
                val worker = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                return worker
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { schedular -> immidiate}
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedular-> immidiate }



        DaggerViewModelComponent.builder()
            .apiModule(ApiModuleTest(networkRepository))
            .build()
            .inject(popularListViewModel)
    }



    @Test
    fun getPopularSuccess(){
        val item = Item(null, "etag", "id", null, null, null )
        val list = listOf(item)
        val successResponse = VideoListResponse("tag", list, "", "", PageInfo(1, 1))


        val map = HashMap<String, String>().apply {
            put("part", "snippet,contentDetails,statistics")
            put("chart", "mostPopular")
            put("maxResults", "20")
            put("regionCode", "IN")
            put("key", Constants.API_KEY)
        }
        `when`(networkRepository.getPopularPosts(map)).thenReturn(Observable.just(successResponse))

        popularListViewModel.loadPopularList()

        Assert.assertEquals(1, popularListViewModel.popularPostList.value?.size)
    }
}