package io.github.xylsh.common;

import com.google.common.io.LineProcessor;

/**
 * Created by apple on 15-4-10.
 */
public interface IProcessor<F,T> {

    T process(F from);

}
