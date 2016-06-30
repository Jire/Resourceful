package org.jire.resourceful

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

internal class ThreadSafeResource<T>(defaultUpdateDuration: Long,
                                     defaultUpdateDurationUnit: TimeUnit = TimeUnit.MILLISECONDS,
                                     initialize: () -> T)
: Resource<T>(defaultUpdateDuration, defaultUpdateDurationUnit, initialize) {

	private val value = AtomicReference<T>()
	private val lastUpdate = AtomicLong()

	override fun value() = value.get() ?: +this

	override fun updateValue(newValue: T): T {
		value.set(newValue)
		lastUpdate.set(System.nanoTime())
		return newValue
	}

	override fun lastUpdate() = lastUpdate.get()

}