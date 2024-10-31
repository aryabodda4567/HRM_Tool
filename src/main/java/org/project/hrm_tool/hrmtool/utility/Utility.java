package org.project.hrm_tool.hrmtool.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.temporal.ChronoUnit;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.Duration;

    public class Utility {


        // Method to encode a string using Base64
        public static String encodeString(String input) {
            return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
        }



        // Method to decode a Base64 encoded string
        public static String decodeString(String encoded) {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        }



        // Method to generate a random number
        public static long generateRandomNumber(long min, long max) {
            Random random = new Random();
            return random.nextLong(max - min + 1) + min; // inclusive of both min and max
        }



        // Method to generate a random string of given length
        public static String generateRandomString(int length) {
            StringBuilder sb = new StringBuilder();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                sb.append(characters.charAt(index));
            }
            return sb.toString();
        }



        // Method to calculate the number of days difference between two dates
        public static long daysBetween(LocalDate startDate, LocalDate endDate) {
            return ChronoUnit.DAYS.between(startDate, endDate);
        }




        // Method to calculate the difference between two timestamps in days and hours
        public static String timeDifference(LocalDateTime start, LocalDateTime end) {
            Duration duration = Duration.between(start, end);
            long days = duration.toDays();
            long hours = duration.toHours() % 24; // remaining hours after days
            return days + " days and " + hours + " hours";
        }

        // Method to generate SHA-256 hash of a given string
        public static String generateHash(String input) {
            try {
                // Create a MessageDigest instance for SHA-256
                MessageDigest digest = MessageDigest.getInstance("SHA-256");

                // Generate the hash
                byte[] hashBytes = digest.digest(input.getBytes());

                // Convert byte array to hex string
                StringBuilder hexString = new StringBuilder();
                for (byte b : hashBytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0'); // Append leading zero if necessary
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Hashing algorithm not found", e);
            }
        }



}
