package br.com.links_organizer_core_back_springboot.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }
}