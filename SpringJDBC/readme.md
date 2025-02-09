
This **README.md** provides an overview of the project, explaining the purpose of different layers, annotations, and the usage of **JdbcTemplate**.
---

# Spring JDBC Student Management Project

This project demonstrates how to manage student records using **Spring JDBC** and an **H2 database**. It showcases the separation of responsibilities into multiple layers, each with a specific purpose, to make the application scalable and maintainable.

---

## Why Different Layers?

In a large application, it is important to connect to both the server and the database. We split the application into layers to simplify this process and improve code organization.

### Key Layers:

1. **DAO Layer (Data Access Object)**  
   This layer interacts directly with the database, handling queries and data storage. It’s also known as the **Repository Layer**.

2. **Service Layer**  
   The Service Layer focuses on business logic. It communicates with the DAO layer but is separate from direct database operations.

3. **Model Layer**  
   This layer represents the data entities in the system. Each entity is mapped to a table in the database.

4. **Client**  
   The client interacts with the Service Layer, which acts as a mediator between the client and the database.

---

### **Anatomy of the Layers**

1. **Model Layer (Student Class)**  
   The `Student` class defines the properties of a student: `rollNo`, `name`, and `marks`. This class represents a record in the database.

   ```java
   public class Student {
       private String name;
       private int rollNo;
       private int marks;
       // Getters and Setters
   }
   ```

2. **Service Layer (StudentService Class)**  
   The `StudentService` class processes data before interacting with the database through the DAO layer. It defines methods such as `addStudent()` and `getAllStudents()`.

   ```java
   public class StudentService {
       @Autowired
       Studentrepo studentrepo;

       public void addStudent(Student student) {
           studentrepo.save(student);
       }

       public List<Student> getAllStudents() {
           return studentrepo.findAll();
       }
   }
   ```

3. **Repository Layer (Studentrepo Class)**  
   The `Studentrepo` class interacts with the database using **JdbcTemplate** to execute SQL queries.

   ```java
   public class Studentrepo {
       private JdbcTemplate jdbc;

       @Autowired
       public void setJdbc(JdbcTemplate jdbc) {
           this.jdbc = jdbc;
       }

       public void save(Student student) {
           String sql = "insert into student (rollno, name, marks) values (?, ?, ?)";
           jdbc.update(sql, student.getRollNo(), student.getName(), student.getMarks());
       }

       public List<Student> findAll() {
           String sql = "select * from student";
           return jdbc.query(sql, new RowMapper<Student>() {
               @Override
               public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                   Student s = new Student();
                   s.setRollNo(rs.getInt("rollno"));
                   s.setName(rs.getString("name"));
                   s.setMarks(rs.getInt("marks"));
                   return s;
               }
           });
       }
   }
   ```

---

### Annotations Used

- **@Service**  
  Used in the Service Layer. It behaves similarly to **@Component**, but it is specifically meant for service classes that handle business logic.

- **@Repository**  
  Used in the Repository Layer to indicate that the class interacts with the database. This annotation is a specialization of **@Component**.

- **@Autowired**  
  Spring automatically injects beans (such as the `Studentrepo` or `JdbcTemplate`), making the code easier to manage.

---

## How JDBC Template Works

The **JdbcTemplate** is a Spring utility that simplifies database interactions. It abstracts boilerplate code and allows you to execute SQL queries with ease.

For example, when inserting data:
```java
String sql = "insert into student (rollno, name, marks) values (?, ?, ?)";
jdbc.update(sql, student.getRollNo(), student.getName(), student.getMarks());
```

For selecting data:
```java
String sql = "select * from student";
jdbc.query(sql, new RowMapper<Student>() {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student s = new Student();
        s.setRollNo(rs.getInt("rollno"));
        s.setName(rs.getString("name"));
        s.setMarks(rs.getInt("marks"));
        return s;
    }
});
```

---

### Why Do We Need RowMapper?

A **RowMapper** helps extract data from the database row by row. When using a **select** query, the results are stored in a `ResultSet`, and the **RowMapper** allows us to transform each row into a `Student` object.

---

### H2 Database Setup

In this project, we use an **H2 database** for simplicity. To set up the database schema and seed initial data, we create two files:

1. **schema.sql**  
   This file defines the database structure.
   ```sql
   create table student (
       rollno int primary key,
       name varchar(50),
       marks int
   );
   ```

2. **data.sql**  
   This file populates the database with initial student records.
   ```sql
   insert into student (rollno, name, marks) values (101, 'Kiran', 79);
   insert into student (rollno, name, marks) values (102, 'Harsh', 68);
   insert into student (rollno, name, marks) values (103, 'Sushil', 82);
   ```

---

## Questions & Definitions

1. **Why are different layers important?**  
   Different layers help in separating concerns: the **DAO layer** handles database interactions, the **Service layer** handles business logic, and the **Model layer** represents the actual data structure. This makes the application easier to maintain and scale.

2. **What is the role of the Repository layer?**  
   The repository layer handles **writing queries**, **getting responses from the database**, and **storing data into the database**.

3. **What is @Repository?**  
   The `@Repository` annotation is used to tell Spring that this class will interact with the database. It’s similar to the `@Controller` annotation in the MVC architecture.

4. **What is JdbcTemplate?**  
   **JdbcTemplate** is a tool that Spring provides to simplify the database operations like querying and updating data. It allows us to interact with the database without writing boilerplate JDBC code.

---
