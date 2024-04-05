package dev.lpa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

//        - An email address should be formatted as username@domain.
//
//        - The username part of the email address can contain alphanumeric characters, so
//         lowercase a to z, upper case A to Z, digit 0 to 9, dots or periods, underscores, and
//         hyphens or dashes(-).
//
//         -The domain part of the email address can contain alphanumeric characters and digits, as
//         well as periods or dashes, such as xyz.com, or abc-xyz.org, but could also be xyz.edu.uk
//         for example


        String emailText = """
                berkay.alkan@gmail.com
                berkay.alkan@invalid
                utku.berkay-alkan@msn.com
                hakan-Alkan2003@valid.co.uk
                berkay-2001@valid.net
                ali!@invalid.com
                nilgun@valid-test.com.au
                nilguninvalid1977@.com
                naz@valid.io
                david@invalid..com
                """;

        Pattern partialPattern = Pattern.compile(
                "([\\p{Alnum}_.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");

        Matcher emailMatcher = partialPattern.matcher(emailText);
        emailMatcher.results().forEach(mr -> {
            System.out.printf("[Username = %s, domain = %s]%n",
                    mr.group(1),
                    mr.group(2));
        });

        //----------------ANOTHER WAY---------------------


        Pattern emailPattern = Pattern.compile(
                "([\\p{Alnum}_.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
        String[] emailSamples = emailText.lines().toArray(String[]::new);
        for (String email : emailSamples){
            Matcher eMatcher = emailPattern.matcher(email);
            boolean matched = eMatcher.matches();
            System.out.println(email + " is " + (matched ? "VALID" : "INVALID"));
            if (matched){
                System.out.printf("[Username = %s, domain = %s]%n",
                        eMatcher.group(1),
                        eMatcher.group(2));
            }else {
                System.out.println();
            }
        }
    }
}
