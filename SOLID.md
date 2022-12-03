# SOLID
## Single Responsibility Principle
Most of our classes only have constructors and one method. All the usecases classes follow the rule. Now, we inspect other classes.
* For entity classes; Preferences and Profile, we have get and set methods to prevent
  their attributes from unwanted modification.
* For controller classes, only DataFetchControl and DataSendControl classes have more than one method.
  This, however does not violate the single responsibility principle because all the methods have the same
  role, namely; fetching data and sending data.
* For UI classes, they are all implemented for the sole purpose of displaying and functioning the UI itself. So each of them
  only has one responsibility.  
  Thus, all of our classes follow the single responsibility principle.

## Open/Closed Principle
We do not have much concern about this principle because each of our UI has independent responsibility and functionality and
each of our controllers and use cases are attributed to each UI.  
One of the few examples that follow Open/Closed Principle is the UI controller.  
This class enables us to remove all the main methods in each UI class, and so we do not have to modify each UI class for the sake of
running them.

## Liskov Substitution Principle
We do not have any concern about this principle because we do not have inheritance relationships among our classes.

## Interface Segregation Principle

## Dependency Inversion Principle
We modified our code after Milestone4 in terms of Dependency Inversion Principle.  
Before the modification, some of our UI classes had Profile(Entity) instances in them.  
This was a violation of dependency inversion principle because if we modify the Profile, we have to also modify our UIs.  
Now, instead of using Profile instances to get the data of the user, we now use methods in DataFetchControl to get the data of the user. 
