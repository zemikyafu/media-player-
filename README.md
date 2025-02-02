**Media Player Application**

**Objective:** Develop a media player application that demonstrates advanced Java programming concepts, including SOLID
principles, clean architecture, Factory pattern, Singleton pattern, Decorator, and Observer pattern. 

---

### **Features Overview**

The Media Player application is designed to manage collections of media files and users. Each user can create and manage
their own playlists. The application enforces certain access controls, allowing only administrators to modify the core
media file and user collections.

---
### **Design Requirements**

1. **Architecture:**
    - Implement a **clean architecture** ensuring separation of concerns between application layers (e.g., UI, business
      logic, and data access).
    - Follow **SOLID principles** to enhance the maintainability and scalability of the application.
2. **Patterns:**
    - **Factory Pattern:** To handle media file creation (Audio/Video).
    - **Singleton Pattern:** To manage global resources like media libraries or user repositories.
    - **Observer Pattern:** To notify components about state changes (e.g., media playback state).
   

### **Testing**
- Unit Testing with JUnit to validate individual components.
- Integration Testing with Mockito to simulate dependencies and ensure smooth interaction between components.
