 package com.example.authactivity.ui.mycontacts.bottomSheet

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomSheetBinding
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.model.AlertData
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import android.content.Intent as Intent1

 class AddBottomSheetFragment(contactsActivity: ContactsActivity) : BaseAddBottomSheetFragment(), ClickListenerBottom {

     lateinit var binding: LayoutAddBottomSheetBinding
     private lateinit var viewModel: ContactsViewModel
     private lateinit var adapter: AdapterBottomSheet

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         binding = LayoutAddBottomSheetBinding.inflate(
             inflater, container, false
         )
         return binding.root
     }

     override fun setupViews() {
         viewModel = getViewModel(clazz = ContactsViewModel::class)
         setupListener()
         setupSearchView()
         showAlertEdit()
         setupRecyclerView()
         subscribe()
     }

     private fun setupRecyclerView() {
         adapter = AdapterBottomSheet(this){onItemClick(it)}
         binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
         binding.recyclerView.adapter = adapter
     }

     private fun subscribe() {
         viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer { adapter.addItems(it) })
         viewModel.subscribeToData()
         viewModel.subscribeToMessage()
         viewModel.getList()
     }

     private fun onItemClick(item: ListData) {
         val intent = Intent1(requireContext(), ContactsActivity::class.java)
         intent.putExtra("ITEM_KEY" , item.name)
         startActivity(intent)
     }

     private fun showAlertEdit() {
         binding.add.setOnClickListener {
             val alert = AlertDialog.Builder(requireContext(), R.style.AddDialogStyle)
             val inflater = layoutInflater.inflate(R.layout.alert_edit, null)
             alert.setView(inflater)
             val negativeButton: Button = inflater.findViewById(R.id.btn_no)
             val positiveButton: Button = inflater.findViewById(R.id.btn_yes)
             val nameEditText: EditText = inflater.findViewById(R.id.et_name)
             val dialog = alert.create()
             negativeButton.setOnClickListener {
                 dialog.dismiss()
             }
             positiveButton.setOnClickListener {
                 checkField(nameEditText, dialog)
             }
             dialog.show()
         }
     }

     private fun checkField(
         nameEditText: EditText, dialog: AlertDialog
     ) {
         var error = 0
         if (nameEditText.isEmptyInputData(getString(R.string.add_name))) error += 1
         if (error > 0) return

         addItem(nameEditText, dialog)
     }

     fun addItem(nameEditText: EditText, dialog: AlertDialog) {
         val alert = AlertData(0, nameEditText.text.toString())
         dialog.dismiss()
         adapter.addItem(alert)
         viewModel.insertList(alert)
     }

     private fun setupSearchView() {
         binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String): Boolean {
                 if (newText == "") adapter.addItems(viewModel.filteredList)
                 else {
                     val searchText = newText.toLowerCase()
                     val filtered = mutableListOf<ListData>()
                     viewModel.filteredList.forEach {
                         if (it.name.toLowerCase().contains(searchText)) filtered.add(it)
                     }
                     adapter.addItems(filtered)
                 }
                 return false
             }
         })
     }

     private fun setupListener() {
         binding.back.setOnClickListener { this.onDestroyView() }
     }

     override fun subscribeToLiveData() {}
     override fun onItemClickBottom(item: ListData) {}
     override fun onLongItemClickBottom(item: ListData) {}
 }




