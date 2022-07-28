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

package com.raywenderlich.android.starsofscience

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.starsofscience.utils.resToPx
import com.raywenderlich.android.starsofscience.utils.toColorInt
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    /** You don’t need this line anymore
     *  because you want to add your custom shape
     *  instead of this solid color. **/
    //  profileCardContainer.setBackgroundColor(R.color.colorPrimary.toColorInt(this))

    /** define the properties of your custom shape:
     *  Color, avatar radius, avatar margin, width and height. **/
    val azureColor = R.color.colorPrimary.toColorInt(this)
    val avatarRadius = R.dimen.avatar_radius.resToPx(this)
    val avatarMargin = R.dimen.avatar_margin.resToPx(this)
    val cardWidth = ViewGroup.LayoutParams.MATCH_PARENT
    val cardHeight = R.dimen.profile_card_height.resToPx(this).toInt()

    /** create a ProfileCardPainter **/
    val painter = ProfileCardPainter(azureColor, avatarRadius, avatarMargin)

    /** Finally, add a new CustomPainter as a subview of profileCardContainer
     *  by passing all its needed properties: **/
    profileCardContainer.addView(
      CustomPainter(
        this,  /** context to create this custom Android View */
        cardWidth,    /** width of the custom shape. */
        cardHeight,   /** and height of the custom shape. */
        painter       /** painter responsible for all the drawing logic. */
      )
    )

  }
}
