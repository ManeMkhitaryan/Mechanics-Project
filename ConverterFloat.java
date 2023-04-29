package com.company;

public class ConverterFloat extends Converter {
    public ConverterFloat(double t0, double t1, double dt, double x0, double v0, double m, double omega) {
        super(t0, t1, dt, x0, v0, m, omega);
    }

    public ConverterFloat() {}

    public String SpringSystem(String bits) {
        int point = bits.indexOf(".");
        String decimal_part = bits.substring(0, point);
        String fractional_part = bits.substring(point + 1);

        return "[" + SpringSystem(decimal_part, "[", "]", 0) + SpringSystem(fractional_part, "{", "}", 1) + "]";
    }

    public String SpringSystem(String bits, String open, String close, int i) {
        if (bits.length() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(open);
        String bracket = open + close;

        int j = bits.length() - 1;

        for (; i < bits.length(); i++) {
            if (bits.charAt(j--) == '1') {
                result.append(open);
            }
                while (bracket.length() != Math.pow(2, i + 1)) {
                    bracket += bracket;
                }
                result.append(bracket);
                result.append(close);
            }
        result.append(close);
        return result.toString();
    }

    public int BinaryToDecimal(String binary) {
        return (int) Math.pow(ampl(computeOscillations(SpringSystem(binary))) * omega, 2);
    }
}
