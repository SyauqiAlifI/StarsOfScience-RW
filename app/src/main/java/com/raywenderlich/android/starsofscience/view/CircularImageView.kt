/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.starsofscience.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircularImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

  private var viewWidth: Int = 0
  private var viewHeight: Int = 0
  private var paint = Paint()

  @SuppressLint("DrawAllocation")
  public override fun onDraw(canvas: Canvas) {

    val bitmapDrawable = drawable as? BitmapDrawable
    bitmapDrawable?.bitmap?.let {
      paint.shader = BitmapShader(
          Bitmap.createScaledBitmap(it, width, height, false),
          Shader.TileMode.CLAMP,
          Shader.TileMode.CLAMP
      )
      val circleCenter = viewWidth / 2
      canvas.drawCircle(
          circleCenter.toFloat(),
          circleCenter.toFloat(),
          circleCenter.toFloat(),
          paint
      )
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    viewWidth = measureWidth(widthMeasureSpec)
    viewHeight = measureHeight(heightMeasureSpec)
    setMeasuredDimension(viewWidth, viewHeight)
  }

  private fun measureWidth(measureSpec: Int): Int {
    val specMode = MeasureSpec.getMode(measureSpec)
    val specSize = MeasureSpec.getSize(measureSpec)
    return if (specMode == MeasureSpec.EXACTLY) {
      specSize
    } else {
      viewWidth
    }
  }

  private fun measureHeight(measureSpecHeight: Int): Int {
    val specMode = MeasureSpec.getMode(measureSpecHeight)
    val specSize = MeasureSpec.getSize(measureSpecHeight)
    return if (specMode == MeasureSpec.EXACTLY) {
      specSize
    } else {
      viewHeight
    }
  }
}
