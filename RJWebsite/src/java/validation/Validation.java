/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static final Pattern VALID_ONLY_TEXT = Pattern.compile("^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]*$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME_TEXT = Pattern.compile("^[A-Za-z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{1,150}$", Pattern.CASE_INSENSITIVE);

    /**
     * Check if the input email contains username, an @ symbol, domain name, a
     * dot, and the domain
     *
     * @param inputEmail String
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
     * @param inputPhoneNumber String
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
     * @param inputPassword String
     * @return
     */
    public boolean passwordValidation(String inputPassword) {
        Matcher m = VALID_PASSWORD_REGEX.matcher(inputPassword);
        return m.find();
    }

    public boolean checkConfirmPassword(String password, String confirmPassword) {

        return password.equals(confirmPassword);
    }

    public boolean checkOnlyContainText(String string) {
        Matcher m = VALID_ONLY_TEXT.matcher(string);
        return m.find();
    }

    public boolean nameValidation(String firstName, String lastName) {
        Matcher fname = VALID_NAME_TEXT.matcher(firstName);
        Matcher lname = VALID_NAME_TEXT.matcher(lastName);
        if (!fname.find() || !lname.find()) {
            return false;
        }
        return true;
    }

    public String modifyString(String string) {
        if (string.length() > 0 && string != null) {
            string = string.replaceAll("\\s{2,}", " ").trim();
            return string;
        } else {
            return string;
        }
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public boolean checkDateStartEnd(String startDate, String endDate) {
        boolean isTrue = true;
        if (startDate.isEmpty() || endDate.isEmpty()) {
            return isTrue;
        }
        try {
            isTrue = sdf.parse(startDate).getTime() < sdf.parse(endDate).getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);

        }
        return isTrue;
    }

    public static void main(String[] args) throws ParseException {
        Validation v = new Validation();
        System.out.println(v.nameValidation("mad", "aad"));
        System.out.println(v.phoneNumberValidation("0969051715"));
        System.out.println(v.checkDateStartEnd("2016-10-13", "2014-05-13"));
    }
}
