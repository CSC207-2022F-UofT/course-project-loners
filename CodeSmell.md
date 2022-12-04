# Checking for Code Smell 
## Bloaters
### Long Method
If a method is too long, there can be redundancy in the code.
fetchAddressFromId method is long. Because it has much overlap with fetchFromId method, we used it to shorten the length of fetchAddressFromId method
sendToId method in DataSendControl is quite long.  
edit in EditProfile is quite long.   
returnObjListAsProfile method in ObjectListToProfile is quite long.  
withInPreferredLocation method in PreferredLocationConnector is quite long.  
checkValidate method in RegChecker is quite long.  
UIs tend to have longer methods, but it is inevitable because they need to display many components
### Long Parameter List
More than three or four parameters.
Only checkIfMissing method in RegChecker contained more than 4 paramter inputs.
We checked other classes did not have any method that contains more than 4 paramters. (We do not include constructors because we need certain number of inputs to generate an instance related to Profile)
### Large Class 
A class contains many methods, lines of code.  
DataFetchControl and DataSendControl classes are large.
EditProfileControl is also large
Authenticator class has many methods, but this is inevitable considering the job of the class. It has to validate many properties of the user's data. Each method should be associated to each data. If we try to put them into one method, it can have a large parameter list. We won't be able to validate a single property so it will also be less flexible.  
ObjectListToProfile is a bit large.  
RegChecker is a bit large
### Data Clumps
Similar group of variables in many places.
In our code, we don't have a group of vairables that is shared in many classes.  
The major groups of variables used throughout the project are profile and preferences which we defined as Entity,

### Primitive Obsession
Using many primitives for simple tasks.  
In EditProfileControl, the profile data is store in a String variable. 
There is also a String variable in DataSendControl that represents the profile info.

## Object-Orientation Abusers
### Switch Statements
In our case, EditProfile contains a sequence of if-statements. This is inevitable because the validation differs between each property of the profile information.

### Temporary Field
Variables are defined in a class scope and only used in one method
We found some variables defined in a class scope but never used, so we deleted them.  
Variables representing UIs in UIController were redundant so we deleted them.  
For other classes, they do not have variables in a class scope that are only used once.

### Refused Bequest
Superclass and subclass being completely different.
In our case, since each senario has their independent role, we do not have inheritance relationships between classes.
ConnectProfilesOutputBoundary and DataSendAccess interfaces are used and they share the same purpose with classes that implement them.

### Alternative Classes with Different Interfaces
We do not have this problem since there are no two classes that perform the identical action.

## Change Prevernters
### Divergent Change
You have to change many unrelated methods when you want to slightly modify a class
Our classes basically have sole responsibility and usually have one methods in it. 
Classes that have multiple methods are RegChecker, ObjectListToProfile, DataSendControl, DataFetchControl.
For DataSendControl and DataFetchControl, each method has their sole responsibility and purpose so they are not affected by slight changes made on their classes.

### Shotgun Surgery
When you want to modify the code slightly, you have to modify many classes.
This is caused by the violation of single reponsibility problem.  
In our documentation in SOLID, we wrote how we follow the single responsibility problem.

### Prallel Inheritance Hierarchies
When you want to create a subclass for one class, you need to create subclasses for other classes.
We do not need to create a subclass for our code so we do not have this problem.

## Dispensable
### Comments
Too many comments.
None of our classes have excessive amount of comments.

### Duplicate Code
### Lazy Class
Some classes are not really used.
Each of our classes has their responsibility in each senario, so we do not have problems with this.

### Data Class
Some classes only contain data that will be used by other classes.
Each of our classes has methods that play role in each senario so there are not data classes. 
Profile and Preferences might seem to be data classes but they are Entity and need to be defined in single classes.

### Dead Code
Variables, methods are no longer used.
We do not have this problem because if there are such variables or methods, IntelliJ will notify us.

### Speculative Generality
Never used methods, classes.
Similar to above, we passed checks of IntelliJ

## Couplers
### Feature Envy
A method accessing other data more than its own.

### Inappropriate Intimacy
One class uses another provate fields or methods
Each class calls public or static methods of another class.
Some class may use attributes of another class which should not be accessed directly. Instead, get() and set() methods should be defined if needed.

### Message Chains
A client requests another object but the object also requests a different object and so on.  
As long as the code follows clean architecture, we don't have this problem. The clean architecture is discussed in CleanArchitecture.md

### Middle Man
A class does one job and delegate the work to another class.
We do not have this problem because each of our classes has a reponsibility that cannot be performed by other classes
