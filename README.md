**Media Player Application**

**Objective:** Develop a media player application that demonstrates advanced Java programming concepts, including SOLID
principles, clean architecture, Factory pattern, Singleton pattern, Decorator, and Observer pattern. (Pick and choose
based on your reference)

---

### **Features Overview**

The Media Player application is designed to manage collections of media files and users. Each user can create and manage
their own playlists. The application enforces certain access controls, allowing only administrators to modify the core
media file and user collections.

---

### **Functional Requirements**

1. **User Management:**
    - Users can create multiple playlists.
    - Only administrators can add, remove, update, or delete users and media files.
2. **Playlist Management:**
    - Users can add, remove, and manage media files within their playlists.
    - Supported actions on media files include playing, pausing, and stopping.
    - Each media file type has additional adjustable properties during playback:
        - **Video Files:** Volume and brightness can be adjusted.
        - **Audio Files:** Volume and sound effects can be adjusted.
3. **Media File Types:**
    - **Audio**
    - **Video**
4. **Error Handling:**
    - Ensure proper exception handling with meaningful messages to the user.

---

### **Design Requirements**

1. **Architecture:**
    - Implement a **clean architecture** ensuring separation of concerns between application layers (e.g., UI, business
      logic, and data access).
    - Follow **SOLID principles** to enhance the maintainability and scalability of the application.
2. **Patterns to Implement:**
    - **Factory Pattern:** To handle media file creation (Audio/Video).
    - **Singleton Pattern:** To manage global resources like media libraries or user repositories.
    - **Observer Pattern:** To notify components about state changes (e.g., media playback state).
    - PS: Feel free to use other patterns as well


### **Testing Requirements**
- Remember try your best not to write anything in Main.java instead Write unit and integration tests 