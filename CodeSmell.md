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
This might be applied to we using properties of Profile and Preferences.
### 
