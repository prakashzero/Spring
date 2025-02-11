

# Job Application System

This is a simple Spring Boot-based job application system where users can add and view job posts. The system uses MVC architecture, interacting with controllers, services, and repositories.

## Features

- Add new job posts
- View all job posts

## Technologies Used

- Spring Boot
- Java
- Spring MVC
- Lombok (for model class annotations)

## Project Structure

The project follows a typical Spring Boot structure:
- **Controller**: Handles web requests and responses.
- **Service**: Business logic and data manipulation.
- **Repository**: Accesses and manipulates data.

### Files Overview

1. **JobController.java**
    - Handles web requests for job postings.
    - Maps requests for adding new jobs, viewing all jobs, and handling form submissions.

2. **JobService.java**
    - Contains the business logic to handle job postings.
    - Fetches all job postings and adds new ones.

3. **JobRepo.java**
    - Serves as the data layer, storing job posts in a list.
    - Fetches data and adds new job posts.

4. **JobPost.java**
    - Represents the model for a job post.
    - Contains attributes like postId, postProfile, postDesc, reqExperience, and postTechStack.

5. **JobApplication.java**
    - The main class that bootstraps the Spring Boot application.

---

## Key Concepts

### 1. DTO (Data Transfer Object)

**Definition**: DTO is a design pattern used to transfer data between layers of an application. It helps in encapsulating the data so it can be passed across various layers (Controller, Service, Repository) without exposing the details of the domain model.

In this project, `JobPost` could be used as a DTO when data is transferred between the user interface and backend services.

### 2. Model Attribute

**Definition**: A model attribute in Spring MVC is an object that holds data for views (HTML pages) to render. The data is often fetched from the service layer and then passed to the view through the controller.

In `JobController`, the `viewAllJobs` method uses the model to add the list of job posts as an attribute (`jobPosts`). This data is then displayed in the "viewalljobs" page.

```java
@GetMapping("/viewalljobs")
public String viewAllJobs(Model m) {
    List<JobPost> jobs = jobservice.getAllJobs();
    m.addAttribute("jobPosts", jobs);
    return "viewalljobs";
}
```

Here, `m.addAttribute("jobPosts", jobs)` adds the list of jobs to the model to be displayed in the view.

---

## How to Run

1. Clone the repository.
2. Ensure that Java and Maven are installed on your system.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the web interface at `http://localhost:8080`.

---

## Conclusion

This project demonstrates a simple MVC setup using Spring Boot where job posts can be added and viewed. The concepts of DTO and model attributes are essential for handling data transfer and interaction between layers in the application.

