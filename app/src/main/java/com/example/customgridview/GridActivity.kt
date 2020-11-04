package com.example.customgridview

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.layout_grid_activity.*

/**
 * Created by Deepak Mandhani on 2020-02-13.
 */
private const val MAX_INPUT = 50

class GridActivity : AppCompatActivity() {

    private var boxSize = 0
    private var increasedWidth = 0
    private var dimen10 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_grid_activity)
        tv1.setOnClickListener { inflateSelectedLayoutView(true) }
        tv2.setOnClickListener { inflateSelectedLayoutView(false) }
        boxSize = getDeviceWidth(this) / 4
        dimen10 = resources.getDimension(R.dimen.dimen_10).toInt()
        inflateSelectedLayoutView(false)
    }

    private fun inflateSelectedLayoutView(isGrid: Boolean) {
        val count = et.text.toString().toInt()
        if (count > MAX_INPUT) {
            Toast.makeText(this, getString(R.string.input_error_msg), Toast.LENGTH_SHORT).show()
            return
        }
        cg.removeAllViews()
        increasedWidth = 0
        if (isGrid)
            for (i in 1..count)
                cg.addView(getFixedChip(i))
        else
            for (i in 1..count)
                cg.addView(getIncreasingChip(i))

    }

    private fun getIncreasingChip(num: Int): Chip {
        val chip = getChip(num)
        increasedWidth += dimen10
        chip.layoutParams.width = increasedWidth
        return chip
    }

    private fun getFixedChip(num: Int): Chip {
        val chip = getChip(num)
        chip.layoutParams.width = boxSize
        return chip
    }

    private fun getChip(num: Int): Chip {
        val itemView = LayoutInflater.from(this).inflate(R.layout.view_grid_item, cg, false)
        val chip = itemView as Chip
        chip.text = num.toString()
        chip.layoutParams.height = boxSize
        return chip
    }

    private fun getDeviceWidth(activity: Activity?): Int {
        if (activity == null) return 0
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}