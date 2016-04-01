package com.seredasv.coloringbook.helpers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import com.seredasv.coloringbook.models.CartoonModel
import com.seredasv.coloringbook.models.FloodFillRangeModel
import java.util.*

class FloodFiller {
    var sourceColor = 0
    var targetColor = 0
    var tolerance = intArrayOf(0, 0, 0)
    var bitmap: Bitmap? = null

    private var width = 0
    private var height = 0
    private var pixels: IntArray? = null
    private var pixelsChecked: BooleanArray? = null
    private var sourceColors = intArrayOf(0, 0, 0)
    private var context: Context
    private var cartoonModel: CartoonModel? = null
    private var rangeModels: Queue<FloodFillRangeModel>? = null

    constructor(context: Context, targetColor: Int, sourceColor: Int, cartoonModel: CartoonModel) {
        this.context = context
        this.sourceColor = sourceColor
        this.targetColor = targetColor
        this.cartoonModel = cartoonModel

        useImage(cartoonModel.scaledBitmap)
        setSourceColors(sourceColor)
    }

    fun setSourceColors(sourceColors: Int) {
        this.sourceColors[0] = Color.red(sourceColors)
        this.sourceColors[1] = Color.green(sourceColors)
        this.sourceColors[2] = Color.blue(sourceColors)
    }

    // Copy data from provided Image to a BufferedImage to write flood fill to, use getBitmap to retrieve cache
    // data in member variables to decrease overhead of property calls
    fun copyImage(img: Bitmap) {
        width = img.width
        height = img.height

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(img, 0f, 0f, null)

        pixels = IntArray(width * height)

        bitmap!!.getPixels(pixels, 0, width, 1, 1, width - 1, height - 1)
    }

    // Use a pre-existing provided BufferedImage and write directly to it
    // cache data in member variables to decrease overhead of property calls
    fun useImage(img: Bitmap) {
        width = img.width
        height = img.height
        bitmap = img

        pixels = IntArray(width * height)

        bitmap!!.getPixels(pixels, 0, width, 1, 1, width - 1, height - 1)
    }

    // Called before starting flood-fill
    protected fun prepare() {
        pixelsChecked = BooleanArray(pixels!!.size)
        rangeModels = LinkedList<FloodFillRangeModel>()
    }

    // Fills the specified point on the bitmap with the currently selected fill color.
    // int x, int y: The starting coords for the fill
    fun floodFill(x: Int, y: Int, fileName: String) {
        // Setup
        prepare()

        if (sourceColors[0] == 0) {
            // ***Get starting color.
            val startPixel = pixels!![width * y + x]
            sourceColors[0] = startPixel shr 16 and 0xff
            sourceColors[1] = startPixel shr 8 and 0xff
            sourceColors[2] = startPixel and 0xff
        }

        // ***Do first call to floodfill.
        LinearFill(x, y, fileName)

        // ***Call floodfill routine while floodfill ranges still exist on the
        // queue
        var rangeModel: FloodFillRangeModel

        while (rangeModels!!.size > 0) {
            // **Get Next Range Off the Queue
            rangeModel = rangeModels!!.remove()

            // **Check Above and Below Each Pixel in the Floodfill Range
            var downPxIdx = width * (rangeModel.y + 1) + rangeModel.startX
            var upPxIdx = width * (rangeModel.y - 1) + rangeModel.startX
            val upY = rangeModel.y - 1// so we can pass the y coord by ref
            val downY = rangeModel.y + 1

            for (i in rangeModel.startX..rangeModel.endX) {
                // *Start Fill Upwards
                // if we're not above the top of the bitmap and the pixel above
                // this one is within the color tolerance
                if (rangeModel.y > 0 && !pixelsChecked!![upPxIdx] && CheckPixel(upPxIdx)) {
                    LinearFill(i, upY, fileName)
                }

                // *Start Fill Downwards
                // if we're not below the bottom of the bitmap and the pixel
                // below this one is within the color tolerance
                if (rangeModel.y < height - 1 && !pixelsChecked!![downPxIdx] && CheckPixel(downPxIdx)) {
                    LinearFill(i, downY, fileName)
                }

                downPxIdx++
                upPxIdx++
            }
        }

        bitmap!!.setPixels(pixels, 0, width, 1, 1, width - 1, height - 1)
    }

    // Finds the furthermost left and right boundaries of the fill area
    // on a given y coordinate, starting from a given x coordinate, filling as
    // it goes.
    // Adds the resulting horizontal range to the queue of floodfill ranges,
    // to be processed in the main loop.

    // int x, int y: The starting coords
    protected fun LinearFill(x: Int, y: Int, fileName: String) {
        // ***Find Left Edge of Color Area
        var lFillLoc = x // the location to check/fill on the left
        var pxIdx = width * y + x

        while (true) {
            // **fill with the color
            pixels?.set(pxIdx, targetColor)

            // **indicate that this pixel has already been checked and filled
            pixelsChecked?.set(pxIdx, true)

            // **de-increment
            lFillLoc-- // de-increment counter
            pxIdx-- // de-increment pixel index

            // **exit loop if we're at edge of bitmap or color area
            if (lFillLoc < 0 || pixelsChecked!![pxIdx] || !CheckPixel(pxIdx)) {
                break
            }
        }

        lFillLoc++

        // ***Find Right Edge of Color Area
        var rFillLoc = x // the location to check/fill on the left

        pxIdx = width * y + x

        while (true) {
            // **fill with the color
            pixels?.set(pxIdx, targetColor)

            // **indicate that this pixel has already been checked and filled
            pixelsChecked?.set(pxIdx, true)

            // **increment
            rFillLoc++ // increment counter
            pxIdx++ // increment pixel index

            // **exit loop if we're at edge of bitmap or color area
            if (rFillLoc >= width || pixelsChecked!![pxIdx] || !CheckPixel(pxIdx)) {
                break
            }
        }

        rFillLoc--

        // add range to queue
        val floodFillRange = FloodFillRangeModel(lFillLoc, rFillLoc, y)

        rangeModels!!.offer(floodFillRange)
    }

    // Sees if a pixel is within the color tolerance range.
    protected fun CheckPixel(px: Int): Boolean {
        val red = pixels!![px].ushr(16) and 0xff
        val green = pixels!![px].ushr(8) and 0xff
        val blue = pixels!![px] and 0xff

        return red >= sourceColors[0] - tolerance[0]
                && red <= sourceColors[0] + tolerance[0]
                && green >= sourceColors[1] - tolerance[1]
                && green <= sourceColors[1] + tolerance[1]
                && blue >= sourceColors[2] - tolerance[2] && blue <= sourceColors[2] + tolerance[2]
    }
}