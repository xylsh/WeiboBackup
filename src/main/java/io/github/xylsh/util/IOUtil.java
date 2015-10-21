package io.github.xylsh.util;

import java.io.Closeable;

/**
 * Created by apple on 15-3-22.
 */
public class IOUtil {

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }

}
