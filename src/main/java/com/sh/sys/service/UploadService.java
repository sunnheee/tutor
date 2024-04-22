package com.sh.sys.service;

import java.io.InputStream;

public interface UploadService {


    void init();

    String upload(InputStream in);
}
