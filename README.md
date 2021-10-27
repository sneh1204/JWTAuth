# JWTAuth
Using JWT and Express JS, Created a Profile Authentication App for Android. Project 5.

**An Android application where users can safely register and login and keep their account information secured.**

### Use cases -
- Account Authorization

### Libraries used - 
- Material Design UI
- ExpressJS
- JSONWebToken
- BCrypt
- MongoDB
- NodeJS
- JetPack

### Features -
- Authentication (Login, Register, Update and View Profile).
- Registration includes User's Fullname, Email, Password, Address, Age, Weight.
- You can update your account information easily.
- Keep your account information secured.

### Database design -
#### _Using MongoDB_
- 2 Collections - (**Auth, Profiles**)

- Auth contains the user authentication information.
> Data fields - email (string), pass (string)
- Profiles contains all of the User information.
> Data fields - fullname (string), email (string), age (number), weight (number), address (string)

## The API Schema:

Base URL - > https://mysterious-beach-05426.herokuapp.com/

* **POST** - auth/login - Authenticates a User and returns JWT Token. `Parameters - email, pass. (urlencoded)`
* **POST** - auth/signup2 - Registers a User profile and return JWT Token. `Parameters - email, pass, fullname, address, age, weight. (urlencoded)`
* **GET** - profile/view - View your User profile. `Headers - Your valid unexpired JWT Token.`
* **POST** - profile/update - Updates your User profile. `Headers - Your valid unexpired JWT Token. Parameters - email, fullname, address, age, weight. (urlencoded)`
