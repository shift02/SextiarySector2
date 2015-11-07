package shift.sextiarysector;

import shift.sextiarysector.agriculture.CropManager;
import shift.sextiarysector.agriculture.CropTest;
import shift.sextiarysector.api.agriculture.AgricultureAPI;
import shift.sextiarysector.api.agriculture.ICrop;

public class SSCrops {

    public static CropManager cropManager;

    public static ICrop test;

    public static void preInit() {

        cropManager = new CropManager();
        AgricultureAPI.cropManager = cropManager;

    }

    public static void initCrops() {

        test = new CropTest();
        cropManager.registerCrop(test);

    }

}
