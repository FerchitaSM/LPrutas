package com.example.lp.dto;

public enum UserType {
        USERS(1), ADMINISTRADOR(0);

        UserType(int status) {
            this.status = status;
        }

        private int status;

        public int getUserType() {
            return status;
        }
}
