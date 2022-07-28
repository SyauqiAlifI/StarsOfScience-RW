package com.raywenderlich.android.starsofscience

import android.graphics.*
import androidx.annotation.ColorInt
import com.raywenderlich.android.starsofscience.utils.*

class ProfileCardPainter(
    @ColorInt private val color: Int,
    private val avatarRadius: Float,
    private val avatarMargin: Float
) : Painter {
    override fun paint(canvas: Canvas) {
        /** Canvas width and height **/
        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()

        /** shapeBounds is a RectF with a size that fits the whole area
         *  of the canvas by using the factory function "fromLTWH()" **/
        val shapeBounds = RectFFactory.fromLTWH(0f, 0f, width, height - avatarRadius)

        /** Paint color **/
        val paint = Paint()
        paint.color = color

        //  canvas.drawRect(shapeBounds, paint)

        val centerAvatar = PointF(shapeBounds.centerX(), shapeBounds.bottom)
        val avatarBounds = RectFFactory.fromCircle(centerAvatar, avatarRadius).inflate(avatarMargin)
        drawBackground(canvas, shapeBounds, avatarBounds)

        /** Finalizing the curve **/
        val curvedShapeBounds = RectFFactory.fromLTRB(
            shapeBounds.left,
            shapeBounds.top + shapeBounds.height() * 0.35f,
            shapeBounds.right,
            shapeBounds.bottom
        )
        drawCurvedShape(canvas, curvedShapeBounds, avatarBounds)
    }

    private fun drawBackground(canvas: Canvas, bounds: RectF, avatarBounds: RectF) {
        val paint = Paint()
        paint.color = color

        val backgroundPath = Path().apply {
            moveTo(bounds.left, bounds.top)
            lineTo(bounds.bottomLeft.x, bounds.bottomLeft.y)
            lineTo(avatarBounds.centerLeft.x, avatarBounds.centerLeft.y)
            arcTo(avatarBounds, -180f, 180f, false)
            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)
            lineTo(bounds.topRight.x, bounds.topRight.y)
            close()
        }

        canvas.drawPath(backgroundPath, paint);
    }

    private fun drawCurvedShape(canvas: Canvas, bounds: RectF, avatarBounds: RectF) {

        val paint = Paint()
        // paint.color = color.darkerShade()
        paint.shader = createGradient(bounds)

        // P6
        val handlePoint = PointF(bounds.left + (bounds.width() * 0.25f), bounds.top)

        val curvePath = Path().apply {
            moveTo(bounds.bottomLeft.x, bounds.bottomLeft.y)                // P1
            lineTo(avatarBounds.centerLeft.x, avatarBounds.centerLeft.y)    // straight line that starts from P1 and ends at P2

            // P2, add an arc in the upper-half region of the avatar bounds, ending in P3.
            arcTo(avatarBounds, -180f, 180f, false)

            lineTo(bounds.bottomRight.x, bounds.bottomRight.y)  // This adds a line from P3 to P4.
            lineTo(bounds.topRight.x, bounds.topRight.y)        // adding a line from P4 to P5.

            // You add a quadratic Bézier curve starts from P5, ends at the bottom-left corner, P1.
            quadTo(handlePoint.x, handlePoint.y, bounds.bottomLeft.x, bounds.bottomLeft.y)

            // close the path, even though it's not required this time since you are back at the beginning point on the path.
            close()
        }

        canvas.drawPath(curvePath, paint)
    }

    /** Adding Gradient Paint **/
    private fun createGradient(bounds: RectF): LinearGradient {
        /** create a list of three colors,
         *  where the middle color is the profile color and the first,
         *  for the last colors are darker shades of that profile color. */
        val colors = intArrayOf(color.darkerShade(), color, color.darkerShade())

        /** create a list of three stops. The first is 0.0
         *  the middle and the stops specify the positions of their
         *  corresponding colors in the color list. */
        val stops = floatArrayOf(0.0f, 0.3f, 1.0f)

        /** Finally, create a linear gradient by passing the start coordinates
         *  and the end coordinates of the gradient,
         *  and the shader "TileMode" to repeat the gradient in case
         *  the area which you fill is larger than the shader you created. */
        return LinearGradient(
            bounds.centerLeft.x, bounds.centerLeft.y,
            bounds.centerRight.x, bounds.centerRight.y,
            colors,
            stops,
            Shader.TileMode.REPEAT
        )
    }
}