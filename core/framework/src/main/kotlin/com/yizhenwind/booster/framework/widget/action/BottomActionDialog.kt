package com.yizhenwind.booster.framework.widget.action

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yizhenwind.booster.framework.databinding.ItemBottomOptionBinding
import com.yizhenwind.booster.framework.databinding.LayoutDialogBottomActionBinding
import com.yizhenwind.booster.framework.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/22
 */
class BottomActionDialog<T : Action> private constructor(
    private val builder: Builder<T>
) : BottomSheetDialogFragment() {

    private var _binding: LayoutDialogBottomActionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutDialogBottomActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            tvActionTitle.text = builder.title
            rvActionList.adapter = ActionAdapter()
        }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    override fun onDestroyView() {
        binding.rvActionList.adapter = null
        super.onDestroyView()
        _binding = null
    }

    private inner class ActionAdapter : RecyclerView.Adapter<ActionViewHolder<T>>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder<T> =
            ActionViewHolder<T>(parent.viewBinding(ItemBottomOptionBinding::inflate)).apply {
                onItemClickListener = { action ->
                    builder.onActionClickListener?.invoke(this@BottomActionDialog, action)
                }
            }

        override fun onBindViewHolder(holder: ActionViewHolder<T>, position: Int) {
            holder.bind(builder.actions[position])
        }

        override fun getItemCount(): Int = builder.actions.size

    }

    class Builder<T : Action> {
        var title: String = ""
            private set
        var actions: List<T> = emptyList()
            private set
        var onActionClickListener: ((BottomActionDialog<T>, T) -> Unit)? = null
            private set

        fun setTitle(title: String) =
            apply { this.title = title }

        fun setActions(actions: List<T>) =
            apply { this.actions = actions }

        fun setOnActionClickListener(onActionClickListener: ((BottomActionDialog<T>, T) -> Unit)?) =
            apply { this.onActionClickListener = onActionClickListener }

        fun build(): BottomActionDialog<T> = BottomActionDialog(this)

        fun show(fm: FragmentManager): BottomActionDialog<T> = build().apply { show(fm) }
    }

    companion object {

        private const val TAG = "BottomActionDialog"
    }
}