# sbgjava API Client: Files

To make file-related requests to the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Files), construct an instance of the `Files` class using your authentication token. 

```
String authToken = "58aeb140-1970-0130-6386-001f5b34aa78";
Files filesRequests = new Files(authToken);
```
### getFileList(String projectID): Get file list

Use this call to return a list of the files in a given project. 

<h4> Example Request

```
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
filesRequests.getFileList(projectID);
```

<h4> Example Response

```
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

<h4> Example Request

```
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String fileID = "550c0bbee4b05f167ed28852";
filesRequest.getFileInfo(projectID, fileID)
```

<h4> Example Response

```
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
