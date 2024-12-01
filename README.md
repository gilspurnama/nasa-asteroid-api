## Nasa Near Earth Object(Neo) API

This application is a Restful API application that connect with Nasa API for searching near-earth object (neo) with parameter of date time

# APIs

There are 2 APIs provided in this application

- Neo feed API
  - endpoint: /neo/feeds
  - params: 
    - startDate: yyyy-MM-dd
    - endDate: yyyy-MM-dd
    - nearest10: true/false
    - This API is to search neo based on time that was given. If the parameter 'nearest10' is true, the API will only return 10 of the nearest neo base on given time
- Neo lookup API
  - endpoint: /neo/feeds/{neoID}
  - This API gives the detailed information of the neo.

# UI
This API also used in a simple UI that I created. The UI is in nasa-asteroid-ui repo
