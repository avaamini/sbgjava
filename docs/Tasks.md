# Tasks

To manage tasks via the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Tasks), construct an instance of the `Tasks` class using your authentication token. 
```java
String authToken = "c6d0207bed2041a5a2293f7e171b6795";
Tasks taskRequests = new Tasks(authToken);
```

### getTasks(String projectID): Get tasks

Use this call to return a list of all the tasks in a given project. The response is formatted as a JSONObject. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
taskRequests.getTasks(projectID); 
```

<h6> Example Response </h6> 

```java
{
    "items": [
        {
            "id": "53080",
            "name": "RNA-Seq Alignment - TopHat run - 02-25-2015 08:10:22",
            "description": "",
            "pipeline_id": "547deeb9d79f0019815645e5",
            "pipeline_revision": "0",
            "start_time": 1424851823000,
            "status": {
                "status": "draft",
                "message": "",
                "jobs_completed": 0,
                "jobs_total": 0
            },
            "parameters": {
                "145798": {
                    "path": "/Projects/7707/"
                },
                "173320": {},
                "276485": {},
                "276899": {
                    "path": [
                        ":547deea5e4b017b3add30610"
                    ]
                },
                "292444": {
                    "path": "/Projects/7707/"
                },
                "478419": {
                    "path": "/Projects/7707/"
                },
                "559468": {
                    "path": "/Projects/7707/"
                },
                "577805": {},
                "604423": {
                    "path": "/Projects/7707/"
                },
                "641225": {
                    "path": [
                        ":547dee8ae4b017b3add30577"
                    ]
                },
                "676986": {},
                "737351": {
                    "path": "/Projects/7707/"
                },
                "744363": {
                    "path": "/Projects/7707/"
                },
                "819711": {
                    "path": "/Projects/7707/"
                },
                "920464": {
                    "path": []
                },
                "945088": {
                    "path": "/Projects/7707/"
                },
                "962991": {},
                "983238": {}
            }
        },
        {
            "id": "53078",
            "name": "Alignment Metrics QC run - 02-25-2015 08:06:26",
            "description": "",
            "pipeline_id": "547df455d79f0019815645eb",
            "pipeline_revision": "0",
            "start_time": 1424851586000,
            "status": {
                "status": "draft",
                "message": "",
                "jobs_completed": 0,
                "jobs_total": 0
            },
            "parameters": {
                "1546689": {
                    "path": []
                },
                "1551273": {
                    "path": []
                },
                "1555367": {},
                "1672814": {},
                "1793436": {
                    "path": "/Projects/7707/"
                }
            }
        },
         
        <snip>
         
        {
            "id": "43542",
            "name": "RNA-Seq Differential Expression - Cuffdiff (with Visualization) run - 10-17-2014 23:23:53",
            "description": "",
            "pipeline_id": "547deeb9d79f0019815645e6",
            "pipeline_revision": "0",
            "start_time": 1417521257000,
            "status": {
                "status": "completed",
                "message": "Completed.",
                "jobs_completed": 11,
                "jobs_total": 11
            },
            "parameters": {
                "120501": {},
                "192932": {
                    "path": "/Projects/7707/"
                },
                "197191": {
                    "path": "/Projects/7707/"
                },
                "384093": {
                    "path": "/Projects/7707/"
                },
                "398498": {
                    "path": [
                        ":547dee8ae4b017b3add30577"
                    ]
                },
                "416551": {},
                "529198": {},
                "632912": {
                    "groups": [
                        {
                            "files": [
                                ":547deeb1e4b017b3add30652",
                                ":547deeb4e4b017b3add30664",
                                ":547dee98e4b017b3add305c5"
                            ],
                            "name": "Heart"
                        },
                        {
                            "files": [
                                ":547deeabe4b017b3add3062e",
                                ":547dee89e4b017b3add30571",
                                ":547dee8fe4b017b3add30595"
                            ],
                            "name": "Cerebral_Cortex"
                        },
                        {
                            "files": [
                                ":547dee88e4b017b3add3056b",
                                ":547dee86e4b017b3add30563",
                                ":547deeb7e4b017b3add30674"
                            ],
                            "name": "Liver"
                        }
                    ]
                },
                "866860": {
                    "path": [
                        ":547deea5e4b017b3add30610"
                    ]
                },
                "889880": {
                    "path": "/Projects/7707/"
                },
                "971061": {}
            }
        }
    ]
}
```

### executeTask(String projectID, String pipelineID, String pipelineRevision, String name, String description, JSONObject inputs, JSONObject parameters): Execute a task

Use this call to run a task within a given project. The response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String pipelineID = "422";
String pipelineRevision = "";
String name = "Test 2 of C. Elegans VC";
String description = "Testing Caenorhabditis Elegans Exome Variant Calling";

JSONObject inputs = new JSONObject();
inputs.put("309485", new JSONArray().put("13645"));
inputs.put("317344", new JSONArray().put("13646"));
inputs.put("318662", new JSONArray().put("13645"));
inputs.put("699018", new JSONArray().put("13647"));

JSONObject params = new JSONObject();
JSONObject params393463 = new JSONObject();
params393463.put("read_trimming_qual", 30);
params393463.put("rg_seq_tech", "Illumina");
params.put("393463", params393463);
params.put("677492", new JSONObject());

taskRequests.executeTask(projectID, pipelineID, pipelineRevision, name, description, inputs, params);
```

<h6> Example Response </h6> 
```java
{
    "id": "6870",
    "name": "FastQC Analysis run - 03-20-2015 08:01:09",
    "description": "",
    "pipeline_id": "550c0beb727ab720186db837",
    "pipeline_revision": "0",
    "start_time": 1426838469000,
    "status": {
        "status": "completed",
        "message": "Completed.",
        "jobs_completed": 4,
        "jobs_total": 4
    },
    "inputs": {
        "177252": [
            ":550c0bbee4b05f167ed2880c",
            ":550c0bbee4b05f167ed2881b",
            ":550c0bbee4b05f167ed2881d"
        ]
    },
    "parameters": {
        "558078": null
    },
    "outputs": {
        "556132": [
            "550c10a5e4b05f167ed288fe",
            "550c10a1e4b05f167ed288fb",
            "550c109de4b05f167ed288f8"
        ],
        "1274412": [
            "550c109de4b0980a10cb2ab7",
            "550c10a1e4b0980a10cb2aba",
            "550c10a5e4b0980a10cb2abd"
        ]
    }
}
```

### getTaskDetails(String projectID, String taskID): Get task details

Use this call to obtain information about a particular task in a given project. Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String taskID = "94";
taskRequests.getTaskDetails(projectID, taskID);
```

<h6> Example Response </h6>

```java
{
    "items": [
        {
            "id": "6870",
            "name": "FastQC Analysis run - 03-20-2015 08:01:09",
            "description": "",
            "pipeline_id": "550c0beb727ab720186db837",
            "pipeline_revision": "0",
            "start_time": 1426838469000,
            "status": {
                "status": "completed",
                "message": "Completed.",
                "jobs_completed": 4,
                "jobs_total": 4
            },
            "parameters": {
                "177252": {
                    "path": [
                        ":550c0bbee4b05f167ed2880c",
                        ":550c0bbee4b05f167ed2881b",
                        ":550c0bbee4b05f167ed2881d"
                    ]
                },
                "556132": {
                    "path": "\/Projects\/13826\/"
                },
                "558078": null,
                "1274412": {
                    "path": "\/Projects\/13826\/"
                }
            }
        }
    ]
}
```

### taskAction(String projectID, String taskID, String action): Abort a task 

Use this call to execute a designated action on a particular task. Currently the only supported action is "abort". Response is formatted as a JSON Object. 

<h6> Example Request </h6> 

```java
String projectID = "130d3136-eb47-4bea-98e4-96225a89f647";
String taskID = "94";
String action = "abort"; 
taskRequests.taskAction(projectID, taskID, action);
```

<h6> Example Response </h6> 

```java
{"Operation finished successfully.":204}
```

