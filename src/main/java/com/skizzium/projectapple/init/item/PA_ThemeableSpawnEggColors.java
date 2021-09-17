package com.skizzium.projectapple.init.item;

import com.skizzium.projectapple.ProjectApple;

public class PA_ThemeableSpawnEggColors {
    private static boolean isSpooktober = ProjectApple.holiday == 1;

    public static int SKIZZIE_PRIMARY = isSpooktober ? 0XF89520 : 0XB40A1A;
    public static int SKIZZO_PRIMARY = isSpooktober ? 0XFF6A00 : 0XFF0000;

    public static int SKIZZIE_SECONDARY = isSpooktober ? 0XE65B00 : 0X9A080F;
    public static int KA_BOOM_SKIZZIE_SECONDARY = isSpooktober ? 0XFEF364 : 0X9A080F;
}
