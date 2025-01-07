# Spring IoC and Dependency Injection (DI)

## Why IoC?
In traditional Java development, we create objects using the `new` keyword. This approach tightly couples the object creation process to the application code. Spring introduces **Inversion of Control (IoC)** to take control of object creation, management, and destruction, thereby reducing coupling and improving flexibility.

## Introduction to IoC
- **Inversion of Control (IoC)** refers to transferring the responsibility of object creation, management, and destruction from the developer to the IoC container in Spring.
- The container takes control over the lifecycle of objects, hence the term "Inversion of Control."
- **Dependency Injection (DI)** allows objects to receive their dependencies where they are required.

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

