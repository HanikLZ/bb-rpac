package org.mdvsc.rapc.transform

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class RIMClassRewriter(classVisitor: ClassVisitor): ClassVisitor(Opcodes.ASM9, classVisitor) {

    companion object {
        const val REWRITE_PACKAGE = "org/mdvsc/rapc/ext"
        const val REWRITE_CLASS_STRING_KT = "$REWRITE_PACKAGE/StringKt"
        const val REWRITE_CLASS_COLLECTIONS_KT = "$REWRITE_PACKAGE/CollectionsKt"
        const val REWRITE_CLASS_ARRAYS_KT = "$REWRITE_PACKAGE/ArraysKt"
        const val REWRITE_CLASS_ARRAYS = "$REWRITE_PACKAGE/Arrays"
        const val REWRITE_CLASS_COLLECTION = "$REWRITE_PACKAGE/Collection"
        const val REWRITE_CLASS_COLLECTIONS = "$REWRITE_PACKAGE/Collections"
        const val REWRITE_CLASS_COLLECTION_ENUMERATION = "$REWRITE_PACKAGE/CollectionEnumeration"
        const val REWRITE_CLASS_LIST = "$REWRITE_PACKAGE/List"
        const val REWRITE_CLASS_LIST_ITR = "$REWRITE_PACKAGE/ListItr"
        const val REWRITE_CLASS_ITR = "$REWRITE_PACKAGE/Itr"
        const val REWRITE_CLASS_SINGLETON_LIST = "$REWRITE_PACKAGE/SingletonList"
        const val REWRITE_CLASS_SUB_ARRAY_LIST = "$REWRITE_PACKAGE/SubArrayList"
        const val REWRITE_CLASS_SUB_LIST = "$REWRITE_PACKAGE/SubList"
        const val REWRITE_CLASS_ABSTRACT_COLLECTION = "$REWRITE_PACKAGE/AbstractCollection"
        const val REWRITE_CLASS_ABSTRACT_LIST = "$REWRITE_PACKAGE/AbstractList"
        const val REWRITE_CLASS_ITERATOR = "$REWRITE_PACKAGE/Iterator"
        const val REWRITE_CLASS_ITERABLE = "$REWRITE_PACKAGE/Iterable"
        const val REWRITE_CLASS_LIST_ITERATOR = "$REWRITE_PACKAGE/ListIterator"
        const val REWRITE_CLASS_CONCURRENT_MODIFICATION_EXCEPTION = "$REWRITE_PACKAGE/ConcurrentModificationException"
        const val REWRITE_CLASS_INDEX_OUT_OF_BOUNDS_EXCEPTION = "$REWRITE_PACKAGE/IndexOutOfBoundsException"
        const val REWRITE_CLASS_ARRAY_LIST = "$REWRITE_PACKAGE/ArrayList"
        const val REWRITE_CLASS_ARRAY_LIST_ITR = "$REWRITE_PACKAGE/ArrayListItr"
        const val REWRITE_CLASS_RANDOM_ACCESS = "$REWRITE_PACKAGE/RandomAccess"
        const val REWRITE_CLASS_COMPARATOR = "$REWRITE_PACKAGE/Comparator"
    }

    override fun visitMethod(
        access: Int, name: String?, descriptor: String?,
        signature: String?, exceptions: Array<out String>?
    ): MethodVisitor = object : AdviceAdapter(api, super.visitMethod(access, name, descriptor, signature, exceptions), access, name, descriptor) {

        override fun visitFieldInsn(opcode: Int, owner: String, fieldName: String, descriptor: String) {
            if (opcode == GETSTATIC && owner == "java/util/Locale") return
            super.visitFieldInsn(opcode, owner, fieldName, descriptor)
        }

        override fun visitMethodInsn(opcode: Int,
                                     owner: String,
                                     methodName: String,
                                     methodDescriptor: String,
                                     isInterface: Boolean) {
            var hookOwner = owner
            var hookMethodDesc = methodDescriptor
            if (opcode in INVOKEVIRTUAL..INVOKEDYNAMIC) when(owner) {
                "java/lang/String" -> if ((methodName == "toUpperCase" || methodName == "toLowerCase") && methodDescriptor == "(Ljava/util/Locale;)Ljava/lang/String;") hookMethodDesc = "()Ljava/lang/String;"
                "kotlin/text/StringsKt" -> if ((methodName == "contains"
                            || methodName == "contains\$default"
                            || methodName == "indexOf"
                            || methodName == "indexOf\$default"
                            || methodName == "lastIndexOf"
                            || methodName == "lastIndexOf\$default")) hookOwner = REWRITE_CLASS_STRING_KT
                "kotlin/collections/ArraysKt" -> if ((methodName == "joinToString"
                            || methodName == "joinToString\$default"
                            || methodName == "joinTo"
                            || methodName == "joinTo\$default"
                        )) hookOwner = REWRITE_CLASS_ARRAYS_KT
                "kotlin/collections/CollectionsKt" -> if ((methodName == "contains"
                            || methodName == "listOf"
                            || methodName == "mutableListOf"
                            || methodName == "arrayListOf"
                            || methodName == "listOfNotNull"
                            || methodName == "emptyList"
                            || methodName == "joinToString"
                            || methodName == "joinToString\$default"
                            || methodName == "joinTo"
                            || methodName == "joinTo\$default"
                        )) hookOwner = REWRITE_CLASS_COLLECTIONS_KT
            }
            super.visitMethodInsn(opcode, hookOwner, methodName, hookMethodDesc, isInterface)
        }
    }
}