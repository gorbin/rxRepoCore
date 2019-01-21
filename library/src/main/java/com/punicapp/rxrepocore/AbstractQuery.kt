package com.punicapp.rxrepocore

import java.util.ArrayList

abstract class AbstractQuery<out T> {

    protected var filters: MutableList<LocalFilter> = ArrayList()
    protected var sorting: MutableList<LocalSort> = ArrayList()

    fun filter(data: List<LocalFilter>) {
        filters.addAll(data)
    }

    fun sort(data: List<LocalSort>) {
        sorting.addAll(data)
    }

    abstract fun find(): List<T>

    abstract fun first(): T?

    abstract fun count(): Long

    abstract fun remove(): Int
}
