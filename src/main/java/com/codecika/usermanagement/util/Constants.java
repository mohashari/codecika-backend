package com.codecika.usermanagement.util;

/**
 * Created by yukibuwana on 1/24/17.
 */

public class Constants {

    public static final class PageParameter {
        public static final String LIST_DATA = "listData";
        public static final String TOTAL_PAGES = "totalPages";
        public static final String TOTAL_ELEMENTS = "totalElements";
    }

    public static final class Keycloak {
        public static final String REALM_MASTER = "master";
        public static final String ADMIN_CLI_CLIENT_ID = "admin-cli";
    }

    public static final class ParkingLocation {
        public static final String API = "/location-service/internal/parking/get-location-by-secure-id";
    }

    public static final class Transaction {
        public static final String API = "/transaction-service/internal/transaction/get-regcode?qrcode=";
    }

    public static final class Astra {
        public static final String API = "/api/v1/ADP/BigDataCustomer/CheckCustomer/";
    }
}
