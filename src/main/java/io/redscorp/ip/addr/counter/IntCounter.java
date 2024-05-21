package io.redscorp.ip.addr.counter;

/**
 * The int counter.
 *
 * @author <a href="mailto:gennady.abakyan@gmail.com">Gennady Abakyan</a>
 */
public class IntCounter {
    private static final long[] MASKS;

    private final long[][][][] keyPresences = new long[256][256][256][4];

    private volatile long count;

    /*
     * The static initialization block.
     */
    static {
        MASKS = new long[64];

        for (int i = 0; i < 64; i++) {
            MASKS[i] = 1L << i;
        }
    }

    public long getCount() {
        return count;
    }

    /**
     * The method to count.
     *
     * @param key the key
     */
    public void count(int key) {
        int keyPart1 = key >>> 24;
        int keyPart2 = (key >>> 16) & 0x000000ff;
        int keyPart3 = (key >>> 8) & 0x000000ff;
        int keyPart4 = key & 0x000000ff;

        int index1 = keyPart4 / 64;
        int index2 = keyPart4 % 64;

        synchronized (keyPresences[keyPart1][keyPart2][keyPart3]) {
            long[] keyPresences0 = keyPresences[keyPart1][keyPart2][keyPart3];

            long keyPresences0x = keyPresences0[index1];

            if (isPresent(keyPresences0x, index2)) {
                keyPresences0[index1] = setPresent(keyPresences0x, index2);

                count++;
            }
        }
    }

    private static boolean isPresent(long keyPresences, int index) {
        return (keyPresences & MASKS[index]) == MASKS[index];
    }

    private static long setPresent(long keyPresences, int index) {
        return keyPresences | MASKS[index];
    }
}
