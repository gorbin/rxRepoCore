package com.punicapp.rxrepocore

import com.google.common.base.Optional

import io.reactivex.Single
import io.reactivex.functions.Consumer

interface IRepository<T> {

    fun save(item: T, clearData: Boolean): Single<T> {
        return Single.just(item).doOnSuccess(saveInChain())
    }

    fun saveAll(items: List<T>, clearData: Boolean): Single<List<T>> {
        return Single.just(items).doOnSuccess(saveAllInChain())
    }

    fun saveInChain(clearData: Boolean): Consumer<T>

    fun saveAllInChain(clearData: Boolean): Consumer<List<T>>

    fun save(item: T): Single<T> = save(item, true)

    fun saveAll(items: List<T>): Single<List<T>> = saveAll(items, true)

    fun saveInChain(): Consumer<T> = saveInChain(true)

    fun saveAllInChain(): Consumer<List<T>> = saveAllInChain(true)

    fun modifyFirst(action: Consumer<T>): Single<T>

    fun removeInChain(filters: LocalFilters?): Single<Int>

    fun fetch(filters: LocalFilters?, sorts: LocalSorts?): Single<Optional<List<T>>>

    fun first(filters: LocalFilters?, sorts: LocalSorts?): Single<Optional<T>>

    fun instantFetch(filters: LocalFilters?, sorts: LocalSorts?): Optional<List<T>> {
        return fetch(filters, sorts).blockingGet()
    }

    fun instantFirst(filters: LocalFilters?, sorts: LocalSorts?): Optional<T> {
        return first(filters, sorts).blockingGet()
    }

    fun count(filters: LocalFilters?): Single<Long>

    fun instantCount(filters: LocalFilters?): Long? {
        return count(filters).blockingGet()
    }
}
