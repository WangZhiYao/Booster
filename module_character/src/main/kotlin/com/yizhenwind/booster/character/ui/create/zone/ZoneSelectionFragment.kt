package com.yizhenwind.booster.character.ui.create.zone

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
 * @since 2022/9/3
 */
@AndroidEntryPoint
class ZoneSelectionFragment :
    BaseListMVIFragment<ZoneSelectionViewState, ZoneSelectionSideEffect>() {

    private val activityViewModel by activityViewModels<CreateCharacterViewModel2>()
    private val viewModel by viewModels<ZoneSelectionViewModel>()
    private val adapter = ZoneSelectionAdapter()

    override fun initView() {
        binding.rvList.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ZoneSelectionFragment.adapter
        }

        adapter.onZoneSelectedListener = { zone ->
            activityViewModel.setZone(zone)
            findNavController().navigate(R.id.action_nav_zone_selection_to_nav_server_selection)
        }
    }

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
        activityViewModel.zone.observe(viewLifecycleOwner) { zone ->
            adapter.checkedZone = zone
        }
    }

    override fun render(state: ZoneSelectionViewState) {
        adapter.zoneList = state.zoneList
    }
}