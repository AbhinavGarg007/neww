package com.example.emp354.linear;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextValidation {



public static boolean isValidName(String string){
    String NAME_PATTERN="^[A-Z]{1}[a-z]{1,}$";
    Pattern pattern=Pattern.compile(NAME_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();
}

public static boolean isValidUserName(String string)
{
    String USER_NAME_PATTERN="^[A-Za-z]{4,}[0-9]{2,}$";
    Pattern pattern=Pattern.compile(USER_NAME_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();

}

public static boolean isValidPhoneNumber(String string)
{
    String PHONE_NUMBER_PATTERN="^[2-9]{2}[0-9]{8}$";
    Pattern pattern=Pattern.compile(PHONE_NUMBER_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();
}

public static boolean isValidEmail(String string)
{
    String EMAIL_PATTERN="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    Pattern pattern=Pattern.compile(EMAIL_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();
}

public static boolean isValidPassword(String string)
{
    String PASSWORD_PATTERN="^[A-Za-z0-9]{6,8}$";
    Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();
}

public static boolean isValidOptionalCode(String string)
{
    String OPTIONAL_CODE_PATTERN="^[A-Za-z]{4}[0-9]{2,3}$";
    Pattern pattern=Pattern.compile(OPTIONAL_CODE_PATTERN);
    Matcher matcher=pattern.matcher(string);
    return matcher.matches();

}
}
