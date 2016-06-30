package org.jire.resourceful

import java.util.concurrent.TimeUnit

abstract class Resource<T>(val defaultUpdateDuration: Long,
                           val defaultUpdateDurationUnit: TimeUnit = TimeUnit.MILLISECONDS,
                           val initialize: () -> T) {

	protected abstract fun value(): T

	protected abstract fun updateValue(newValue: T): T

	protected abstract fun lastUpdate(): Long

	operator fun invoke() = value()

	operator fun unaryPlus() = updateValue(initialize())

	operator fun invoke(duration: Long, durationUnit: TimeUnit = TimeUnit.MILLISECONDS): T {
		val elapsed = System.nanoTime() - lastUpdate()
		val durationNanos = durationUnit.toNanos(duration)
		if (elapsed >= durationNanos) return +this
		return this()
	}

	operator fun unaryMinus() = this(defaultUpdateDuration, defaultUpdateDurationUnit)

}