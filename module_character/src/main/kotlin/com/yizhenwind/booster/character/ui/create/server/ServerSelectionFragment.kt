package com.yizhenwind.booster.character.ui.create.server

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yizhenwind.booster.character.R
import com.yizhenwind.booster.character.ui.create.CreateCharacterViewModel2
import com.yizhenwind.booster.component.base.BaseListMVIFragment
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/5
 */
@AndroidEntryPoint
class ServerSelectionFragment :
    BaseListMVIFragment<ServerSelectionViewState, ServerSelectionSideEffect>() {

    private val activityViewModel by activityViewModels<CreateCharacterViewModel2>()
    private val viewModel by viewModels<ServerSelectionViewModel>()
    private val adapter = ServerSelectionAdapter()

    override fun initView() {
        binding.rvList.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ServerSelectionFragment.adapter
        }

        adapter.onServerSelectedListener = { server ->
            activityViewModel.setServer(server)
            findNavController().navigate(R.id.action_nav_server_selection_to_nav_account_input)
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        activityViewModel.zone.observe(viewLifecycleOwner) { zone ->
            viewModel.getServerListByZone(zone)
        }
        activityViewModel.server.observe(viewLifecycleOwner) { server ->
            adapter.checkedServer = server
        }
    }

    override fun render(state: ServerSelectionViewState) {
        state.serverList.let { serverList ->
            adapter.serverList = serverList
            if (serverList.size == 1) {
                activityViewModel.setServer(serverList.first())
            }
        }
    }
}