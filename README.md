# Java Login Web App

A Java EE program where users can login and do very little. Admins can login and add new users.

## Requirements
- The application will store a number of valid users who will be able to login
- The system should have a reasonable way of dealing with invalid logins
- It looks amazing
- Login details are kept within a Java class or supported file
- Aspects of the application are restricted
- Sections of the website are view-able only by those with an admin role
- User details are kept on a database
- An admin is able to add new users to the database and assign them a role

## Achievements
- Valid users are stored within a datastore class
- Invalid logins are handled, a message is displayed prompting a correct login
- CSS has been used to create clean, consistant, webpages 
- Only admin users can access the add new users page
- The resources folder cannot be accessed

## Technology
### Java Server Faces
- JSFs are used to create the XHTML pages
- These pages communicate with the Java Beans - which provide the functionality

### Java EE Security
- Resource files are restricted
- User authentication uses an identity store to verify user roles

## User Guide
- Upon running the program, the index page is opened
- Users can log in to the website on this page
- Once logged in the user is taken to the welcome page, the user's name is displayed here
- If the user is an admin, they can access the admin page
- From the admin page, an admin can add new users 
