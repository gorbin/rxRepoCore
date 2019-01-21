package com.punicapp.rxrepocore

import java.util.ArrayList

class LocalSorts {
    var sorts: MutableList<LocalSort>? = null

    fun addSort(sort: LocalSort): LocalSorts {
        if (sorts == null) sorts = ArrayList()
        sorts!!.add(sort)
        return this
    }
}
