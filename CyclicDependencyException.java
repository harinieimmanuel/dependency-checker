package com.nab.coding.exception;

public class CyclicDependencyException extends Exception{
        private final String message;

        public CyclicDependencyException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
}
