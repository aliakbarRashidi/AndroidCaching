package com.robertlevonyan.demo.caching.common.view.objectbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.robertlevonyan.demo.caching.common.rv.MoviesAdapter
import com.robertlevonyan.demo.caching.databinding.FragmentObjectBoxBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ObjectBoxFragment : Fragment() {
  companion object {
    fun newInstance() = ObjectBoxFragment()
  }

  private val viewModel: ObjectBoxViewModel by viewModel()
  private val binding by lazy { FragmentObjectBoxBinding.inflate(layoutInflater) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launchWhenCreated {
      val adapter = MoviesAdapter()
      binding.rvMovies.adapter = adapter
      viewModel.allMovies.collect { adapter.apply { submitList(it) } }
    }
  }
}
