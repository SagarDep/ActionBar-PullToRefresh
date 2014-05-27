/*
 * Copyright 2013 Chris Banes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import uk.co.senab.actionbarpulltorefresh.library.DefaultHeaderTransformer;

/**
 * @see uk.co.senab.actionbarpulltorefresh.library.DefaultHeaderTransformer
 */
public class AbcDefaultHeaderTransformer extends DefaultHeaderTransformer {

    @Override
    protected Drawable getActionBarBackground(Context context) {
        // Super handles ICS+ anyway...
        if (Build.VERSION.SDK_INT >= super.getMinimumApiLevel()) {
            return super.getActionBarBackground(context);
        }

        // Get action bar style values...
        TypedArray abStyle = obtainStyledAttrsFromThemeAttr(context, R.attr.actionBarStyle,
                R.styleable.ActionBar);
        try {
            return abStyle.getDrawable(R.styleable.ActionBar_background);
        } finally {
            abStyle.recycle();
        }
    }

    @Override
    protected int getActionBarSize(Context context) {
        // Super handles ICS+ anyway...
        if (Build.VERSION.SDK_INT >= super.getMinimumApiLevel()) {
            return super.getActionBarSize(context);
        }

        int[] attrs = { R.attr.actionBarSize };
        TypedArray values = context.obtainStyledAttributes(attrs);
        try {
            return values.getDimensionPixelSize(0, 0);
        } finally {
            values.recycle();
        }
    }

    @Override
    protected int getActionBarTitleStyle(Context context) {
        // Super handles ICS+ anyway...
        if (Build.VERSION.SDK_INT >= super.getMinimumApiLevel()) {
            return super.getActionBarTitleStyle(context);
        }

        // Get action bar style values...
        TypedArray abStyle = obtainStyledAttrsFromThemeAttr(context, R.attr.actionBarStyle,
                R.styleable.ActionBar);
        try {
            return abStyle.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
        } finally {
            abStyle.recycle();
        }
    }

    @Override
    protected int getMinimumApiLevel() {
        return Build.VERSION_CODES.ECLAIR_MR1;
    }
}
