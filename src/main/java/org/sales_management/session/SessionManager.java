package org.sales_management.session;

public class SessionManager {
        private static UserSession userSession;

        public static UserSession getSession() {
            return userSession;
        }

        public static void setSession(UserSession session) {
            userSession = session;
        }

        public static void clearSession() {
            userSession = null;
        }
}
