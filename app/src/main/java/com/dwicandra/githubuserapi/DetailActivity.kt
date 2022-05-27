package com.dwicandra.githubuserapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dwicandra.githubuserapi.Adapter.SectionPagerAdapter
import com.dwicandra.githubuserapi.ViewModel.MainViewModel
import com.dwicandra.githubuserapi.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val mainViewModel by viewModels<MainViewModel>()

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        @StringRes
        private val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar != null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0f

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        val tabLayout: TabLayout = binding.tabsLayout
        val viewPager: ViewPager2 = binding.viewPager

        val sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager, lifecycle, bundle)
        viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        if (username != null) {
            mainViewModel.getDetailUser(username)
        }
        setDetailUser()

    }

    private fun setDetailUser() {
        mainViewModel.apply {
            detailUserGithub.observe(this@DetailActivity) {
                if (it != null) {
                    binding.apply {
                        Glide.with(this@DetailActivity)
                            .load(it.avatarUrl)
                            .circleCrop()
                            .error(R.mipmap.ic_launcher)
                            .into(ivAvatar)
                        collapsingToolbar.title = it.name
                        tvNameReceiver.text = it.name
                        tvUsernameReceiver.text = it.login
                        tvCompanyReceiver.text = it.company
                        tvLocationReceiver.text = it.location
                        tvFollowers.text = it.followers.toString()
                        tvFollowing.text = it.following.toString()
                        tvRepositories.text = it.publicRepos.toString()
                    }
                }
            }

        }
    }
}