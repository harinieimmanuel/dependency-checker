package com.nab.coding.exception;

public class InvalidInputException extends Exception{
        private final String message;

        public InvalidInputException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
}
