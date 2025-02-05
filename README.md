# Spring IoC and Dependency Injection (DI)

## Why IoC?
In traditional Java development, we create objects using the `new` keyword. This approach tightly couples the object creation process to the application code. Spring introduces **Inversion of Control (IoC)** to take control of object creation, management, and destruction, thereby reducing coupling and improving flexibility.

## Introduction to IoC
- **Inversion of Control (IoC)** refers to transferring the responsibility of object creation, management, and destruction from the developer to the IoC container in Spring.
- The container takes control over the lifecycle of objects, hence the term "Inversion of Control."
- **Dependency Injection (DI)** allows objects to receive their dependencies where they are required.
  Here’s a detailed explanation and example of **Dependency Injection** using the provided files.

### What is Dependency Injection (DI)?

**Dependency Injection (DI)** is a design pattern used in Spring to decouple components and inject required dependencies at runtime. It enables objects to be supplied with their dependencies from an external source, such as a container (like Spring’s IoC container), instead of creating them manually within the class.

### Scenario in the Example

In this case, we have the following setup:
- **`Computer`**: An interface that defines a method `code()`.
- **`Desktop`** and **`Laptop`**: Two classes that implement the `Computer` interface.
- **`Bhanu`**: A class that depends on the `Computer` interface for calling the `code()` method.

The Spring container will manage the dependencies and inject the appropriate implementation (`Desktop` or `Laptop`) into the `Bhanu` class.

### Class Breakdown:

1. **`Computer` Interface**:
   This interface defines a method `code()` that will be implemented by both `Desktop` and `Laptop`.

   ```java
   package org.example;

   public interface Computer {
       public void code();
   }
   ```

2. **`Desktop` Class**:
   This class implements the `Computer` interface and provides its own implementation of the `code()` method. It is annotated with `@Component("comp")`, meaning Spring will create an instance of this class and register it in the container under the name "comp".

   ```java
   package org.example;

   import org.springframework.stereotype.Component;

   @Component("comp")
   public class Desktop implements Computer {

       public Desktop() {
           System.out.println("Desktop object created");
       }

       @Override
       public void code() {
           System.out.println("Desktop class");
       }
   }
   ```

3. **`Laptop` Class**:
   Similar to `Desktop`, `Laptop` also implements the `Computer` interface. It is annotated with `@Component`, allowing Spring to create and manage its object automatically.

   ```java
   package org.example;

   import org.springframework.stereotype.Component;

   @Component
   public class Laptop implements Computer {

       public Laptop() {
           System.out.println("Laptop object created");
       }

       @Override
       public void code() {
           System.out.println("Laptop class");
       }
   }
   ```

4. **`Bhanu` Class**:
   This is the class that depends on the `Computer` interface. The `@Autowired` annotation on the `comp` field tells Spring to inject a `Computer`-type bean into this field. Since we have both `Desktop` and `Laptop` beans in the container, `@Qualifier("comp")` is used to specify that Spring should inject the `Desktop` instance (as it’s registered with the name "comp").

   The `@Value("24")` annotation is used to inject the value `24` into the `age` field.

   ```java
   package org.example;

   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Qualifier;
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.stereotype.Component;

   @Component
   public class Bhanu {
       @Autowired
       @Qualifier("comp")
       private Computer comp;

       @Value("24")
       private int age;

       public Bhanu() {
           System.out.println("Bhanu constructor called since object created");
       }

       public void code() {
           comp.code();  // Calls the Desktop's implementation of code()
       }
   }
   ```

### How Spring Injects Dependencies:

- **Component Scanning**: Both `Desktop` and `Laptop` are annotated with `@Component`, allowing Spring to automatically discover and register these classes as beans in the container during component scanning.

- **Autowired**: In the `Bhanu` class, the `@Autowired` annotation is used to inject the `Computer` implementation. Spring will try to inject an instance of `Computer` into the `comp` field of the `Bhanu` class.

- **Qualifier**: Since there are two implementations of `Computer` (`Desktop` and `Laptop`), Spring needs to know which one to inject. The `@Qualifier("comp")` annotation tells Spring to inject the bean named "comp", which is the `Desktop` class.

- **Value Injection**: The `@Value("24")` annotation injects the value `24` into the `age` field of the `Bhanu` class.

### Running the Code:

When the Spring container initializes, it follows these steps:
1. Spring creates an instance of the `Desktop` class and registers it under the name "comp" (due to the `@Component("comp")` annotation).
2. Spring creates an instance of the `Laptop` class and registers it in the container.
3. Spring creates an instance of the `Bhanu` class. During the creation of this object:
    - The `comp` field is injected with the `Desktop` object because of the `@Qualifier("comp")`.
    - The `age` field is set to `24` because of the `@Value` annotation.

When `bhanu.code()` is called, the `code()` method of the `Desktop` class will be invoked, and the output will be:

```plaintext
Desktop object created
Bhanu constructor called since object created
Desktop class
```

### Key Concepts:

- **Inversion of Control (IoC)**: Spring’s IoC container controls the creation and lifecycle of objects.
- **Component Scanning**: Automatically detects and registers classes annotated with `@Component`.
- **Autowiring**: Automatically injects required dependencies.
- **Qualifier**: Resolves ambiguity when multiple beans of the same type exist.
- **Dependency Injection (DI)**: Decouples object creation from usage, improving modularity and testability.

### Conclusion:

This example illustrates how Spring manages dependencies through autowiring and qualifier annotations. The `Bhanu` class depends on the `Computer` interface, and Spring injects the `Desktop` implementation. The configuration is flexible, allowing easy changes to injected dependencies by modifying annotations or the Spring context without altering the core logic of the class.

## IoC Container
- Spring provides containers like **BeanFactory** and **ApplicationContext**.
  - **BeanFactory** is now deprecated (starting from Spring 6).
  - **ApplicationContext** extends **BeanFactory** and provides additional functionalities.
- Common implementation classes of **ApplicationContext** include:
  - `ClassPathXmlApplicationContext`
  - `FileSystemXmlApplicationContext`
  - `AnnotationConfigApplicationContext`

### Configuring a Spring Project
You can configure a Spring project in the following ways:
1. **XML-based configuration**  
2. **Java-based configuration**  
3. **Annotations**  

### Example: XML-based Configuration
1. Create an XML file (e.g., `spring.xml`) in the `resources` directory.
2. Define a bean in the XML file:
   ```xml
   <bean id="exampleBean" class="com.example.ExampleClass" />
   ```
3. Load the context in the main class:
   ```java
   ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
   ExampleClass example = (ExampleClass) context.getBean("exampleBean");
   ```

### Accessing Beans
- **Inside `main` class**: Use `ApplicationContext` directly.
- **Outside `main` class**: Use `@Autowired` annotation to inject the dependency.

## Why Setter Injection?
Setter Injection allows you to configure properties of an object externally, typically using XML or annotations, rather than hardcoding them in the Java class. This improves flexibility and reusability.

### Example: Setter Injection
- Example:
  ```xml
  <bean id="person" class="com.example.Person">
      <property name="age" value="24" />
  </bean>
  ```
- Use `<property>` tag to set primitive or reference values.

## Why Constructor Injection?
Constructor Injection is used to ensure that required dependencies are injected when the object is created. This is especially useful for immutable objects or when you want mandatory properties to be set.

### Example: Constructor Injection
- Example:
  ```xml
  <bean id="person" class="com.example.Person">
      <constructor-arg index="0" value="24" />
  </bean>
  ```
- Use `<constructor-arg>` to pass values during bean initialization.

## Bean Scopes
Spring supports multiple bean scopes:
1. **Singleton** (default): 
   - A single instance of the bean is created when the container is loaded.
   - Subsequent requests return the same instance.
2. **Prototype**:
   - A new instance is created every time the bean is requested.
   - Beans are not created at container initialization but on `getBean()` calls.

## Why Autowiring?
Autowiring simplifies bean injection by letting Spring resolve dependencies automatically, reducing the need for explicit configuration.

### Autowiring Modes
- Modes:
  - **byName**: Matches the property name with the bean ID.
  - **byType**: Matches the property type with a bean type.
  - **constructor**: Resolves dependencies by constructor arguments.

### Example with `@Autowired`
- Define an interface and implementing classes:
  ```java
  public interface Computer {
      void compute();
  }

  public class Laptop implements Computer {
      public void compute() {
          System.out.println("Laptop computing...");
      }
  }

  public class Desktop implements Computer {
      public void compute() {
          System.out.println("Desktop computing...");
      }
  }
  ```
- Configure beans in XML:
  ```xml
  <bean id="laptop" class="com.example.Laptop" />
  <bean id="desktop" class="com.example.Desktop" />
  ```
- Use `@Autowired`:
  ```java
  @Autowired
  private Computer computer;
  ```

## Handling Conflicts in Autowiring
- Use `primary` attribute:
  ```xml
  <bean id="laptop" class="com.example.Laptop" primary="true" />
  ```

## Why Lazy Initialization?
Lazy Initialization ensures that beans are created only when explicitly requested, rather than at container startup. This can improve startup time and resource usage.

### Example: Lazy Initialization
- By default, Spring initializes all singleton beans at container startup.
- To delay bean initialization, use `lazy-init`:
  ```xml
  <bean id="exampleBean" class="com.example.ExampleClass" lazy-init="true" />
  ```
- Useful when beans are not immediately required.

---

# Difference: Prototype vs Lazy Initialization
| Feature               | Prototype                              | Lazy Initialization                  |
|-----------------------|----------------------------------------|--------------------------------------|
| **Object Creation**    | Created only on `getBean()` call      | Created on explicit request          |
| **Scope**              | Each request creates a new instance   | Singleton by nature                  |
| **Dependencies**       | Dependencies resolved at runtime      | Dependencies created when invoked    |

---

