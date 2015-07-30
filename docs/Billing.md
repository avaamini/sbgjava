# sbgjava API Client Library

## Billing

The `Billing` class allows you to make billing requests to the [Seven Bridges Genomics API](https://docs.sbgenomics.com/display/developerhub/API%3A+Billing). 

A `Billing` object is constructed by supplying your authentication token as a string. Be sure to keep your authentication token secure! 

```
String authToken = "58aeb140-1970-0130-6386-001f5b34aa78";
Billing billingRequests = new Billing(authToken);
```

### Get billing info: 
