package br.com.uplandhammer.storage.exception;

public class StorageFileException extends RuntimeException {
    public StorageFileException(String message) {
        super(message);
    }

    public StorageFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
