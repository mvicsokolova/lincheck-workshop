import java.util.concurrent.ConcurrentHashMap

class MultiMap<K, V> {
    private val map = ConcurrentHashMap<K, List<V>>()

    fun addBroken(key: K, value: V) {
        val list = map[key]
        if (list == null) {
            map[key] = listOf(value)
        } else {
            map[key] = list + value
        }
    }

    fun add(key: K, value: V) {
        map.compute(key) { _, list ->
            if (list == null) listOf(value) else list + value
        }
    }

    fun get(key: K): List<V> = map[key] ?: emptyList()
}