# Upload

To securely and reliably [upload large files](https://docs.sbgenomics.com/display/developerhub/API%3A+Upload+Files) to the Seven Bridges Platform via the API, construct an instance of the `Upload` class by supplying your authentication token. 
```java
String authToken = "c6d0207bed2041a5a2293f7e171b6795";
Upload uploadRequests = new Upload(authToken);
```

### getUploadInfo(String uploadID): Get info about an upload

Use this call to obtain information about an active upload. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String uploadID = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
uploadRequests.getUploadInfo(uploadID);
```

<h6> Example Response </h6> 

```java
{
  "upload_id": "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT",
  "project_id": "130d3136-eb47-4bea-98e4-96225a89f647",
  "name": "Sample1_RNASeq_chr20.pe_1.fastq",
  "part_size": 5242880
}
```

### getURLFilePart(String uploadID, int partNumber): Get upload URL for a file part

Use this call to get the URL to which the specified file part should be uploaded. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String uploadID = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
int filePart = 1;
uploadRequests.getURLFilePart(uploadID, filePart);
```

<h6> Example Response </h6> 

```java
{
    "upload_id": "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT",
    "part_number": 1,
    "uri": "https://sbg.s3.amazonaws.com/Sample1_RNASeq_chr20.pe_1.fastq?&partNumber=1Expires=1423158590&Signature=BMfU70lIJyIBHYSpfuFl4zIKRvQ%3D"
}
```

### initializeUpload(String projectID, String name, Integer size, Integer partSize): Initialize file upload

Use this call to initialize the upload of a file. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileName = "Sample1_RNASeq_chr20.pe_1.fastq";
Integer fileSize = 5242880;
Integer partSize = null;
uploadRequests.initializeUpload(projectID, fileName, fileSize, partSize);
```

<h6> Example Response </h6> 

```java
{
    "upload_id": "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT",
    "project_id": "130d3136-eb47-4bea-98e4-96225a89f647",
    "name": "Sample1_RNASeq_chr20.pe_1.fastq",
    "part_size": 5242880
}
```

### confirmUpload(String uploadID, Integer partNumber, String eTag): Confirm successful upload

Use this call to verify via the API that part of a file has been successfully uploaded to the backing storage. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String upload = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
Integer partNumber = 1;
String partETag = "d41d8cd98f00b204e9800998ecf8427e";
uploadRequests.confirmUpload(upload, partNumber, partETag);
```

<h6> Example Response </h6> 

```java
{"Operation finished successfully.":204};
```

### finalizeUpload(String uploadID): Finalize file upload

Use this call to finalize the upload of a file. Response is formatted as a JSON Object. 

<h6> Example Request </h6>

```java
String uploadID = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
uploadRequests.finalizeUpload(uploadID);
```

<h6> Example Response </h6> 

```java
{
    "id" : "54fef96de4b01a2f7d562685",
    "name" : "RefSeq.exome.b37.bed",
    "size" : 21120387
}
```

### abortUpload(String uploadID): Abort upload

Use this call to abort an existing upload specified by its upload ID. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String uploadID = "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT";
uploadRequests.abortUpload(uploadID);
```

<h6> Example Response </h6> 

```java
{
    "upload_id": "8D7sQJxQk14ubsEnKaoeQZlRvV6ouQtMzBWaQNJdxPDLypUC3WogwtJdncevHxnT",
    "project_id": "130d3136-eb47-4bea-98e4-96225a89f647",
    "name": "Sample1_RNASeq_chr20.pe_1.fastq",
    "part_size": 5242880
}
```






