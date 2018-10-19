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
-repackageclasses

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

