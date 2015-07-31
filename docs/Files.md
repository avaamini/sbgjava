# Files

To make file-related requests to the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Files), construct an instance of the `Files` class using your authentication token. 

```java
String authToken = "58aeb140-1970-0130-6386-001f5b34aa78";
Files filesRequests = new Files(authToken);
```
### getFileList(String projectID): Get file list

Use this call to return a list of the files in a given project. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
filesRequests.getFileList(projectID);
```

<h6> Example Response </h6> 

```java
{
  "items": [
    {
      "id": 13501,
      "name": "c.elegans_chr2_test.fastq",
      "size": 7998083,
      "metadata": {
          "file_type": "fastq",
          "sample": "Some sample id",
          "paired_end": "1",
          "qual_scale": "illumina13",
          "seq_tech": "Illumina"
      }
    },
    {
      "id": 13502,
      "name": "c.elegans_chr2.fasta",
      "size": 15584937,
      "metadata": {
          "file_type": "fasta"
      }
    }
  ]
}
```

### getFileInfo(String projectID, String fileID): Get file info

Use this call to obtain detailed information about a given file in a given project. The fileID for a file may be obtained using the getFileList(String projectID) call.

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "550c0bbee4b05f167ed28852";
filesRequest.getFileInfo(projectID, fileID)
```

<h6> Example Response </h6> 

```java
{  
    "id": "550c0bbee4b05f167ed28852",  
    "name": "c.elegans_chr2_test.fastq",  
    "size": 7998083,  
    "metadata": {  
        "file_type": "fastq",  
        "sample": "Some sample id", 
        "paired_end": "1",  
        "qual_scale": "illumina13",  
        "seq_tech": "Illumina"  
    }  
}
```

### copyFiles(String projectID, ArrayList<String> fileIDs): Copy files

Use this call to copy a particular group of uploaded files to a given project. Pass in the list of file IDs to be copied. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
ArrayList<String> fileIDs = new ArrayList<String>();
fileIDs.add("5506a44ae4b04a4ab3ae7250");
fileIDs.add("5506a44ae4b04a4ab3ae7254");
filesRequest.copyFiles(projectID, fileIDs);
```

<h6> Example Response </h6> 

```java
{
  "file_id": [
     "5506a44ae4b04a4ab3ae7250",
     "5506a44ae4b04a4ab3ae7254",
     "5506a44ae4b04a4ab3ae7252"
  ]
}
```

### updateMetadata(String projectID, String fileID, String newFilename, JSONObject metadataFields): Update file metadata

Use this call to update the metadata of a given file in a given project. Specify the metadata fields to be updated in a JSONObject. Use "" or `null` for fields you do not wish to update.

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "13501";
String newName = "c.elegans_chr2_test.fastq";
JSONObject metadataFields = {
    "file_type": "fastq",
    "sample": "Some sample id",
    "paired_end": "1",
    "qual_scale": "illumina13",
    "seq_tech": "Illumina"
}
filesRequest.updateMetadata(projectID, fileID, newName, metadataFields);
```

<h6> Example Response </h6> 

```java
{
	"id":"13501",
	"name":"c.elegans_chr2_test.fastq",
	"metadata":{
		"qual_scale":"illumina13",
		"paired_end":"1",
		"seq_tech":"Illumina",
		"file_type":"fastq",
		"sample":"Some sample id"
	},
	"size":116963244
}
``` 

### updateMetadata(String projectID, String fileID, String newFilename, String fileType, String qualScale, String seqTech, String sampleID, String library, String platformUnit, String pairedEnd): Update file metadata

Use this call to update the metadata of a given file in a given project. Specify each metadata field individually. Pass "" or `null` for field you do not wish to update. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "13501";
String newName = "c.elegans_chr2_test.fastq";
String fileType = "fastq";
String sampleID = "Some sample id";
String pairedEnd = "1";
String qualScale = "illumina13";
String sequenceTech = "Illumina";
filesRequest.updateMetadata(projectID, fileID, newName, fileType, qualScale, sequenceTech, sampleID, "", "", pairedEnd);
```

<h6> Example Response </h6> 

```java
{
	"id":"13501",
	"name":"c.elegans_chr2_test.fastq",
	"metadata":{
		"qual_scale":"illumina13",
		"paired_end":"1",
		"seq_tech":"Illumina",
		"file_type":"fastq",
		"sample":"Some sample id"
	},
	"size":116963244
}
``` 

### deleteFile(String projectID, String fileID): Delete a file

Use this call to delete a given file in a given project. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "550c0bbee4b05f167ed28852";
filesRequest.deleteFile(projectID, fileID);
```

<h6> Example Response </h6>

```java
{"Operation finished successfully.":204}
```

### getDownloadURL(String projectID, String fileID): Get download URL

Use this call to obtain a direct download URL for a given file in a given project. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "550c0bbee4b05f167ed28852";
filesRequest.deleteFile(projectID, fileID);
```

<h6> Example Response </h6> 

```java
{
  "url": "https://bucket.s3.amazonaws.com/c.elegans_chr2_test.fastq%2Badeec6e8-c4ca-8181-daad-27c2c28b4e89?response-content-type=application%2Foctet-stream&Expires=1354197116&response-content-disposition=attachment%3B%20filename%3Dc.elegans_chr2_test.fastq&AWSAccessKeyId=AKIAIP48A9IU09FBANQI&Signature=ThYOrtdfyYDUF%2Bqz73rjAWdSg84%3D"
}


