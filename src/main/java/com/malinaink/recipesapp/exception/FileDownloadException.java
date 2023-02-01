package com.malinaink.recipesapp.exception;

import java.io.IOException;

public class FileDownloadException extends IOException {
    public FileDownloadException(String msg) {
        super(msg);
    }

}
