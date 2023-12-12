# CV Builder Android App

This Android app allows users to input and store personal information for building a Curriculum Vitae (CV). Users can enter details such as roll number, name, CGPA, degree, gender, date of birth, and interests. The app utilizes a SQLite database to store and retrieve user data.

## Features

- **User Input Form:**
  - Enter personal information including roll number, name, CGPA, degree, gender, date of birth, and interests.
  - Date of birth selection using a DatePickerDialog.

- **Save and View Data:**
  - Save entered information to an SQLite database using a DBHelper class.
  - View the saved data in a readable format using an AlertDialog.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/cv-builder-android.git
   ```

2. **Open in Android Studio:**
   Open the project in Android Studio and build the app.

3. **Run on Emulator or Device:**
   Run the app on an emulator or connect an Android device and run the app.

## Usage

1. **Enter Personal Information:**
   - Launch the app and fill in the required fields.
   - Choose a degree from the spinner.
   - Select a gender using radio buttons.
   - Pick a date of birth using the provided DatePickerDialog.
   - Check interests using checkboxes.

2. **Save Information:**
   - Click the "Save Information" button to store the entered data in the SQLite database.

3. **View Stored Information:**
   - Click the "View CVs" button to see a list of stored CVs in an AlertDialog.

## Contributing

If you find any issues or have suggestions for improvement, feel free to open an issue or create a pull request.


## Acknowledgments

- This app uses the Android `DatePickerDialog` for easy date selection.
- Special thanks to [your-name] for contributing to the project.

