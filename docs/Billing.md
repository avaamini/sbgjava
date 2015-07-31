# Billing

To make billing-related requests to the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Billing), construct an instance of the `Billing` class using your authentication token. 
```java
String authToken = "58aeb140-1970-0130-6386-001f5b34aa78";
Billing billingRequests = new Billing(authToken);
```

### getBillingInfo(String authToken): Get billing info

Use this call to return a list of the IDs of all the billing groups you have access to. The response is formatted as a JSONObject. 

<h6> Example Request </h6> 

```java
billingRequests.getBillingInfo();
```

<h6> Example Response </h6>

```java
{
  "items": [
    {
      "id": "934140c0-8000-4efd-816c-917584978312",
      "name": "Pilot Fund (username)"
    }
  ]
}
```

