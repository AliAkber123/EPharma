# E-Pharma

**An Android-based online pharmacy and laboratory-report access application**

E-Pharma is a university team project built with **Java**, **Android Studio**, and **Firebase**. It brings two healthcare-related tasks into one mobile application: browsing medicines through a shopping-cart interface and accessing laboratory reports using a test code.

The project demonstrates Android UI development, user authentication, cloud database integration, and handling user interactions such as cart updates and form validation.

> **Project scope:** E-Pharma is an academic prototype. The documented functionality includes medicine browsing and cart management; production payment processing, prescription verification, and delivery fulfillment are **not documented** as completed features.

## Project at a Glance

| Category | Details |
| --- | --- |
| Project | E-Pharma |
| Platform | Android |
| Language | Java |
| Development environment | Android Studio |
| Authentication | Firebase Authentication |
| Database | Firebase Realtime Database |
| Project type | University team project |
| Contributors | Ali Akber and Sheikh Sanjida Prova |

## Features

### 1. Registration and Login

- Welcome screen with navigation to the login interface.
- Registration form for username, email address, phone number, and password.
- Email/password sign-in using Firebase Authentication.
- Input validation and user-facing error feedback, as described in the project report.
- Logout option.

### 2. Medicine Browsing

- Browse medicines grouped by disease or medical category.
- View product information and prices.
- Select medicines to add to a shopping cart.

### 3. Shopping Cart

- Add medicines to the cart.
- Increase or decrease product quantities.
- Remove selected products.
- Recalculate the displayed total when quantities change.

**Basic calculation:** `item total = unit price × quantity`

### 4. Laboratory-Report Access

- Select a hospital from the provided list.
- Enter a unique test code to access a laboratory report, as documented in the project report.

> **Privacy consideration:** The report describes test-code-based retrieval but does not establish a production-ready authorization or patient-identity verification process. Such controls would be essential before using the application for real patient data.

### 5. Navigation

The main application menu provides access to **Medicines**, **Lab Reports**, **About**, and **Log Out**.

## Technology Stack

| Technology | Use in the project |
| --- | --- |
| Java | Android application logic |
| Android Studio | Development environment |
| Firebase Authentication | User registration and sign-in |
| Firebase Realtime Database | Cloud-hosted application records |
| Gradle | Android build and dependency management |
| AndroidX / Material Components | Android UI components |
| Glide | Image-loading library included in project dependencies |
| Butter Knife | View-binding library included in project dependencies |
| EventBus | Event-handling library included in project dependencies |

**Repository configuration:** The checked-in Gradle files specify Android Gradle Plugin **4.2.2**, `compileSdkVersion 30`, `minSdkVersion 19`, and `targetSdkVersion 30`. These are the original project settings, not a recommendation for a new production Android application.

## High-Level Architecture

```text
                    Android Application
                     (Java / Android UI)
                             |
                 +-----------+-----------+
                 |                       |
         Firebase Authentication   Firebase Realtime Database
          (user sign-in)           (application records)
                                         |
                              +----------+----------+
                              |          |          |
                            Users     Medicines    Cart
```

This diagram is a simplified representation based on the project report and repository dependencies. It is not a complete class-level architecture diagram.

## Application Workflow

**Account access**

```text
Welcome Screen → Register / Login → Main Menu
```

**Medicine browsing and cart**

```text
Main Menu → Medicines → Choose Category → Select Product
          → Add to Cart → Change Quantity / Remove Item → Updated Total
```

**Laboratory reports**

```text
Main Menu → Lab Reports → Select Hospital → Enter Test Code
          → Access Report (as described in the project report)
```

## Screenshots and Demo

The original **[Lab Report](Lab%20report.pdf)** contains screenshots of:

- Welcome, registration, and login screens (page 3).
- Main menu, medicine categories, and hospital list (page 4).
- Medicine catalog and shopping cart (page 5).
- Test-code entry and About screens (page 6).
- Firebase database snapshots (pages 7–8).

**[Watch the project demonstration video](E-Pharma.mp4)**

> The screenshots in the original report are historical demonstrations of the academic project. They should not be treated as evidence that the app currently has a deployed production backend.

## Project Structure

```text
EPharma/
├── app/                  # Android app module and source files
├── gradle/wrapper/       # Gradle wrapper files
├── build.gradle          # Root build configuration
├── settings.gradle       # Project/module configuration
├── gradlew               # Gradle wrapper (Unix/macOS)
├── gradlew.bat           # Gradle wrapper (Windows)
├── Lab report.pdf        # Project report with screenshots
├── Project Proposal.pdf  # Original project proposal
├── E-Pharma.mp4          # Application demonstration
└── README.md             # Project documentation
```

## Getting Started

This is an older academic Android project. Building it may require an Android Studio/Gradle/JDK combination compatible with the checked-in configuration.

1. **Clone the repository:**

   ```bash
   git clone https://github.com/AliAkber123/EPharma.git
   cd EPharma
   ```

2. **Open the project in Android Studio.** Open the repository folder and allow Gradle synchronization to finish.

3. **Configure Firebase for your own environment.** Create or select a Firebase project, register an Android application matching the Gradle application ID (`com.example.e_pharma`), enable the required authentication and database services, and supply the Android Firebase configuration file at `app/google-services.json` if it is not present or suitable for your environment.

4. **Review database access rules and sample data** before testing. The app may depend on Firebase records and settings from the original development environment; their availability in a new Firebase project is not guaranteed.

5. **Run the app** using a compatible Android emulator or device.

**Security:** Do not commit service-account keys, private credentials, or real patient information. Firebase client configuration is not a substitute for secure authentication, authorization, and database rules.

## Engineering Experience

This project provided hands-on experience with:

- Developing an Android application with multiple screens and user workflows.
- Connecting a mobile interface to Firebase services.
- Implementing form handling and account-related navigation.
- Managing medicine and shopping-cart data in a cloud-backed application.
- Updating on-screen prices in response to quantity changes.
- Working collaboratively on a university software project.

## Future Improvements

The original report proposed:

- Doctor appointment scheduling at selected hospitals.
- Google Maps integration for customer location.
- Ambulance-request functionality.

Additional improvements worth considering before any real-world deployment include:

- Stronger patient authentication and report access controls.
- A security review of database rules and user-data storage.
- Prescription validation and a complete order/fulfillment workflow.
- Automated testing, clearer loading/error states, and accessibility improvements.
- Updating Android dependencies and target SDK versions.

## Project Contributors

This academic project was developed collaboratively by:

- **Ali Akber**
- **Sheikh Sanjida Prova**

See the original [Lab Report](Lab%20report.pdf) for the submitted project overview and screenshots.

---

**E-Pharma** — Exploring how mobile development and cloud services can make everyday healthcare tasks more convenient.
