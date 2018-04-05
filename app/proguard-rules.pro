-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/removal/advanced,!code/removal/simple

-allowaccessmodification
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exception
-keepattributes SourceFile
-keepattributes LineNumberTable
-flattenpackagehierarchy ''
-renamesourcefileattribute SourceFile

-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep all public constructors of all public classes, but still obfuscate+optimize their content.
# This is necessary because optimization removes constructors which are called through XML.
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context);
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep public class * {
    public protected *;
}

#Serializable objects
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

#Google play services proguard rules:
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

#Timber
-dontwarn org.jetbrains.annotations.**

#Glide 4
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule

-keepclassmembers class android.support.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}

#Support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v4.app.Fragment

#Support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-keep class android.support.v7.widget.RoundRectDrawable { *; }

#Support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keep class android.net.http.** { *; }
-keep class android.**
-keep class com.google.android.gms.**
-keep class com.google.analytics.** { *; }
-keep class com.google.firebase.**
-keep class com.google.android.gms.** { *; }
-keep class com.mikepenz.materialize.view.**
-keep public class android.support.v4.** { *; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.v8.renderscript.** { *; }

-dontwarn android.net.http.**
-dontwarn android.**
-dontnote com.android.vending.licensing.ILicensingService
-dontwarn com.google.android.gms.**
-dontwarn com.google.common.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.errorprone.annotations.**
-dontwarn org.apache.http.**

#Turn off Logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
}

## LIBRARY: Retrofit
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn rx.**
-dontwarn retrofit.**
-dontwarn com.google.appengine.**

## LIBRARY: GSON
-keep class com.google.gson.** { *; }
-keep class com.google.inject.** { *; }
-dontwarn com.google.gson.**

-keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
}

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

# PagerSlidingTabStrip classes
-keep class com.astuetz.** {*;}

## LIBRARY: OkHttp
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**

## ButterKnife
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

## Crashlytics
-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**
-keep public class * extends java.lang.Exception

## Required for the Javascript Interface
-keepclassmembers class com.urbanairship.js.UAJavascriptInterface {
   public *;
}

## SQLCipher
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable

-keep class net.sqlcipher.** { *; }

-keep class net.sqlcipher.database.** { *; }
-dontwarn net.sqlcipher.**
-keepattributes EnclosingMethod

# Widespace
-keep class com.widespace.internal.interfaces.** {
   *;
}

-dontwarn com.widespace.adspace.helpers.AttributesParseHelper