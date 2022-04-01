package com.trendinggitrepos.presentation.repodetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.trendinggitrepos.R
import com.trendinggitrepos.databinding.FragmentGithubRepoDetailBinding
import com.trendinggitrepos.presentation.repolist.GithubRepoViewModel

val TAG = "GithubRepoDetailFr"

class GithubRepoDetailFragment : Fragment() {

    private var _binding: FragmentGithubRepoDetailBinding? = null
    val binding get() = _binding!!

    private val sharedViewModel: GithubRepoViewModel by activityViewModels()
    val args: GithubRepoDetailFragmentArgs by navArgs()

    private val backPressDispatcher = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            // Redirect to our own function
            this@GithubRepoDetailFragment.onBackPressed()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this, backPressDispatcher)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGithubRepoDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getRepositoryByNodeId(args.nodeId).observe(this, Observer {
            Log.d(TAG, it.toString())

            binding.apply {
                tvRepoName.text = it.fullName?.replaceFirstChar {
                        firstChar ->
                    firstChar.uppercase()
                }

                tvRepoDesc.text = it.description
                tvVisibility.text ="${getString(R.string.visibility)}: ${it.visibility}"

                tvForks.text = "${getString(R.string.forks)}\n${it.forksCount}"
                tvStars.text = "${getString(R.string.stars)}\n${it.stargazersCount}"
                tvIssues.text = "${getString(R.string.issues)}\n${it.openIssues}"
            }
        })
    }

    private fun onBackPressed() {
        Navigation.findNavController(requireView()).popBackStack()
    }

}