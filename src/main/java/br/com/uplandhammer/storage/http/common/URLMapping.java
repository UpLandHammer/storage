package br.com.uplandhammer.storage.http.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class URLMapping {
    public static final String API = "api/storage";
    //BUCKET
    public static final String BUCKET_CREATE = "/bucket";
    public static final String BUCKET_LIST_ALL = "/buckets";
    public static final String BUCKET_REMOVE = "/bucket/{bucketName}";
    //FILE
    public static final String FILE_UPLOAD = "/file/upload/{bucketName}";
    public static final String FILE_GET_INFO = "/file/info/{bucketName}";
    public static final String FILE_GET_BY_BUCKET = "/file/{fileName}/bucket/{bucketName}";
    public static final String FILE_REMOVE_FILE_BY_BUCKET = "/file/{fileName}/bucket/{bucketName}";

}
