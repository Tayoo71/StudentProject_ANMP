package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentproject.databinding.FragmentCarListBinding
import com.example.studentproject.viewmodel.CarViewModel

class CarListFragment : Fragment() {
    private lateinit var binding: FragmentCarListBinding
    private lateinit var viewModel: CarViewModel
    private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        // Setup RecyclerView
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = carListAdapter

        // Observe perubahan ViewModel
        observeViewModel()

        // Refresh layout handling
        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

    }

    private fun observeViewModel() {
        // Mengamati LiveData yang berisi daftar mobil
        viewModel.carsLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
            binding.recView.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        })

        // Mengamati error dalam memuat data
        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })

        // Mengamati status loading
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}
