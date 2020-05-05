-dontskipnonpubliclibraryclassmembers
-flattenpackagehierarchy ''
-dump class_files.txt
#-printseeds seeds.txt
#-printusage unused.txt
#-printconfiguration "build/outputs/mapping/configuration.txt"
#-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*, !class/merging/*, !code/removal/advanced, !code/removal/simple
-renamesourcefileattribute SourceFile

-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
}

-keep public class * extends android.app.Activity
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

#Timber
-dontwarn org.jetbrains.annotations.**

-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v8.renderscript.** { *; }

## Glide 4
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

## Crashlytics
-keep class com.crashlytics.** { *; }
-keep public class * extends java.lang.Exception

-dontwarn com.crashlytics.**


### Coroutines
# ServiceLoader support
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

#Temp fix
-keep class * implements androidx.viewbinding.ViewBinding {
     public static *** bind(android.view.View);
}