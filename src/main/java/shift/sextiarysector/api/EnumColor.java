package shift.sextiarysector.api;

public enum EnumColor {

    Black(0x000000), Red(0xFF0000), Green(0x088A29), Brown(0x8A4B08), Blue(0x0000FF), Purple(0xB404AE), Cyan(0x00FFFF), LightGray(0x2EFE2E), Gray(0x848484), Pink(0xFF00FF), Lime(0x64FE2E), Yellow(0xFFFF00), LightBlue(
            0x2E9AFE), Magenta(0xFF00BF), Orange(0xFF4000), White(0xFFFFFF), Unknown(0x000000);

    public static EnumColor[] colors = {
            Black,
            Red,
            Green,
            Brown,
            Blue,
            Purple,
            Cyan,
            LightGray,
            Gray,
            Pink,
            Lime,
            Yellow,
            LightBlue,
            Magenta,
            Orange,
            White
    };

    public final int color;

    private EnumColor(int color) {
        this.color = color;
    }

    public static EnumColor getColor(int i) {

        if (0 > i || i > 15) return Unknown;

        return colors[i];

    }

    public byte[] getGLColor3ub() {
        return new byte[] { (byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF) };
    }
}