package io.github.aungkothet.padc.assignment11.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.aungkothet.padc.assignment11.R
import io.github.aungkothet.padc.assignment11.adapters.PhotoItemAdapter
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.github.aungkothet.padc.assignment11.mvp.presenters.PhotoListPresenter
import io.github.aungkothet.padc.assignment11.mvp.views.PhotoListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseAcitvity(), PhotoListView {

    override fun displayPhotoList(photoList: List<PhotoVO>) {
        photoItemAdapter.setNewData(photoList.toMutableList())
    }

    override fun displayError(errorMessage: String) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun navigateToDetail(photoId: String) {
        startActivity(PhotoDetailActivity.newIntent(applicationContext, photoId))
    }

    private lateinit var photoItemAdapter: PhotoItemAdapter
    private lateinit var photoListPresenter: PhotoListPresenter

    private lateinit var searchDisposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoListPresenter = ViewModelProviders.of(this).get(PhotoListPresenter::class.java)
        photoListPresenter.initPresenter(this)

        photoItemAdapter = PhotoItemAdapter(photoListPresenter)
        rvPhotos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvPhotos.setHasFixedSize(true)
        rvPhotos.adapter = photoItemAdapter

        searchDisposable = Observable.create<String> { emitter ->
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(p0.toString())
                }
            })
        }.debounce(600, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.isNotBlank()) {
                    photoListPresenter.onSearchType(it, this)
                }
            }.subscribe({
                println(it)
            },{
                Log.e("AKTERR",it.localizedMessage)
            },{

            })
        photoListPresenter.onUiReady(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchDisposable.dispose()
    }
}
