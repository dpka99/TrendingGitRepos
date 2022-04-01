package com.trendinggitrepos.presentation.repolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.trendinggitrepos.R
import com.trendinggitrepos.data.model.RepoItem
import com.trendinggitrepos.databinding.ItemRepoBinding


class RepoListRecyclerAdapter(val onItemClickListener: (String) -> Unit) :
    RecyclerView.Adapter<RepoListRecyclerAdapter.ViewHolder>() {

    private var repositories = ArrayList<RepoItem>()

    inner class ViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: RepoItem) {

            binding.apply {
                tvRepoName.text = repoItem.fullName?.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
                tvRepoDesc.text = repoItem.description
                tvLanguage.text = repoItem.language
                cardview.setOnClickListener {
                    repoItem.nodeId?.let { nId -> onItemClickListener(nId) }
                }
            }

        }
    }

    fun setRepoList(_repositories: List<RepoItem>) {
        repositories.clear()
        repositories.addAll(_repositories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(
            inflater,
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int {
        return repositories.size
    }
}