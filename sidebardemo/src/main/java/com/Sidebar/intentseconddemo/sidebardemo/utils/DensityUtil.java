package com.Sidebar.intentseconddemo.sidebardemo.utils;

import android.content.Context;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Administrator on 2016/6/20.
 */
public class DensityUtil {
    public static int sp2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
        return (int) (var1 * var2 + 0.5F);
    }
    /**
     * 汉字转换为汉语拼音首字母
     * 花花大神->hhds
     * @param chinese
     *            汉字
     * @return 拼音
     * 07
     */
    public static String getFirstSpell(String chinese) {

        StringBuffer pybf = new StringBuffer();

        char[] arr = chinese.toCharArray();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (char curchar : arr) {

            if (curchar > 128) {

                try {

                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curchar, defaultFormat);

                    if (temp != null) {

                        pybf.append(temp[0].charAt(0));

                    }

                } catch (BadHanyuPinyinOutputFormatCombination e) {

                    e.printStackTrace();

                }

            } else {

                pybf.append(curchar);

            }

        }

        return pybf.toString().replaceAll("\\W", "").trim();

    }
    /**
     * 获取拼音
     * 英文 返回不变
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();
        String output = "";
        try {
            for (char curchar : input) {
                if (java.lang.Character.toString(curchar).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curchar, format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(curchar);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return getFirstA(output);
    }

    /**
     * 返回字符串的首字母大写
     * @param s
     * @return
     */
    public static String getFirstA(String s) {

        char[] sh = s.toCharArray();

        return (sh[0] + "").toUpperCase();
    }
}
