# Clean Architecture
## Policies
We clearly defined Profile and Preferences as our entities. 
We also have use cases accordingly to what we need.

## Layers

### Dependency Rule
We checked imported classes in our entities, Use Cases, Controllers, and uis.  
There was no violation in entities and Use Cases classes.  
However, we found EditProfileUI importing use cases; LoadFile and LocationConverter. 
RegChecker, Authenticator importing controller DataFetchControl. 

In controller classes, we found ConnectProfilesControl class importing Preferences,
DataSendControl class importing Profile, EditPreferences class importing Preferences, EditProfile class importing 
Profile and Preferences class importing Profile.