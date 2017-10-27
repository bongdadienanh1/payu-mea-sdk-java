## PayU SOAP API Java SDK

This repository contains Java SDK and samples for SOAP API.

## Please Note
> **The Payment Card Industry (PCI) Council has [mandated](http://blog.pcisecuritystandards.org/migrating-from-ssl-and-early-tls) that early versions of TLS be retired from service.  All organizations that handle credit card information are required to comply with this standard. As part of this obligation, PayPal is updating its services to require TLS 1.2 for all HTTPS connections. At this time, PayPal will also require HTTP/1.1 for all connections.**

## Prerequisites
* Java JDK 6 or higher
* An environment which supports TLS 1.2 

#### Gradle
```gradle
repositories {
	mavenCentral()
}
dependencies {
	compile 'co.za.payu.sdk:payu-mea-java-sdk:+'
}
```

## Get Started
- [Run Samples project](rest-api-sample).

License
--------------------
Code released under [SDK LICENSE](LICENSE)

Contributions
--------------------
Pull requests and new issues are welcome. See [CONTRIBUTING.md](CONTRIBUTING.md) for details.
