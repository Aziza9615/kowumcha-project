package com.example.authactivity.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentLangSettingsBinding
import com.example.authactivity.databinding.FragmentStatisticBinding
import com.example.authactivity.ui.onBoard.OnBoardViewModel

class StatisticsFragment : BaseFragment<OnBoardViewModel, FragmentStatisticBinding>(OnBoardViewModel::class) {
    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }

    override fun attachBinding(
            list: MutableList<FragmentStatisticBinding>,
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            attachToRoot: Boolean
    ) {
        list.add(FragmentStatisticBinding.inflate(layoutInflater, container, attachToRoot))
    }
}