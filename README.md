# Android View Controller

An Android library for cycling through views using animations. The library provides 2 animations as built it animations, a flip animation and a fade animation.

Examples of how to use the library have been provided in the example module, you can build and run the app to see it in action.

##Usage

First thing you need is to prepare the views you want to be included in the controller. You can do this by inflating them in Java using the LayoutInflator class or just placing them in the xml. See below for an example xml the activity_image_view_sample.xml from the example module in the project.

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".samples.ImageViewSampleActivity">

    <ImageView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/apples_imageview"
        android:src="@drawable/apples"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />

    <ImageView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/banana_imageview"
        android:src="@drawable/banana"
        android:visibility="gone"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />

    <ImageView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/grapes_imageview"
        android:src="@drawable/grapes"
        android:visibility="gone"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />

    <ImageView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/oranges_imageview"
        android:src="@drawable/oranges"
        android:visibility="gone"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="@string/fruits"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />
</RelativeLayout>
```


