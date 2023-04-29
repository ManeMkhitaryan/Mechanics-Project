package com.company;

public class ConverterInt extends Converter{
    public ConverterInt(double t0, double t1, double dt, double x0, double v0, double m, double omega) {
            super(t0, t1, dt, x0, v0, m, omega);
            }

    public String SpringSystem(String bits) {
            if (bits.length() == 0) {
                return "";
            }

            String result = "[";
            String brackets = "[]";

            int j = bits.length() - 1;

            for (int i = 0; i < bits.length(); i++){
                if (bits.charAt(j--) == '1') {
                    result += '[';
                }
                    while (brackets.length() != Math.pow(2, i+1)) {
                        brackets += brackets;
                    }

                    result += brackets;
                    result += ']';
                }
                result += ']';
                return result;
            }

    public int BinaryToDecimal(String binary) {
            return (int) Math.pow(ampl(computeOscillations(SpringSystem(binary))) * omega, 2);
            }
}







