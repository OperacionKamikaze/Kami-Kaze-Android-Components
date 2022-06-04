/*
 * Copyright (c) 2022. Operación Kami - Kaze.
 *
 * Licensed under the GNU General Public License v3.0
 *
 * https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Permissions of this strong copyleft license are conditioned on making available complete
 * source code of licensed works and modifications, which include larger works using a licensed
 * work, under the same license. Copyright and license notices must be preserved. Contributors
 * provide an express grant of patent rights.
 */

package es.kamikaze.components.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.jetbrains.annotations.NotNull;

public class ImageLoadingUtil {

    private static String imageUrl = "";

    private ImageLoadingUtil() {

    }

    public static void setImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        setImage(context, url, -1, -1, imageView);
    }

    public static void setImage(@NonNull Context context, @NonNull String url, int errorImage, int placeHolder, @NonNull ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            imageUrl = url;
        } else {
            if (errorImage != -1)
                imageView.setImageResource(errorImage);
            return;
        }

        if (!isValidContextForGlide(context)) {
            return;
        }

        if (placeHolder == -1 && errorImage == -1) {
            Glide.with(context)
                    .load(imageUrl)
                    .transform(new CenterInside(), new RoundedCorners(8))
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);
        } else if (placeHolder == -1) {
            Glide.with(context)
                    .load(imageUrl)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .error(errorImage)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(imageUrl)
                    .error(errorImage)
                    .placeholder(placeHolder)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(imageView);
        }
    }

    private static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }

        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            return !activity.isDestroyed() && !activity.isFinishing();
        }
        return true;
    }

    public static void setImage(@NonNull Context context, @NonNull String url, int errorImage, @NonNull ImageView imageView) {
        setImage(context, url, errorImage, -1, imageView);
    }

    public static void setImageRoundCorner(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            imageUrl = url;
        }

        Glide.with(context)
                .load(imageUrl)
                .transform(new CenterInside(), new RoundedCorners(8))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(imageView);
    }

    public static void setMenuItemIcon(@NotNull final Context context, @NotNull String iconUrl, final MenuItem menuItem) {
        imageUrl = iconUrl;
        Glide.with(context).load(imageUrl).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                menuItem.setIcon(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                // No hace nada
            }
        });

    }

    public static Bitmap downloadImgBitmapFromUrl(Context context, String imgUrl) {
        try {
            return Glide.with(context)
                    .asBitmap()
                    .load(imgUrl)
                    .submit().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}