package de.dbo.samples.java8.tutorial0.lambda.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */
public class ListSort {

    public static void main(String[] args) {
	
        final List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        
        final List<String> namesWithNull = Arrays.asList("peter", null, "anna", "mike", "xenia");
        
        final List<String> namesNull = null;

        // standard (old) way of sorting lists with strings
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        
        Collections.sort(names, (String a, String b) -> { return b.compareTo(a); });  // using a bit of lambda
        Collections.sort(names, (String a, String b) -> b.compareTo(a));              // using more(!) of lambda
        Collections.sort(names, (a, b) -> a.compareTo(b));                            // using much(!) of lambda

        System.out.println(names);

        names.sort(Collections.reverseOrder());
        System.out.println(names);

        namesWithNull.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(namesWithNull);

        Optional.ofNullable(namesNull).ifPresent(list -> list.sort(Comparator.naturalOrder()));
        System.out.println(namesNull);
    }

}