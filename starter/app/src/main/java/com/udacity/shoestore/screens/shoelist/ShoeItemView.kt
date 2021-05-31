package com.udacity.shoestore.screens.shoelist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.udacity.shoestore.databinding.ShoeItemViewBinding
import com.udacity.shoestore.models.Shoe

class ShoeItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ShoeItemViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setup(shoe: Shoe) = with(binding) {
        shoeName.text = shoe.name
        shoeSize.text = shoe.getFormattedShoeSize()
        shoeCompany.text = shoe.company
        shoeDescription.text = shoe.description
    }
}