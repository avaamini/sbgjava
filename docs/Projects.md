# Projects

To manage projects via the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Projects), construct an instance of the `Projects` class using your authentication token. 
```java
String authToken = "c6d0207bed2041a5a2293f7e171b6795";
Projects projectsRequests = new Projects(authToken);
```

### getProjects(): Get projects

Use this call to obtain the list of all projects you have access to. 

<h6> Example Request </h6> 

```java
projectsRequests.getProjects();
```

<h6> Example Response </h6> 

```java
{
    "items": [
        {
            "id": "7f7a72d0-da77-4f51-9416-99f14f7316ab",
            "name": "Project 1 ",
            "description": "My first project",
            "my_permissions": {
                "write": true,
                "copy": true,
                "execute": true,
                "admin": true
            }
        },
        {
            "id": "fe9b0038-dbee-40e9-a9fc-fed2e7db6519",
            "name": "Test Project 2",
            "description": "My second project",
            "my_permissions": {
                "write": true,
                "copy": true,
                "execute": true,
                "admin": true
            }
        },
        {
            "id": "0f3d2f23-9efe-4c3c-89a1-97290d04e1f4",
            "name": "My first Project (testuser)",
            "description": "This example project contains data, completed analyses and results. There's an example of exome analysis, RNA-Seq read alignment, and FASTQ quality control. Open it up and take a look. It's the fastest way to learn how to get work done with Seven Bridges.",
            "my_permissions": {
                "write": true,
                "copy": true,
                "execute": true,
                "admin": true
            }
        }
    ]
}
```

### getProjectDetails(String projectID): Get project details

Use this call to return details about a specific project. Response is formatted as a JSON Object. 

<h6> Example Request </h6>

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
projectsRequests.getProjectDetails(projectID);
```

<h6> Example Response </h6> 

```java
{
  "id": "130d3136-eb47-4bea-98e4-96225a89f647",
  "name": "Test Project",
  "description": "Project used for testing.",
  "my_permissions": {
    "write": true,
    "copy": true,
    "execute": true,
    "admin": true
  }
}
```

### getProjectMembers(String projectID): Get project member details

Use this call to return information about each member of a specific project. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java 
String projectID = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
```

<h6> Example Response </h6> 

```java
{
    "items": [
    {
        "id": "08890148-6d9e-4a10-b284-924228d3f99a",
        "user_name": "user1",
        "invitation_pending": false,
        "permissions": {
            "write": true,
            "copy": true,
            "execute": true,
            "admin": false
        }
    },
    {
        "id": "3b4858d3-0a0c-4704-91b7-fa15b5906409",
        "user_name": "user-owner",
        "invitation_pending": false,
        "permissions": {
            "write": true,
            "copy": true,
            "execute": true,
            "admin": true
        }
    }
    ]
}
```

### createProject(String name, String description, String billingGroupID): Create a project

Use this call to create a new project. Specify the project's name, description, and billing group. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String name = "Test API project";
String description = "My first API project";
String billingGroupID = "934140c0-8000-4efd-816c-917584978312";
```

<h6> Example Response </h6> 

```java
{ 
    "id": "067c72e8-54b4-45ec-a46a-21b7494a826b", 
    "name": "Test API project", 
    "description": "My first API project", 
    "my_permissions": { 
        "write": true, 
        "copy": true, 
        "execute": true, 
        "admin": true 
    } 
}
```

### addProjectMember(String projectID, String username, boolean canWrite, boolean canCopy, boolean canExecute, boolean isAdmin): Add project members

Use this call to add users to a project and set their privileges. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
String username = "testuser";
boolean canWrite = true;
boolean canCopy = true;
boolean canExecute = true;
boolean isAdmin = false;
projectsRequests.addProjectMember(projectID, username, canWrite, canCopy, canExecute, isAdmine);
```

<h6> Example Response </h6>

```java
{
    "id": "88fc89c1-cfcd-46ed-a830-6a2fc110c628",
    "username": "testuser",
    "invitation_pending": false,
    "permissions": {
        "write": true,
        "copy": true,
        "execute": false,
        "admin": false
    }
}
```

### editMemberDetails(String projectID, String userID, boolean canWrite, boolean canCopy, boolean canExecute, boolean isAdmin): Edit project members' details

Use this call to set project members' permissions. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
String userID = " 08890148-6d9e-4a10-b284-924228d3f99a";
boolean canWrite = true;
boolean canCopy = true;
boolean canExecute = false;
boolean isAdmin = false;
projectsRequests.editMemberDetails(projectID, userID, canWrite, canCopy, canExecute, isAdmin);
```

<h6> Example Response </h6>

```java
{"Operation finished successfully.":204};
```

### deleteProject(String projectID): Delete a project

Use this call to remove a specified project from the Seven Bridges Platform. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "8e885662-c891-4227-bdb5-d87a90f3f461";
projectsRequests.deleteProject(projectID);
```

<h6> Example Response </h6> 

```java
{"Operation finished successfully.":204};
```

### deleteProjectMember(String projectID, String userID): Remove a project member

Use this call to remove a particular member from a specified project. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "7f7a72d0-da77-4f51-9416-99f14f7316ab";
String userID = "08890148-6d9e-4a10-b284-924228d3f99a";
```

<h6> Example Response </h6> 

```java
{"Operation finished successfully.":204};
```
