package org.mdvsc.rapc

internal const val GROUP_RAPC = "rapc"
internal const val GROUP_MIDLET = "midlet"
internal const val PROGUARD_KEEP_MAIN = "public class * { public static final void main(java.lang.String[]); }"
internal const val PROGUARD_KEEP_MIDLET = "public class * extends javax.microedition.midlet.MIDlet"
internal const val PROGUARD_REMOVE_NULL = """
    class kotlin.jvm.internal.Intrinsics {
        public static void checkNotNull(java.lang.Object);
        public static void checkNotNull(java.lang.Object, java.lang.String);
        public static void checkExpressionValueIsNotNull(java.lang.Object, java.lang.String);
        public static void checkNotNullExpressionValue(java.lang.Object, java.lang.String);
        public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
        public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String);
        public static void checkFieldIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
        public static void checkFieldIsNotNull(java.lang.Object, java.lang.String);
        public static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
        public static void checkNotNullParameter(java.lang.Object, java.lang.String);
    }"""