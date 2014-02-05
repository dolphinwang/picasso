/*
 * Copyright (C) 2014 Square, Inc.
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
package com.squareup.picasso;

import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

class RemoteViewAction extends Action<Void> {
  private final RemoteViews remoteViews;
  private final int[] appWidgetIds;
  private final int viewId;

  RemoteViewAction(Picasso picasso, Request data, RemoteViews remoteViews, int[] appWidgetIds,
      int viewId, int errorResId, boolean skipCache, String key) {
    super(picasso, null, data, skipCache, false, errorResId, null, key);
    this.appWidgetIds = appWidgetIds;
    this.remoteViews = remoteViews;
    this.viewId = viewId;
  }

  @Override void complete(Bitmap result, Picasso.LoadedFrom from) {
    remoteViews.setImageViewBitmap(viewId, result);
    update();
  }

  @Override public void error() {
    if (errorResId != 0) {
      remoteViews.setImageViewResource(viewId, errorResId);
      update();
    }
  }

  private void update() {
    AppWidgetManager manager = AppWidgetManager.getInstance(picasso.context);
    manager.updateAppWidget(appWidgetIds, remoteViews);
  }
}
