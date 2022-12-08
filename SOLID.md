# SOLID
## Single Responsibility Principle
Most of our classes only have constructors and one method. All the usecases classes follow the rule. Now, we inspect other classes.
* For entity classes; Preferences and Profile, we have get and set methods to prevent
  their attributes from unwanted modification.
* For data access classes; FetchData and SendData classes have more than one method.
  This, however does not violate the single responsibility principle because all the methods have the same
  role, namely; fetching data and sending data.
* For controller classes, this principle is also satisfied. Each controller class has one responsibility represented by
  their class names.
* For UI classes, they are all implemented for the sole purpose of displaying and functioning the UI itself. So each of them
  only has one responsibility.  
  Thus, all of our classes follow the single responsibility principle.

## Open/Closed Principle 
One of the few examples that follow Open/Closed Principle is the UI controller.  
This class enables us to remove all the main methods in each UI class, and so we do not have to modify each UI class for the sake of
running them.

## Liskov Substitution Principle
Any aspect of our code follows this principle. Java interfaces such as ConnectProfilesOutputBoundary, FetchDataAccess,
and SendDataAccess have inherited classes but all of them follow this principle.  
All the classes that implement these interfaces have more functionality than the abstract interfaces and can be
substituted for those interfaces. Open/Closed Principle also applies to this.

## Interface Segregation Principle
We followed this principle in DataAccess. We initially had one class that fetches and sends data to the database. 
After our modification, we separated the functionality into two classes. 

## Dependency Inversion Principle
We modified our code after Milestone4 in terms of Dependency Inversion Principle.  
Before the modification, some of our UI classes had Profile(Entity) instances in them.  
This was a violation of dependency inversion principle because if we modify the Profile, we have to also modify our uis.  
Now, instead of using Profile instances to get the data of the user, we now use methods in DataFetchControl to get the data of the user. 
