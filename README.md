<p align="center">
  <a href="" rel="noopener">
 <img width=200px height=200px src="https://drive.google.com/uc?export=view&id=1EG0CbtlKIrpZ67XrJta1t4_9dkNRV4gm" alt="Teman Tanam logo"></a>
</p>

<h3 align="center">Teman Tanam</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/kylelobo/The-Documentation-Compendium.svg)](https://github.com/kylelobo/The-Documentation-Compendium/pulls)
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> Teman Tanam is a Bangkit Capstone Project which the team C23-PS029 made. 
  There's three main part of this project which is the Mobile Development part, Machine Learning part, and 
  Cloud Computing part according to the Bangkit Learning path. This repository only covers the Mobile Development part of the application.
  For more information about the other part, please visit repository below<br>
  <a href = "https://github.com/priscillardine04/ML-Capstone-Project"> Machine Learning Repository </a> <br>
  <a href = "https://github.com/debbyfrandina/CC_Teman-Tanam"> Cloud Computing Repository </a>
    <br> 
</p>

## 📝 Table of Contents
- [About](#about)
- [Deployment](#deployment)
- [Usage](#usage)
- [Built Using](#built_using)
- [Contributing](../CONTRIBUTING.md)
- [Authors](#authors)
- [Acknowledgments](#acknowledgement)

## 🧐 About <a name = "about"></a>
Recently, many farmers felt disadvantaged because of crop failures caused by erratic weather and diseases that attack plants. 
In addition, many people also have an interest in agriculture but lack an understanding of which plants are suitable for the existing soil and weather conditions. 
Crop failure is a major problem in the world of agribusiness, so the best solution is needed in terms of disease detection, weather, soil recommendations, fertilizers, and serum for these plants to thrive. 
The plant planting assistant system contains recommendations for soil, fertilizer, plants serum, and adjusting plant maintenance schedules. This application can also detect damage to plants, provide weather forecast information, and provide crop recommendations based on soil nutritional conditions and climate conditions. 
The Planting Assistant System can be used at any time to assist users, helping to increase efficiency and assisting productivity in agriculture.

- Machine Learning: Collect and Reduce data, Preprocessing and Splitting data, Building models using CNN, Mobile net for plant disease dataset and Random Forest for crop recommendation dataset, Tuning the model, Try different algorithm and architecture for the model, Make predictions about whether or not a plant will be healthy and recommend plants according to environmental conditions, and Convert to TensorFlow lite and pickle format. 
- Mobile Development: Deployment of the TFLite model and Model that deployed in the GCP. Using Firebase Authentication for the authentication mechanism of the application. The application uses Jetpack Compose for the UI development.
- Cloud Computing: Develop the backend database, Develop API to Connect with the Backend Database, and Test the API and Algorithms

The result is that our application called Teman Tanam provides 2 features, namely detecting plant diseases, providing plant recommendations based on an analysis of environmental conditions, and displaying related plant information.

## 🎈 Usage <a name="usage"></a>
There's two main features of our application, which is detecting plant disease and analyze environment and give the recommended crops for the environment. They can 
be accessed through the home screen of the application.
![HomeScreen](https://drive.google.com/uc?export=view&id=1megR5F_sIfnLfqKP8ELccIxu0EnVwQQR)

1. Detect Plant Disease
The application will detect whether the plant is healthy or sick and give the name of what disease does the plant has. It uses CameraX image analyzer and TFLite model behind it. It works in realtime so we just need
to point the camera to any crops and the result will show under.
![DetectDisease](https://drive.google.com/uc?export=view&id=1EgLIy6mLqGD_lxKEjoTtRXmII1v05vaJ)

2. Environment Analyze
Users need to fill the field and the application will show what crop suits the environment condition that the user input. It uses and API and machine learning model that deployed in the GCP. Not all field must be filled
by the users. The Natrium, Phosporus, and Kalium field is an optional, but the other field has to be filled.
![AnalyzeEnvironment](https://drive.google.com/uc?export=view&id=1zHWqAmnsKZLJHW0GZ2x1gkcSAlKp_YKU)<br>
Here's the result. It shows some information according to the crops, for example the name of the crop, latin name, description, and some information about
the optimum environment condition of the crops to growth.<br>
![AnalyzeEnvironment](https://drive.google.com/uc?export=view&id=1--JdGBlFvTV13D_cWy8w4ZqfuklBbs5q)

## 🚀 Deployment <a name = "deployment"></a>
To try this application, users just need to install the .apk file. But make sure the operation system is Android. Here's the link to the .apk file
[APK](https://github.com/firoos18/Capstone-Project/releases/tag/v1.0.0).

## ⛏️ Built Using <a name = "built_using"></a>
- [Kotlin](https://kotlinlang.org/) - Android Development
- [Firebase](https://firebase.google.com) - User Authentication
- [TensorFlow Lite](https://www.tensorflow.org/lite) - Machine Learning Deployment
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android UI Development

## ✍️ Authors <a name = "authors"></a>
- [Naufal Firoos Asy Syarif](https://github.com/firoos18) - Mobile Developer
- [Qanita Zafa Ariska](https://github.com/qanitazf) - Machine Learning
- [Muhammad Hafizh Rachman](https://github.com/mhafizhr21) - Machine Learning
- [Priscilla Ardine Puspitasari](https://github.com/priscillardine04) - Machine Learning
- [Vito Ahmad Husein](https://github.com/vhzrd) - Cloud Computing
- [Angelica Debby Frandina](https://github.com/debbyfrandina) - Cloud Computing

## 🎉 Acknowledgements <a name = "acknowledgement"></a>
- Hat tip to anyone whose code was used
- Inspiration
- References
