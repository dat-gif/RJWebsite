/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Validation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX
            = Pattern.compile("^(84|0[3|5|7|8|9])+([0-9]{8})$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX
            = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    /**
     * Check if the input email contains username, an @ symbol, domain name, a
     * dot, and the domain
     *
     * @param inputEmail
     * @return
     */
    public boolean emailValidation(String inputEmail) {
        if (inputEmail.isEmpty() || inputEmail.length() > 320) {
            return false;
        }
        Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(inputEmail);
        return m.find();
    }

    /**
     * Check if phone number in VietNam phone number format
     *
     * @param inputPhoneNumber
     * @return
     */
    public boolean phoneNumberValidation(String inputPhoneNumber) {
        Matcher m = VALID_PHONE_REGEX.matcher(inputPhoneNumber);
        return m.find();
    }

    /**
     * Check if the input password contains word, number and at least 8
     * character
     *
     * @param inputPassword
     * @return
     */
    public boolean passwordValidation(String inputPassword) {
        Matcher m = VALID_PASSWORD_REGEX.matcher(inputPassword);
        return m.find();
    }

    public boolean checkConfirmPassword(String password, String confirmPassword) {

        return password.equals(confirmPassword);
    }

    public static void main(String[] args) {
        Validation v = new Validation();
        System.out.println(v.emailValidation("text@gmailom"));
        System.out.println(v.phoneNumberValidation("0969051715"));
    }
}
