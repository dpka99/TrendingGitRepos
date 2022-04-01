package com.trendinggitrepos.presentation.repolist

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trendinggitrepos.R
import com.trendinggitrepos.common.NetworkValidator
import com.trendinggitrepos.databinding.FragmentGithubRepoListBinding
import com.trendinggitrepos.presentation.App
import javax.inject.Inject


class GithubRepoListFragment : Fragment() {

    private val sharedViewModel: GithubRepoViewModel by activityViewModels()
    private var _binding: FragmentGithubRepoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RepoListRecyclerAdapter

    @Inject
    lateinit var networkValidator: NetworkValidator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            (activity?.application as App).appComponent.inject(this)
        } catch (e: Exception) {
            Log.e("Error", "onAttach")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGithubRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = RepoListRecyclerAdapter() { nodeId -> onRepoItemClicked(nodeId) }
        binding.apply {
            recyclerview.layoutManager = LinearLayoutManager(requireActivity())
            recyclerview.adapter = adapter
        }

        displayGithubRepositories()

    }

    private fun displayGithubRepositories() {
        if (!networkValidator.isNetworkAvailable()) {
            binding.tvMessage.text = getString(R.string.network_error_message)
        }

        val responseLiveData = sharedViewModel.getReposData()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                if (!networkValidator.isNetworkAvailable() && it.isNotEmpty()) {
                    binding.tvMessage.text = ""
                }
                adapter.setRepoList(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

//    private fun workInfoObserver() {
//        sharedViewModel.getSyncOutput().observe(this, Observer { listOfWorkInfo ->
//
//            Log.e("workInFoObserver", listOfWorkInfo.toString())
//            if (listOfWorkInfo.value.isNullOrEmpty()) {
//                return@Observer
//            }
//
//            val workInfo = listOfWorkInfo.value!!.get(0)
//            Log.e("workInFoObserver", workInfo.state.isFinished.toString())
//            if (workInfo.state.isFinished) {
//                displayGithubRepositories()
//            }
//        })
//    }

    private fun onRepoItemClicked(nodeId: String) {
        val action =
            GithubRepoListFragmentDirections
                .actionGithubRepoListFragmentToGithubRepoDetailFragment(nodeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}