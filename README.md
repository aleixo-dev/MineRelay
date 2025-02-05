

<p align="center">
  <img src="https://github.com/user-attachments/assets/c3e10c38-3e20-4266-be39-4fc29fec6edc">
</p>

MineRelay is a multi-faceted platform designed to bridge the gap between Minecraft (Paper) servers and mobile applications. 
It enables real-time interaction between a Minecraft server and its players, providing seamless integration with a Ktor-based API and Android mobile application.

The project is designed to display live data on connected players, such as their names and status, directly to a mobile app, enhancing player engagement and allowing for remote server monitoring. 
MineRelay enables server administrators to stay connected with their player base in real-time, making it easier to manage server activity and keep the community involved even when away from the game.

## **How it works**
Minecraft Server (Paper Plugin):
  - The plugin runs on a Minecraft Paper server and fetches online players' data at regular intervals.
  - It sends real-time updates (player names, status, etc.) via HTTP POST requests to the MineRelay API.
  - The plugin operates asynchronously to avoid disrupting the gameplay experience.

API Server (Ktor):
  - The Ktor-based API server receives the data sent from the Minecraft plugin.
  - It processes the incoming information and exposes endpoints that the Android mobile app can use to fetch player data.
  - The API is lightweight and scalable, designed to handle not just player data, but also future functionalities like player stats, server events, or additional game integrations.

Android Mobile App:
  - The Android app fetches data from the MineRelay API, showing real-time player statistics such as the list of currently connected players.
  - It provides an intuitive and interactive interface for server administrators and players to keep track of server status, even when they’re away from the game.
  - The app is designed to be lightweight, responsive, and to update in real-time, offering a dynamic user experience.

## **Key Features**
- Real-Time Player Tracking:
  View the list of players currently connected to your Minecraft server directly from your mobile device.
- Seamless Integration:
  Integrate Minecraft with an Android app through a simple API interface, making server management and monitoring easy and accessible.
- Scalability:
  Easily extend the system with more services in the future (e.g., player statistics, chat integration, or advanced event tracking).
- Offline Access:
  Even when you’re not in front of your Minecraft server, you can check player status and server health from anywhere using the mobile app.

## **Build and Run**
### 1. **Clone the Repository**
```bash
git clone https://github.com/yourusername/minerelay.git
cd minerelay
```
### 2. **Build the ktor Api Server**
```bash
cd backend
./gradlew build
./gradlew run
```
### 3. **Build and Run the Minecraft Plugin**
```bash
cd plugin
./gradlew build
```
### 4. **Build and Run Android Mobile Application**
 1. Navigate to the mobile directory and open it in Android Studio.
 2. Open Android Studio and select "Open" to open the mobile module.
 3. Build the app by selecting "Build > Make Project".
 4. Configure the API base URL in the app's NetworkConfig or Retrofit setup (make sure it points to the server where the Ktor API is running).
 5. Run the app on a device or emulator by selecting "Run > Run 'app'".

### **Development Notes**
- Minecraft Plugin: The plugin uses Paper API and HTTP requests to send player data to the API.
- API: Built with Ktor and Kotlin, the API is lightweight and can easily scale with new features.
- Android App: Built with Jetpack Compose and Kotlin, the app is optimized for real-time data updates.

### **Support**
If you have any questions or issues with the project, feel free to open an issue in the GitHub repository, and I'll be happy to assist.
