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

package com.raywenderlich.android.starsofscience.utils

import android.graphics.PointF
import android.graphics.RectF

object RectFFactory {
  fun fromLTRB(left: Float, top: Float, right: Float, bottom: Float) =
      RectF(left, top, right, bottom)

  fun fromLTWH(left: Float, top: Float, width: Float, height: Float) =
      fromLTRB(left, top, left + width, top + height)

  fun fromCircle(center: PointF, radius: Float) =
      fromCenter(
          center = center,
          width = radius * 2,
          height = radius * 2
      )

  fun fromCenter(center: PointF, width: Float, height: Float) =
      fromLTRB(
          center.x - width / 2,
          center.y - height / 2,
          center.x + width / 2,
          center.y + height / 2
      )
}


/**
Returns a new rectangle with edges moved outwards by the given delta.
 */
fun RectF.inflate(delta: Float): RectF {
  return RectFFactory.fromLTRB(
      left - delta,
      top - delta,
      right + delta,
      bottom + delta
  )
}

data class Size(val width: Float, val height: Float)