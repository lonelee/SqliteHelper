package com.lonelee.sqlitehelper.util;

import com.sun.istack.internal.Nullable;

/**
 * Created by Administrator on 2015/11/24.
 */
public class StringUtil {

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

}
