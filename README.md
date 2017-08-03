# FlowToObject

## What's FlowToObject

> **This is a pure application that converts a data stream to an object, and is committed to completing the conversion of the data stream to the object**

## How to use

under the package `com.nickchen.service` , there are two samples:
- `HotelService` (method with argument)
- `UserService` (without)

```java
/* HotelService.java */
@ServiceInterface(protocol = "http", baseUrl = "localhost:8080")
public interface HotelService {
    @ServiceMethod(value = "/user", method="get")
    Hotel getHotel(Map<String, Object> map);
}
```

`@ServiceInterface(protocol, baseUrl)` works on the class, `@ServiceMethod` works on the method.  

`HotelService` will requests *http://localhost:8080/user?x=xx&xx=xxx* and bind the return value to the return type of method **`getHotel`** .

**BUT! How to user HotelService???**  

*FlowToObject* should works with Spring framework, `@ServiceInterface` will be auto implemented and inject into Spring IOC, and then, just like use it like Spring's way.

```java
UserService userService = (UserService) applicationContext.getBean("userService");
```


