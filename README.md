# Project Manager API
This is a simple spring boot REST API for managing projects and tasks. It allows users to create projects, add tasks to projects, view tasks, and update their status. 


### Technologies Used:
*   Spring Boot (REST API)
*   H2 (Database)

### Deployment and Running the API
1. Build and compile using maven

```bash
mvn clean install
```
2. Deploy using docker
```bash
docker-compose up --build
```
The backend will be available at http://localhost:8080


## Endpoints
1. **POST /projects** - Create a new project
2. **GET /projects** -  Retrieve all projects.
3. **GET /projects/{projectId}** - Retrieve a specific project by ID.
4. **POST /projects/{projectId}/tasks** - Add a new task to a specific project.
5. **GET /projects/{projectId}/tasks** - Retrieve all tasks for a project, with optional filters by status and dueDate.
6. *PUT /tasks/{taskId}** - Update a task’s details.
7. **DELETE /tasks/{taskId}** - Delete a task.
8. **GET /projects/summary** - Retrive each project tasks summary