package com.example.myapplication;

import android.support.annotation.NonNull;

public interface MainActivity1 {
    void onRequestPermissionResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
