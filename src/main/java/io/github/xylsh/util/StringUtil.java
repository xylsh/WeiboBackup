package io.github.xylsh.util;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by apple on 15-4-6.
 */
public class StringUtil {

    private static final Splitter spaceSplitter = Splitter.on(" ").omitEmptyStrings().trimResults();
    private static final Splitter colonSplitter = Splitter.on(":").omitEmptyStrings().trimResults();

    private static ImmutableMap<String, Integer> monthMap;

    static {
        //Jan, Feb, Mar, Apr, May, Jun,Jul, Aug, Sep, Oct, Nov, Dec
        monthMap = ImmutableMap.<String, Integer>builder()
                .put("Jan", 0).put("Feb", 1).put("Mar", 2).put("Apr", 3)
                .put("May", 4).put("Jun", 5).put("Jul", 6).put("Aug", 7)
                .put("Sep", 8).put("Oct", 9).put("Nov", 10).put("Dec", 11)
                .build();
    }

    //"Mon Apr 06 18:57:59 +0800 2015"
    public static Date toDate(String str) {
        checkArgument(StringUtils.isNotBlank(str));

        List<String> valList = spaceSplitter.splitToList(str);
        checkArgument(valList.size() == 6);

        List<String> timeValList = colonSplitter.splitToList(valList.get(3));
        checkArgument(timeValList.size() == 3);

        int year = Integer.parseInt(valList.get(5));
        int month = monthMap.get(valList.get(1)) + 1;
        int day = Integer.parseInt(valList.get(2));
        int hour = Integer.parseInt(timeValList.get(0));
        int minute = Integer.parseInt(timeValList.get(1));
        int seconds = Integer.parseInt(timeValList.get(2));

        DateTime dateTime = new DateTime(year, month, day, hour, minute, seconds);
        return dateTime.toDate();
    }

    public static String resourceNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    //在文件名后加"_[now]".
    public static File renameFile(File saveFile) {
        String oldPath = saveFile.getAbsolutePath();
        int dotIndex = oldPath.lastIndexOf(".");
        long now = new Date().getTime();
        String newPath = oldPath.substring(0, dotIndex) + "_" + now + oldPath.substring(dotIndex);

        return new File(newPath);
    }

    public static String generateErrorTip(BindingResult bindingResult){
        if( bindingResult == null || !bindingResult.hasErrors() ){
            return "";
        }

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errmsg = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errmsg.append(String.format("%s\n", fieldError.getDefaultMessage()));
        }

        return errmsg.toString();
    }
}
