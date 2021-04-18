package com.example.abodemart.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MSPEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    init {
        // call the func to apply the font to the components
        applyFont()
    }
    private fun applyFont() {
        // This is used to get the file from the assets folder and set it to the title textView
        val typeface: Typeface = Typeface.createFromAsset(context.assets, "raleway.regular.ttf")
        setTypeface(typeface)
    }
}