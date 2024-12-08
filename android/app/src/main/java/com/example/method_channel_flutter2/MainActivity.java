package com.example.method_channel_flutter2;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL_METHOD = "com.example.karthi/common";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_METHOD)
                .setMethodCallHandler(
                        (call, result) -> {
                            if(call.method.equals("getListOfApps")){
                                result.success(getListOfApps());
                                return;
                            }
                            result.notImplemented();;
                            return;
                        }
                );
    }
    private List<String> getListOfApps(){

//        PackageManager pm = getPackageManager();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        List<String> list = new ArrayList<>();
        for(PackageInfo p : packs){
            if((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
                list.add(p.applicationInfo.loadLabel(getPackageManager()).toString());
            }
        }
        return list;
////        List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(0);
//////        List<ApplicationInfo> listApplicationInfo = getPackageManager().getInstalledApplications(0);
//////        String[] stringsList = new String[listApplicationInfo.size()];
//        final List<String> list = new ArrayList<>();
//        PackageManager pm = getPackageManager();
//
////        for (PackageInfo packageInfo: packageInfoList){
//////            list.add(packageInfo.loadLabel(getPackageManager()).toString());
////            list.add(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
////        }
//        return list;
    }
}
