package scott.stromberg.kickstarterchallenge.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import scott.stromberg.kickstarterchallenge.databinding.MainFragmentBinding
import scott.stromberg.kickstarterchallenge.ui.main.GifsAdapter.GifViewHolder
import scott.stromberg.kickstarterchallenge.util.observeTextChange
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var viewBinding: MainFragmentBinding
    private val disposables = CompositeDisposable()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MainFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = GifsAdapter() {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.giphyUrl)))
        }
        viewBinding.recyclerView.addRecyclerListener { viewHolder ->
            val gifViewHolder = viewHolder as GifViewHolder
            Glide.with(requireContext()).clear(gifViewHolder.viewBinding.imageView)
        }

        disposables.add(
            viewBinding.searchView.observeTextChange()
                .subscribe { query ->
                    doSearch(query)
                }
        )

        viewBinding.searchView.onActionViewExpanded()
    }

    private fun doSearch(query: String) {
        if (query.isNotEmpty()) {
            viewBinding.progressBar.visibility = View.VISIBLE

            disposables.add(
                viewModel.doSearch(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        viewBinding.progressBar.visibility = View.GONE
                    }
                    .subscribe({ gifs ->
                        (viewBinding.recyclerView.adapter as GifsAdapter).items = gifs
                    }, {
                        Timber.e(it)
                    })
            )
        }
        else {
            (viewBinding.recyclerView.adapter as GifsAdapter).items = listOf()
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}