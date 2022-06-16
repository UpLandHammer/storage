# Storage

This API is designed to store files on `amazon's S3` service.
Made with `SpringBoot` and documented with `Swagger`.

## Note, you must have an amazon account with access to the S3 service.

## API's

**To Create a bucket on S3.**


```
curl --location --request POST 'http://localhost:9166/api/storage/bucket/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bucketName": "myBucketName"
}'
```

**List all buckets**

```
curl --location --request GET 'http://localhost:9166/api/storage/bucket/all'
```

**List all files by bucket, remembering that the folder must exist**

```
curl --location --request GET 'http://localhost:9166/api/storage/file/info/myBucketName'
```

**Store File**
```
curl --location --request POST 'http://localhost:9166/api/storage/file/upload/myBucketName' \
--form 'file=@"/C:/Users/Dell/Downloads/file.txt"'
```

**Get file stored**

```
curl --location --request GET 'http://localhost:9166/api/storage/file/myBucketName/file.txt'
```

**Remove file by bucket**

```
curl --location --request DELETE 'http://localhost:9166/api/storage/file/remove/myBucketName/file.txt'
```

**Remove bucket**

```
curl --location --request DELETE 'http://localhost:9166/api/storage/bucket/remove/myBucketName'
```
