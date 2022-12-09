# Checking for Code Smell 
## Bloaters
### Long Method
fetchAddressFromId method is long but should have overlap with fetchFromId
send_to_id method in DataSendControl is quite long  
uis tend to have longer methods, but it is inevitable because they need to display many components
fetchAddressFromId method is long. Since it has much overlap with fetchFromId method,
we used it to shorten the length of fetchAddressFromId method

### Long Parameter List
Only checkIfMissing method in RegChecker contained more than 4 parameter inputs. Since these parameters in RegChecker 
consist of Profile entity, it is unreasonable to separate them to create another class just for this class. 
We checked other classes did not have any method that contains more than 4 parameters. 
(We do not include constructors because we need certain number of inputs to generate an instance related to Profile)

### Large Class  
DataFetchControl and DataSendControl classes are large.
EditProfileControl is also large
Authenticator class has many methods, but this is inevitable considering the job of the class. 
It has to validate many properties of the user's data. Each method should be associated to each data.
If we try to put them into one method, it can have a large parameter list. 
We won't be able to validate a single property so it will also be less flexible.

### Data Clumps
In our code, we don't have a group of variables that is shared in many classes.  
The major groups of variables used throughout the project are profile and preferences which we defined as Entity, so 
there is no problem with it

### Primitive Obsession
No excessive use of primitives

## Object-Orientation Abusers
### Switch Statements
In our case, EditProfile contains a sequence of if-statements. 
This is inevitable because the validation differs between each property of the profile information.

### Temporary Field
We found some variables defined in a class scope but never used, so we deleted them.  
Variables representing UIs in UIController were redundant so we deleted them.  
For other classes, they do not have variables in a class scope that are only used once.

### Refused Bequest
No suspicious part found

### Alternative Classes with Different Interfaces
We do not have this problem since there are no two classes that perform the identical action.

## Change Prevernters
### Divergent Change
Our classes basically have sole responsibility and usually have one methods in it, which means some modifications on one 
class won't affect other classes

### Shotgun Surgery
In our documentation in SOLID, we wrote how we follow the single responsibility problem. 
Plus, we have clean architecture, so this code smell is not detected.

### Parallel Inheritance Hierarchies
No code smell detected.

## Dispensable
### Comments
None of our classes have excessive amount of comments. Enough amount to explain our code

### Duplicate Code
### Lazy Class
Each of our classes has their responsibility in each senario, so we do not have problems with this.

### Data Class
Each of our classes has methods that play role in each senario so there are not data classes. 
Profile and Preferences might seem to be data classes, but they are Entities and need to be defined in single classes.

### Dead Code
We do not have this problem because if there are such variables or methods, IntelliJ will notify us.
We cleared all the warnings

### Speculative Generality
Similar to above, we passed all the checks of IntelliJ

## Couplers
### Feature Envy
No code smell detected

### Inappropriate Intimacy
Each class calls public or static methods of another class. 
Never used private, protected methods or fields of another class

### Message Chains
Our code has distinct scenarios so there is no long chain of methods.

### Middle Man
A class does one job and delegate the work to another class.
We used to have this problem with DataController implementing the methods from SendData and FetchData. 
We deleted DataController so it does not be a middle man. 
