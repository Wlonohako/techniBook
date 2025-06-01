# Technibook

Technibook is a social media web application built using Spring Boot and MySQL. It allows users to register, post content, join groups, send messages, and interact via real-time communication (WebSockets). The platform supports common social media features such as friend requests, post visibility, comments, and reactions.

---

## 1. Project Overview

Technibook is designed to serve as a modern social networking platform that implements:

-   User registration, authentication, and profile management
-   Post creation with visibility options (public, private, friends-only)
-   Comments on posts, including threaded (nested) comments
-   Friendship system (requests, acceptance)
-   Groups with role-based permissions (Member, Moderator, Admin)
-   Real-time chat via WebSockets
-   RESTful API and containerized deployment

---

## 2. API Endpoints Documentation

### 🔐 Authentication

-   `POST /api/auth/register`  
    **Request:** `{ email, password, name, surname, birthDate, sex }`  
    **Response:** `201 Created`

-   `POST /api/auth/login`  
    **Request:** `{ email, password }`  
    **Response:** `{ token, userId }`

---

### 👤 Users

-   `GET /api/users/{id}`  
    **Response:** User details (excluding password)

-   `PUT /api/users/{id}`  
    **Auth required**  
    **Request:** `{ name, surname, birthDate, sex }`  
    **Response:** Updated user

---

### 📝 Posts

-   `GET /api/posts`  
    **Response:** List of posts (filtered by visibility)

-   `POST /api/posts`  
    **Request:** `{ content, visibility, groupId (optional) }`  
    **Response:** Created post

-   `PUT /api/posts/{id}`  
    **Auth required & owner**  
    **Request:** `{ content, visibility }`  
    **Response:** Updated post

-   `DELETE /api/posts/{id}`  
    **Soft deletes a post**

---

### 💬 Comments

-   `POST /api/comments`  
    **Request:** `{ postId, content, parentCommentId (optional) }`

-   `GET /api/posts/{id}/comments`  
    **Response:** Threaded comments for post

---

### 👥 Friends

-   `POST /api/friends/request`  
    **Request:** `{ friendId }`  
    **Response:** `Friend request sent`

-   `POST /api/friends/accept`  
    **Request:** `{ requestId }`  
    **Response:** `Friend request accepted`

-   `GET /api/friends`  
    **Response:** List of accepted friends

---

### 🏘️ Groups

-   `POST /api/groups`  
    **Request:** `{ title, description }`  
    **Response:** Created group

-   `GET /api/groups`  
    **Response:** List of groups

-   `POST /api/groups/{groupId}/members`  
    **Request:** `{ userId, permission }`  
    **Only admin/moderator**

---

### 📥 Messages (WebSocket)

**WebSocket Endpoint:** `/ws/chat`

-   Users can connect via WebSocket using their JWT token
-   After connection, send message payloads:
    ```json
    {
        "receiverId": 5,
        "content": "Hey, how are you?"
    }
    ```

## 3. 🛠️ Technologies Used

-   **Spring Boot** – Java-based backend framework for building REST APIs
-   **MySQL** – Relational database for persistent data storage
-   **WebSocket** – Enables real-time chat functionality
-   **Docker** – Containerization for consistent deployment
-   **JWT (JSON Web Token)** – Secure authentication mechanism

<sub><i>Note: Some elements of the project may change during development.</i></sub>