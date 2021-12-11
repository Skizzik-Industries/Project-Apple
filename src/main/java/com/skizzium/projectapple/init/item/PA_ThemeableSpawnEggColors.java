package com.skizzium.projectapple.init.item;

import com.skizzium.projectapple.ProjectApple;

public class PA_ThemeableSpawnEggColors {
    private static boolean isSpooktober = ProjectApple.holiday == 1;

    public class Primary {
        public static int SKIZZIE = isSpooktober ? 0XF89520 : 0XB40A1A;
        public static int SKIZZO_PRIMARY = isSpooktober ? 0XFF6A00 : 0XFF0000;
    }

    public class Secondary {
        public static int SKIZZIE = isSpooktober ? 0XE65B00 : 0X9A080F;
        public static int KA_BOOM_SKIZZIE = isSpooktober ? 0XFEF364 : 0X9A080F;
        public static int SKIZZO = isSpooktober ? 0X331400 : 0X330000;
    }
}
