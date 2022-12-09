# Clean Architecture

## DataAccess
We have dataaccess folder that contain FetchData and SendData classes that access the database.
This is inspired by the lecture slide "Example of Program With Clean Architecture", page15 in "04-CleanArchitecture.pdf"
(Lecture slide).
By doing this, we can access the database without using controllers or use cases, which do not violate Clean Architecture.

## Business Policies
We clearly defined Profile and Preferences as our Entities. 
We also have use cases accordingly to what we need.

## Layers
Our code is following the layers as you can see by the namings of the folders
controllers, dataaccess, entities, uis, usecases

### Dependency Rule
We checked imported classes in our Entities, Use Cases, Data Access, Controllers, and UIs.  
There was no violation in Entities and Use Cases classes.

### Direction of Arrows
All of our classes have dependency arrows pointing inward in terms of layers. Namely from UIs to Entities.
