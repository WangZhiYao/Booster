package com.yizhenwind.booster.character.ui.create

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.databinding.ActivityCreateCharacter2Binding
import com.yizhenwind.booster.component.base.BaseMVIActivity
import com.yizhenwind.booster.component.ext.activityArgs
import com.yizhenwind.booster.component.ext.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
@AndroidEntryPoint
class CreateCharacterActivity2 :
    BaseMVIActivity<CreateCharacterViewState, CreateCharacterSideEffect>() {

    private val viewModel by viewModels<CreateCharacterViewModel2>()
    private val binding by viewBindings<ActivityCreateCharacter2Binding>()
    private val args by activityArgs<CreateCharacterArgs>()
    private val appBarConfiguration = AppBarConfiguration(emptySet())

    override fun getRootView(): View = binding.root

    override fun initPage() {
        initView()
        initData()
    }

    private fun initView() {
        binding.apply {
            toolbar.apply {
                setSupportActionBar(this)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }

            val navController = navHostFragment.getFragment<NavHostFragment>().navController
            navController.setGraph(R.navigation.navigation_create_character)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    private fun initData() {
        viewModel.observe(this)
        args.customer?.let { viewModel.setCustomer(it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}