package com.greethy.usercommon.constant;

public final class Constant {

    public static class RoleType {

        public static final String ROLE_REGULAR = "REGULAR";
        public static final String ROLE_PREMIUM = "PREMIUM";
        public static final String ROLE_EXPERT = "EXPERT";
        public static final String ROLE_MERCHANT = "MERCHANT";
        public static final String ROLE_ADMIN = "ADMIN";

    }

    public static class MessageKeys {
        public static final String LOGIN_SUCCESS = "user.success.login";
        public static final String REGISTER_SUCCESS = "user.success.register";

        public static final String EMAIL_DUPLICATE = "user.bad-request.email.duplicated";
        public static final String USER_NOT_FOUND = "user.bad-request.not-found";

        public static final String DATA_USERNAME_OR_EMAIL_BLANK = "data.invalid.blank.username-or-email";
        public static final String DATA_PASSWORD_BLANK = "data.invalid.blank.password";

        public static final String INVALID_USERNAME_OR_EMAIL = "user.auth.login.invalid-username-or-email";
        public static final String WRONG_PASSWORD = "user.auth.login.wrong-password";
        public static final String ACCESS_DENIED = "user.auth.access-denied";
    }

}
