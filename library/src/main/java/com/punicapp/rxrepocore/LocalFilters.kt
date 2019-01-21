package com.punicapp.rxrepocore

import java.util.ArrayList

class LocalFilters {

    var filters: MutableList<LocalFilter>? = null

    fun addFilter(filter: LocalFilter): LocalFilters {
        if (filters == null) filters = ArrayList()
        filters!!.add(filter)
        return this
    }
}
