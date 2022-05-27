package com.dwicandra.githubuserapi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwicandra.githubuserapi.Adapter.ListUserAdapter
import com.dwicandra.githubuserapi.DetailActivity
import com.dwicandra.githubuserapi.R
import com.dwicandra.githubuserapi.ViewModel.MainViewModel
import com.dwicandra.githubuserapi.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arg = arguments
        username = arg?.getString(DetailActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowingBinding.bind(view)

        initializeAdapter()
        mainViewModel.getFollowingUsers(username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding?.rvFollowing?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(activity, layoutManager.orientation)
        binding?.rvFollowing?.addItemDecoration(itemDecoration)

        observeData()
    }

    private fun observeData() {
        mainViewModel.apply {
            listFollowingGithub.observe(viewLifecycleOwner) {
                if (it != null) {
                    val adapter = ListUserAdapter(it)
                    binding?.rvFollowing?.adapter = adapter
                }
                isLoading.observe(viewLifecycleOwner) {boolean->
                    showLoading(boolean)
                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }

}