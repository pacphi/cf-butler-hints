# cf-butler-hints

Spring Native hints for [cf-butler](https://github.com/pacphi/cf-butler).

## Clone

```
git clone https://github.com/pacphi/cf-butler-hints.git
```

## How to Build

```
./mvnw org.apache.maven.plugins:maven-dependency-plugin:3.2.0:get -Dartifact=org.cloudfoundry:cloudfoundry-client:5.5.0.RELEASE
./mvnw clean install
```
> You must download the cloudfoundry-client artifact in order for [NativeHintsTest](src/test/java/io/pivotal/cfapp/hints/NativeHintsTest.java) to successfully complete test execution.

This produces a library artifact that is consumed as a dependency by the Spring AOT Maven plugin inside the [native](https://github.com/pacphi/cf-butler/blob/master/pom.xml#L543) Maven [profile](https://maven.apache.org/guides/introduction/introduction-to-profiles.html) of [cf-butler](https://github.com/pacphi/cf-butler-hints)'s [pom.xml](https://github.com/pacphi/cf-butler/blob/master/pom.xml). 

## Status

**Under active development**. Functional, curated collection of [Spring Native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started) [hints](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#native-hints) is still being [figured out](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#troubleshooting).