package org.jire.resourceful

import java.util.concurrent.TimeUnit

fun <T> resource(defaultUpdateDuration: Long,
                 defaultUpdateDurationUnit: TimeUnit = TimeUnit.MILLISECONDS,
                 initialize: () -> T): Resource<T>
		= ThreadSafeResource(defaultUpdateDuration, defaultUpdateDurationUnit, initialize)

fun <T> resource(defaultUpdateDuration: Int,
                 defaultUpdateDurationUnit: TimeUnit = TimeUnit.MILLISECONDS,
                 initialize: () -> T)
		= resource(defaultUpdateDuration.toLong(), defaultUpdateDurationUnit, initialize)