package com.example.authactivity.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.authactivity.ui.lang.ContextUtils
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel<BaseEvent>, VB : androidx.viewbinding.ViewBinding>(
    private val clazz: KClass<VM>
) : AppCompatActivity() {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        viewModel = getViewModel(clazz = clazz)
        setupViews()
        subscribeToLiveData()
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}
