# Flutter BLoC Generator Plugin

A powerful **Android Studio & IntelliJ IDEA** plugin that automatically generates complete Flutter BLoC pattern files with proper structure and best practices.

## ✨ Features

- 🚀 **Complete BLoC Pattern Generation** - Creates all necessary files for a feature
- 🎯 **Smart Naming Conventions** - Automatically converts input to proper Dart naming
- 📱 **Modern Flutter Support** - Uses latest BLoC patterns and best practices
- ⌨️ **Keyboard Shortcuts** - Quick generation with `Ctrl+Alt+B`
- 🖱️ **Context Menu Integration** - Right-click anywhere in your project
- 🤖 **Android Studio Native** - Perfect integration with Android Studio's Flutter development
- ✅ **Input Validation** - Prevents invalid feature names
- 🏗️ **Feature-based Architecture** - Organized file structure

## 📁 Generated Structure

```
your_feature/
├── bloc/
│   ├── your_feature_bloc.dart      # Main BLoC with event handlers
│   ├── your_feature_event.dart     # Event definitions
│   └── your_feature_state.dart     # State definitions  
├── models/
│   └── your_feature_model.dart     # Data model with JSON support
└── your_feature_screen.dart        # Complete UI implementation
```

## 🚀 Installation

### Method 1: Install in Android Studio (Recommended)
1. **Open Android Studio**
2. Go to **File → Settings** (Windows/Linux) or **Android Studio → Preferences** (Mac)
3. Navigate to **Plugins**
4. Click **⚙️ → Install Plugin from Disk**
5. Select the generated `.zip` file from `build/distributions/`
6. **Restart Android Studio**

### Method 2: Install in IntelliJ IDEA
1. Go to **File → Settings → Plugins**
2. Click **⚙️ → Install Plugin from Disk**
3. Select the generated `.zip` file from `build/distributions/`
4. Restart IntelliJ IDEA

### Method 3: Build from Source
```bash
git clone <your-repo>
cd flutter-bloc-generator
./gradlew buildPlugin
```

## 📖 Usage in Android Studio

### 🖱️ Method 1: Context Menu (Recommended)
1. **Open your Flutter project** in Android Studio
2. **Right-click** on any folder in the **Project view** (e.g., `lib/`, `lib/features/`)
3. Select **"Generate BLoC Pattern"** from the context menu
4. Enter your feature name (e.g., `home`, `user_profile`, `product`)
5. Complete BLoC structure is generated instantly! ✨

### ⌨️ Method 2: Keyboard Shortcut
- Press **`Ctrl+Alt+B`** (Windows/Linux) or **`Cmd+Alt+B`** (Mac)
- Enter feature name in the dialog
- Files are generated in the currently selected directory

### 📂 Method 3: New Menu
- Go to **File → New → Generate BLoC Pattern**
- Or **Right-click in Project view → New → Generate BLoC Pattern**
- Enter feature name and choose destination folder

### 🔧 Method 4: Tools Menu
- Go to **Tools → Generate BLoC Pattern**
- Enter feature name in the dialog

## 🤖 Android Studio Specific Features

### **Perfect Flutter Integration**
- ✅ **Works seamlessly** with Android Studio's Flutter plugin
- ✅ **Recognizes Flutter project structure** (`lib/`, `test/`, etc.)
- ✅ **Integrates with Android Studio's** file navigation and project view
- ✅ **Code completion** works immediately with generated files
- ✅ **Syntax highlighting** for all generated Dart files

### **Android Studio Project Structure**
```
📦 your_flutter_project/
├── 📂 android/                     # Android native code
├── 📂 ios/                         # iOS native code  
├── 📂 lib/                         # Flutter Dart code
│   ├── 📂 features/                # Generated BLoCs go here
│   │   ├── 📂 home/                # Example: home feature
│   │   │   ├── 📂 bloc/
│   │   │   │   ├── 📄 home_bloc.dart
│   │   │   │   ├── 📄 home_event.dart
│   │   │   │   └── 📄 home_state.dart
│   │   │   ├── 📂 models/
│   │   │   │   └── 📄 home_model.dart
│   │   │   └── 📄 home_screen.dart
│   │   ├── 📂 profile/             # Example: profile feature
│   │   └── 📂 settings/            # Example: settings feature
│   ├── 📂 shared/                  # Shared components
│   ├── 📂 core/                    # Core utilities
│   └── 📄 main.dart
├── 📂 test/                        # Test files
├── 📄 pubspec.yaml                 # Dependencies
└── 📄 README.md
```

## 💻 Requirements

### **For Android Studio:**
- ✅ **Android Studio** (any recent version: Arctic Fox, Bumblebee, Chipmunk, Dolphin, Electric Eel, Flamingo, Giraffe, Hedgehog, Iguana, etc.)
- ✅ **Flutter Plugin** installed and enabled in Android Studio
- ✅ **Dart Plugin** installed and enabled in Android Studio
- ✅ **Flutter SDK** configured in Android Studio
- ✅ **Java 17** or higher

### **For IntelliJ IDEA:**
- ✅ **IntelliJ IDEA 2023.2+** Community or Ultimate Edition
- ✅ **Flutter Plugin** installed
- ✅ **Dart Plugin** installed
- ✅ **Java 17** or higher

## 🎯 What's Generated

### 🏗️ BLoC Class (`feature_bloc.dart`)
```dart
class FeatureBLoC extends Bloc<FeatureEvent, FeatureState> {
  // Complete event handling setup
  // Loading, success, and error states
  // Proper async/await patterns
  // TODO comments for business logic
}
```

### 📨 Events (`feature_event.dart`)
```dart
abstract class FeatureEvent extends Equatable {
  // Base event class with Equatable
  // Load, Update, and Delete events
  // Proper props implementation
}
```

### 📊 States (`feature_state.dart`)
```dart
abstract class FeatureState extends Equatable {
  // Initial, Loading, Loaded, Error, Empty states
  // Type-safe state management
  // Equatable implementation
}
```

### 📦 Model (`feature_model.dart`)
```dart
class FeatureModel extends Equatable {
  // JSON serialization (fromJson/toJson)
  // copyWith method for immutability
  // Equatable implementation
  // All necessary data class methods
}
```

### 📱 Screen (`feature_screen.dart`)
```dart
class FeatureScreen extends StatelessWidget {
  // Complete Flutter widget
  // BlocProvider setup
  // BlocBuilder implementation
  // Error handling UI
  // Loading indicators
  // Refresh functionality
}
```

## 🛠️ Development

### Building the Plugin
```bash
# Clean build
./gradlew clean

# Build plugin for both Android Studio and IntelliJ IDEA
./gradlew buildPlugin

# Test in Android Studio
./gradlew runIde

# Verify plugin compatibility
./gradlew verifyPlugin
```

### Project Structure
```
src/main/
├── kotlin/com/ckr/blocgenerator/
│   ├── GenerateBlocAction.kt      # Main action handler
│   └── BlocFileGenerator.kt       # File generation logic
└── resources/META-INF/
    └── plugin.xml                 # Plugin configuration
```

## 🔧 Customization

You can easily modify the templates in `BlocFileGenerator.kt` to:
- Add more events/states (like `RefreshEvent`, `ClearEvent`)
- Include repositories or services layer
- Change naming conventions (camelCase vs snake_case)
- Add custom imports or dependencies
- Include additional Flutter widgets or components

## 🐛 Troubleshooting

### Plugin doesn't appear in Android Studio menu
- ✅ **Check plugin installation**: Settings → Plugins → Look for "Flutter BLoC Generator"
- ✅ **Verify plugin is enabled**: Make sure it has a checkmark
- ✅ **Restart Android Studio** completely after installation
- ✅ **Check Flutter project**: Make sure you have a Flutter project open
- ✅ **Try multiple locations**: Right-click in Project view, Editor, Tools menu

### Context menu doesn't show "Generate BLoC Pattern"
- ✅ **Right-click on folders**, not files (try `lib/` folder)
- ✅ **Check Android Studio version**: Should be fairly recent
- ✅ **Enable Flutter plugin**: Settings → Plugins → Flutter (should be enabled)
- ✅ **Try keyboard shortcut**: `Ctrl+Alt+B` as alternative

### Build errors when using generated files
- ✅ **Add BLoC dependencies** to `pubspec.yaml`:
  ```yaml
  dependencies:
    flutter: 
      sdk: flutter
    flutter_bloc: ^8.1.3
    equatable: ^2.0.5
  ```
- ✅ **Run flutter pub get** in Android Studio terminal
- ✅ **Restart Android Studio** after adding dependencies

### Plugin build fails
- ✅ **Ensure Java 17+** is installed
- ✅ **Check Gradle wrapper** version (8.5)
- ✅ **Clear Gradle cache**: `./gradlew clean`
- ✅ **Update Android Studio** to latest version

## 🚀 Quick Start Guide for Android Studio

1. **Install the plugin** using Method 1 above
2. **Open your Flutter project** in Android Studio
3. **Navigate to `lib/` folder** in Project view
4. **Right-click** → **"Generate BLoC Pattern"**
5. **Enter "home"** as feature name
6. **Check the generated structure** in Project view
7. **Add dependencies** to `pubspec.yaml`
8. **Run `flutter pub get`**
9. **Start using your BLoC** in your Flutter app! 🎉

### Example Integration:
```dart
// In your main.dart or app.dart
import 'features/home/home_screen.dart';

// Navigate to your generated screen
Navigator.push(
  context,
  MaterialPageRoute(builder: (context) => HomeScreen()),
);
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Test with both Android Studio and IntelliJ IDEA using `./gradlew runIde`
5. Commit your changes (`git commit -m 'Add amazing feature'`)
6. Push to the branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

## ⭐ Support

If you find this plugin helpful, please:
- ⭐ **Star the repository**
- 🐛 **Report bugs** via GitHub issues
- 💡 **Suggest features** for Android Studio or IntelliJ IDEA
- 📝 **Contribute improvements**
- 📢 **Share with other Flutter developers**
- 💬 **Join Flutter community discussions**

## 🌟 Compatibility

| IDE | Version | Status |
|-----|---------|--------|
| **Android Studio** | Arctic Fox+ | ✅ Fully Supported |
| **IntelliJ IDEA Community** | 2023.2+ | ✅ Fully Supported |
| **IntelliJ IDEA Ultimate** | 2023.2+ | ✅ Fully Supported |
| **VS Code** | N/A | ❌ Not Supported |

## 📞 Contact & Community

- **Author**: Nabin Rai
- **Email**: nabinrai.4017@gmail.com
- **GitHub**: [NabinRai4017](https://github.com/NabinRai4017)
- **Issues**: [Report bugs here](https://github.com/NabinRai4017/flutter-bloc-generator/issues)
- **Discussions**: [Feature requests & questions](https://github.com/NabinRai4017/flutter-bloc-generator/discussions)

---

**Made with ❤️ for the Flutter & Android Studio community**

**⚡ Boost your Flutter development productivity in Android Studio!** ⚡