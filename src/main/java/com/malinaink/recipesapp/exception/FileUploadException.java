package com.malinaink.recipesapp.exception;

import java.io.IOException;

public class FileUploadException extends IOException {
    public FileUploadException(String msg) {
        super(msg);
    }
}
