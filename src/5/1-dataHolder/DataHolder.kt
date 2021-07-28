class DataHolder {
    // The data is split into two 64-bit fields
    private var first: Long = 0
    private var second: Long = 0

    @Volatile
    private var version = 0 // mod 2 == 0 -> READY TO READ
    // mod 2 == 1 -> WRITING IN PROGRESS

    // Should not be run in parallel
    fun write(newFirst: Long, newSecond: Long) {
        version++ // lock the holder, readers wait until it is unlocked
        first = newFirst // write the 1st part of the data
        second = newSecond // write the 2nd part of the data
        version++ // release the holder for reads
    }

    fun read(): Pair<Long, Long> {
        while(true) {
            val curVersion = version
            // Is there a concurrent `write`?
            if (curVersion % 2 == 1) continue
            // Read the data in a non-atomic way
            val first = first
            val second = second
            // Complete if the version is still the same
            if (curVersion == version) return first to second
        }
    }
}