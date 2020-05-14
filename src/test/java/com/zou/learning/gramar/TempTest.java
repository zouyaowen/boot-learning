package com.zou.learning.gramar;

import org.junit.Test;

/**
 * @author zou
 * @date 2020-03-03 12:28 上午
 */
public class TempTest {
    @Test
    public void testSubStr() {
        String doctorInfo = "JIH-ASD-asdf";
        System.out.println(doctorInfo.substring(0, doctorInfo.indexOf("-")));
        System.out.println(doctorInfo.substring(doctorInfo.indexOf("-") + 1, doctorInfo.lastIndexOf("-")));
        System.out.println(doctorInfo.substring(doctorInfo.lastIndexOf("-") + 1));
    }
}
