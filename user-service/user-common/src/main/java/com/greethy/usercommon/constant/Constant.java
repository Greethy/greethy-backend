package com.greethy.usercommon.constant;

public final class Constant {

    public static class RoleType {

        public static final String ROLE_REGULAR = "regular";
        public static final String ROLE_PREMIUM = "premium";
        public static final String ROLE_EXPERT = "expert";
        public static final String ROLE_MERCHANT = "merchant";
        public static final String ROLE_ADMIN = "admin";

    }

    public static class MessageKeys {
        public static final String LOGIN_SUCCESS = "user.success.login";
        public static final String REGISTER_SUCCESS = "user.success.register";
        public static final String EMAIL_DUPLICATE = "user.bad-request.email.duplicated";
        public static final String USER_NOT_FOUND = "user.bad-request.not-found";
        public static final String INVALID_USERNAME_OR_EMAIL = "user.auth.login.invalid-username-or-email";
        public static final String WRONG_PASSWORD = "user.auth.login.wrong-password";
        public static final String ACCESS_DENIED = "user.auth.access-denied";
    }

}
