# Spring POC

This POC is to demonstrate ConversionService and ServiceLocator can be used to simplify creating mappers and services based on specific criteria.

## ConversionService
The basics for Conversion service are baked into spring. The only thing developers need to do is create a class per converter annotated with `@Component` that meets this interface:
```java
public interface Converter<T, K>{
    public K convert(T source);
}
```

The annotation marks the class as a component, and is found in the class path automatically by spring on instantiation. It is then added to a list of components meeting the `Converter<T,K>` interface and presented in the conversion service.

## ServiceLocator

This pattern is a little more complicated. Here we are mimicing what ConversionService does, but for an interface of our own design. With it, we can add variety with very little impact to the rest of our code base.

To add functionality, a developer just needs to make a class annotated with `@Component` in the classpath, meeting the contract of the interface we desire. The class will be picked up automatically and our factory will be able to deliver the correct variety based on inputs.

## How these are demonstrated in the POC
### The overall POC
This POC is actually a pretty big waste of code. The user supplies a GET request to the service of the form `/convert/{value}?types=integer,long,float,double`, and get back a json payload with conversions of the types requested in the query parameters. Simple casting and serialization.

### POC for ConversionService
Here, I used the conversion service to handle the casting of the values. You send in the value from the URL (it's captured as a `String`) and it returns the boxed type requested. There is a switch-case to resolve the requested type to a java `Class`, but other than that, the code is just a call to the service.

### POC for ServiceLocator
Here, I used the ServiceLocator pattern to handler serialization. The JSON payload returns the values in the form of
```json
{"numbers":[
    {"integer":value}
]}
```
where the objects inside the array are the requested types. The code is virtually the same, but the key in the object differs by type. The serializer essentially just writes the object with a specific key and the value of the supplied number (after it's been cast and boxed to one of the number classes).

There are multiple parts here that need addressing. 
#### NumberSerializer
This is the interface we want to represent in multiple ways, using multiple different classes.
#### NumberSerializerFactory
This factory is an incredibly simple interface that returns our serializers. Spring will create the concrete backing class after being scanned by our configuration class
#### SerializerConfig
This is where the spring magic happens. We create a bean of the type `ServiceLocatorFactoryBean` and feed it our factory. From there, it creates a concrete class behind the scenes, and then apply any classes that meet the interface we expect to get back.

Notice that here we have tagged the configuration with the `@Configuration` and `@ComponentScan` annotations. The `@Configuration` tags this as a configuration that spring should use to do exactly that. The `@ComponentScan` says that spring should scan (recursively i believe) into the classpath of the folder the class is in, and any contained folders looking for classes marked `@Component`. This is why we tagged our serializers with `@Component`.