package com.example.picasso;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso;
import java.util.Random;

public class SampleWidgetProvider extends AppWidgetProvider {

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.sample_widget);

    // Load image for all appWidgetIds.
    Picasso.with(context) //
        .load(Data.URLS[new Random().nextInt(Data.URLS.length)]) //
        .placeholder(R.drawable.placeholder) //
        .error(R.drawable.error) //
        .into(updateViews, appWidgetIds, R.id.image);
  }
}
