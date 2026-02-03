# PlayTask (Android) 📱

**PlayTask** is a self-discipline and time management application designed to gamify productivity. Users can earn "task coins" by completing tasks and consume them for rewards, helping to overcome procrastination.

> **Note:** This project was originally developed as a coursework experiment and is currently undergoing modernization and refactoring.

## ✨ Features

- **Task Management:** Create, edit, and delete tasks (Shopping Items).
- **Gamification:** Earn/Spend mechanism (Logic in progress).
- **Map Integration:**
  - **Baidu Map SDK:** Location and navigation services.
  - **Tencent Map SDK:** Vector map display.
- **Tools:**
  - **News Browser:** Built-in WebView.
  - **Game View:** Interactive game fragments.

## 🛠️ Tech Stack

- **Language:** Java 8
- **Minimum SDK:** API 24 (Android 7.0)
- **Target SDK:** API 33 (Android 13)
- **Architecture:** Single Activity (MainActivity) + Multi-Fragment (ViewPager2 + TabLayout).
- **Data Persistence:** JSON (Gson) - *Refactored from legacy ObjectStream.*
- **UI Components:**
  - RecyclerView
  - ViewPager2
  - TabLayoutMediator
  - ConstraintLayout

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Garyko0730/AndoridCoding.git
   ```

2. **Open in Android Studio:**
   - Wait for Gradle sync to complete.

3. **Configure Map Keys:**
   - Note: The project uses Baidu/Tencent Map SDKs. Ensure you have valid API keys in `AndroidManifest.xml` (or configure them) to use map features.

4. **Run:**
   - Select `app` run configuration.
   - Deploy to Emulator or Physical Device.

## 🔄 Recent Refactoring (v2.0)

We are actively modernizing the codebase:
- **Code Cleanup:** Removed legacy commented-out logic in `MainActivity`.
- **Architecture:** Decoupled `ShopItemAdapter` from Fragments.
- **Storage:** Migrated from binary serialization (`.date`) to human-readable JSON (`.json`) using Gson.

## 📄 License

MIT License.
