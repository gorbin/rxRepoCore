package com.punicapp.rxrepocore

import com.google.common.base.Optional
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.lang.reflect.Type


interface IKeyValueRepository<K> {
    fun <V> save(key: K, value: V): Single<V> = Single.just(value).doOnSuccess(saveInChain(key))

    fun <V> instantSave(key: K, value: V): V = save(key, value).blockingGet()

    fun <V> saveInChain(key: K): Consumer<V>

    fun remove(key: K): Single<K> = Single.just<K>(key).doOnSuccess(removeInChain())

    fun instantRemove(key: K): K = remove(key).blockingGet()

    fun removeInChain(): Consumer<K>

    operator fun <V> get(key: K, t: Type): Single<Optional<V>> = Single.just<K>(key).map(getInChain(t))

    fun <V> instantGet(key: K, t: Type): Optional<V> = get<V>(key, t).blockingGet()

    fun <V> getInChain(t: Type): Function<K, Optional<V>>

    fun has(key: K): Single<Boolean> = Single.just<K>(key).map(hasInChain(key))

    fun instantHas(key: K): Boolean = has(key).blockingGet()

    fun hasInChain(key: K): Function<K, Boolean>

    fun clear(): Single<Boolean>
}
