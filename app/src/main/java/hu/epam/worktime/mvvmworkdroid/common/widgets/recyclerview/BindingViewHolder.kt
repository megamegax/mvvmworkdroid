package hu.epam.worktime.mvvmworkdroid.common.widgets.recyclerview

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * General viewholder class for recyclerview items when using databinding.
 * Created by Mate_Redecsi on 10/19/2016.
 */
class BindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
