# Description
Example repo for possible bug in spring security 5.8.0

BasicAuth is not working with
```SecurityContextHolder.strategyName = MODE_INHERITABLETHREADLOCAL```
enabled.