package com.yuxi.scratchcard

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ScratchCard:View
   {
       constructor(context: Context?):this(context,null,0,0)
       constructor(context: Context?,attrs: AttributeSet?):this(context,attrs,0)
       constructor(context: Context?,attrs: AttributeSet?, defStyleAttr:Int):this(context,attrs,defStyleAttr,0)
        lateinit var paint:Paint
       lateinit var mPath:Path
       lateinit var mBgBitmap:Bitmap
       lateinit var mFgBitmap:Bitmap
       lateinit var mCanvas :Canvas
       @TargetApi(Build.VERSION_CODES.LOLLIPOP)
       constructor(context: Context?, attrs: AttributeSet?, defStyleAttr:Int, defStyleRes:Int):super(context,attrs,defStyleAttr,defStyleRes){
            paint = Paint()
           paint.alpha = 0
           paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
            paint.style=Paint.Style.STROKE
           paint.strokeJoin = Paint.Join.ROUND
           paint.strokeWidth = 50f
           paint.strokeCap=Paint.Cap.ROUND
           mPath = Path()
           mBgBitmap = BitmapFactory.decodeResource(resources,R.mipmap.invite)
           mFgBitmap = Bitmap.createBitmap(mBgBitmap.width,mBgBitmap.height,Bitmap.Config.ARGB_8888)
           mCanvas = Canvas(mFgBitmap)
           mCanvas.drawColor(Color.GRAY)



       }

       override fun onTouchEvent(event: MotionEvent?): Boolean {

           when(event?.action){
               MotionEvent.ACTION_DOWN->{
                   mPath.reset()
                   mPath.moveTo(event.x,event.y)
               }
               MotionEvent.ACTION_MOVE->{
                   mPath.lineTo(event.x,event.y)
               }
           }
           mCanvas.drawPath(mPath,paint)
           invalidate()
           return true
       }

       override fun onDraw(canvas: Canvas?) {
           super.onDraw(canvas)
           canvas?.drawBitmap(mBgBitmap,0f,0f,null)
           canvas?.drawBitmap(mFgBitmap,0f,0f,null)
       }

}