class Counter {
    @Volatile
    private var value = 0

    fun inc(): Int = ++value

    fun addAndGet(delta: Int): Int {
        value += delta
        return value
    }

    fun get() = value
}