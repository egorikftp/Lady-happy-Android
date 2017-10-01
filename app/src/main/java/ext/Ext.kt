@file:Suppress("NOTHING_TO_INLINE")

package ext

import android.graphics.drawable.Animatable
import android.widget.ImageView

inline fun ImageView.animateAvd() {
    val animatedDrawable = drawable

    if (animatedDrawable is Animatable) {
        animatedDrawable.start()
    }
}
