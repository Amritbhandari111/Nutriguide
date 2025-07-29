# --- Keep your main entry point activity ---
-keep public class com.amrit.nutriguidefoodsbyconditionnutrients.MainActivity { *; }

# --- Keep all activities just in case ---
-keep public class * extends android.app.Activity
-keep public class * extends androidx.activity.ComponentActivity

# --- Jetpack Compose ---
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# --- Navigation Component / Jetpack Compose Navigation ---
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# If you're using Composable previews or deep links, keep them too:
-keep class * {
    @androidx.compose.runtime.Composable <methods>;
}

# --- Kotlin Reflection support (for any reflection-based Compose or Kotlin code) ---
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }
-dontwarn kotlin.**

# --- Keep JSON model classes used via reflection (e.g., Gson, Moshi, kotlinx.serialization) ---
# Replace 'com.amrit.nutriguidefoodsbyconditionnutrients.model' with your actual model package
-keep class com.amrit.nutriguidefoodsbyconditionnutrients.model.** { *; }

# --- Keep class members with constructors (safe default) ---
-keepclassmembers class * {
    public <init>(...);
}

# --- Optional: Keep line numbers for stack traces ---
-keepattributes SourceFile,LineNumberTable
# Gson
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Keep your data models
-keep class com.amrit.nutriguidefoodsbyconditionnutrients.popular.FoodItemWithoutCategory { <fields>; }
-keep class com.amrit.nutriguidefoodsbyconditionnutrients.foodsscreen.FoodItem { <fields>; }

