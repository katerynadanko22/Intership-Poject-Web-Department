package org.example.unit;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

import java.io.*;

public class FileItemImpl implements FileItem {

    private String path;

    public FileItemImpl(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream(){
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isInMemory() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] get() {
        return new byte[0];
    }

    @Override
    public String getString(String s) {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public void write(File file) {

    }

    @Override
    public void delete() {

    }

    @Override
    public String getFieldName() {
        return null;
    }

    @Override
    public void setFieldName(String s) {

    }

    @Override
    public boolean isFormField() {
        return false;
    }

    @Override
    public void setFormField(boolean b) {

    }

    @Override
    public OutputStream getOutputStream() {
        return null;
    }

    @Override
    public FileItemHeaders getHeaders() {
        return null;
    }

    @Override
    public void setHeaders(FileItemHeaders fileItemHeaders) {

    }
}

