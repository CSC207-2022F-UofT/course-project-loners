# Clean Architecture
## Policies
We clearly defined Profile and Preferences as our Entities. 
We also have use cases accordingly to what we need.

## Layers

### Dependency Rule
We checked imported classes in our Entities, Use Cases, Controllers, and UIs.  
There was no violation in Entities and Use Cases classes.  
However, we found EditProfileUI importing use cases; LoadFile and LocationConverter. 
Plus, RegUI was also importing a use case; PictureHolder.

In controller classes, we found ConnectProfilesControl class importing Preferences,
DataSendControl class importing Profile, EditPreferences class importing Preferences, EditProfile class importing 
Profile and Preferences class importing Profile.

### Direction of Arrows
Some of our classes have dependency arrow pointing to the opposite direction.  
For instance, Authenticator, ConnectProfiles, EditPreferences, PreferredLocationConnector, RegChecker, RegDataStore 
depend on DataFetchControl or DataSendControl
