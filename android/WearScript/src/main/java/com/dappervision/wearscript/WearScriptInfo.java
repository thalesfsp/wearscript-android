/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dappervision.wearscript;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Represents a launchable application. An application is made of a name (or title), an intent
 * and an icon.
 */
public class WearScriptInfo {
    public static final String WS_PKG = "com.dappervision.wearscript";
    public static final String WS_ACTIVITY = "com.dappervision.wearscript.ui.MainActivity";
    private static final String EXTRA_NAME = "extra";
    /**
     * The application name.
     */
    private CharSequence title;

    /**
     * The intent used to start the application.
     */
    private Intent intent;

    /**
     * The application icon.
     */
    private Drawable icon;

    /**
     * When set to true, indicates that the icon has been resized.
     */
    boolean filtered;

    public WearScriptInfo(String title){
        this.title = title;
    }

    public WearScriptInfo(String title, String filePath) {
        this.title = title;
        setActivity(new ComponentName(WS_PKG, WS_ACTIVITY),
                Intent.FLAG_ACTIVITY_CLEAR_TOP, filePath);
    }

    /**
     * Creates the application intent based on a component name and various launch flags.
     *
     * @param className   the class name of the component representing the intent
     * @param launchFlags the launch flags
     */
    public final void setActivity(ComponentName className, int launchFlags, String file) {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(className);
        intent.setFlags(launchFlags);
        intent.putExtra(EXTRA_NAME, file);
    }

    /**
     * Creates the application intent based on a component name and various launch flags.
     *
     * @param className   the class name of the component representing the intent
     * @param launchFlags the launch flags
     */
    final void setActivity(ComponentName className, int launchFlags) {
        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(className);
        intent.setFlags(launchFlags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationInfo)) {
            return false;
        }

        ApplicationInfo that = (ApplicationInfo) o;
        return title.equals(that.title) &&
                intent.getComponent().getClassName().equals(
                        that.intent.getComponent().getClassName());
    }

    @Override
    public int hashCode() {
        int result;
        result = (title != null ? title.hashCode() : 0);
        final String name = intent.getComponent().getClassName();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static WearScriptInfo playground() {
        WearScriptInfo wsi = new WearScriptInfo("Playground");
        wsi.setActivity(new ComponentName(WS_PKG, WS_ACTIVITY), Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return wsi;
    }

    public Intent getIntent() {
        return intent;
    }

    public CharSequence getTitle() {
        return title;
    }
}
