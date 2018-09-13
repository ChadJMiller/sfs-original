# Credits 

https://github.com/gauthierj/dsm-webapi-client

This code starts with the above mentioned repository code and we want to give credit where it is due.

Code has been repackaged but remains largely the same.  The intent is to augment with additional apis.



# sfs
Synology file system

A java based Synology DSM Webapi client.



Roadmap :


# Build

Without tests: `gradle build -x test`
With tests: `gradle build`

## Integration Tests

Tests requires an actual Synology DSM server running with some existing account, share and folder structure.

# Usage

## Spring 

This is a Spring based library. It is supposed to be used within Spring based applications.

1. Import class `DsmWebapiClientConfig` in the application configuration
2. Provide implementations of follwing interfaces in the application context

**`DsmUrlProvider`**:  provides the URL of Synology DSM server.

Existing implementation: `PropertiesDsmUrlProvider`

This implementation retrieves the URL based on the following properties: 

- `dsm.webapi.scheme`
- `dsm.webapi.host`
- `dsm.webapi.port`

**`AuthenticationProvider`**: provides login and password

Existing implementation: `PropertiesAuthenticationProvider`

This implementation retrieves connection information based on the following properties:

- `dsm.webapi.username`
- `dsm.webapi.password`
- `dsm.webapi.session`

Existing implementation: `ConsoleAuthenticationProvider`

This implentation asks credentials in the console.

**`TimeZoneProvider`**: provides time zone

Existing implementation: `PropertiesTimeZoneProvider`

Retrieves time zone form the following property:

- `dsm.webapi.timeZone`

Existing implementation: `DefaultTimeZoneProvider`

Retrieves system default time zone.

`PropertySourcesPlaceholderConfigurer` if any  `PropertiesXXXProvider` is used.

**Example configuration**

```java
@Configuration
@Import(DsmWebapiClientConfig.class)
public class MyAppConfig {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new PropertiesAuthenticationProvider();
    }

    @Bean
    public DsmUrlProvider urlProvider() {
        return new PropertiesDsmUrlProvider();
    }

    @Bean
    public TimeZoneProvider timeZoneProvider() {
        return new PropertiesTimeZoneProvider();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    // ...
    // My app's own beans
    // ...
}
```
Finally, inject services needed in application classes: search for interfaces `XXXService` corresponding to implemented APIs.

```java
public class MyApplicationSerivce {

    @Autowired
    FileListService fileListService;

    // ...
    
    public void doStuff() throws Exception {
        // ...
        List<File> list = fileListService.list("/my-share");
        // ...
    }

    // ...
}
```
Integration tests are a good place to look for samples.

# Project structure and currently implemented APIs:

## Core module (`dsm-webapi-client-core`)
Contains all base code required to easily call DSM webapi.

It also implements the following APIs:
- SYNO.API.Info: `ApiInfoService`
- SYNO.API.Auth: `AuthenticationService`

## File station module (`dsm-webapi-client-filestation`)

Implements FileStation APIs:
- SYNO.FileStation.List: `FileListService` and `ShareListService`
- SYNO.FileStation.Info: `FileStationInformationService`
- SYNO.FileStation.Search: `SearchService`
- SYNO.FileStation.Download: `DownloadService`
- SYNO.FileStation.Upload: `UploadService`
- SYNO.FileStation.CreateFolder: `CreateFolderService`
- SYNO.FileStation.CopyMove: `CopyMoveService`
- SYNO.FileStation.Delete: `DeleteService`
- SYNO.FileStation.DirSize: `DirSizeService`
- SYNO.FileStation.Rename: `RenameService`

Future development :
- Missing FileStation APIs
- DownloadStation APIs
