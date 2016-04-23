package de.dbo.samples.java8.tutorial0.lambda.converter;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ConverterUsageMore {

    static int outerStaticNum;

    int outerNum;

    void testScopes() {
        int num = 1;

       Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);

        String convert = stringConverter.convert(2);
        System.out.println(convert);    // 3

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerNum = 13;
            return String.valueOf(from);
        };

        String[] array = new String[1];
        Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };

        stringConverter3.convert(23);

        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new ConverterUsageMore().testScopes();
    }

}