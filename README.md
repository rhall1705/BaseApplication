# BaseApplication

This repository exists as a showcase for base classes used in my applications to reduce boilerplate and to create clean, performant Android applications. I will add to it as I abstract further base classes from my main projects. Additionally, it includes a very simple sample activity, object model, and database as an example of its use cases. 

## UI

### BaseActivity

* Convenience method for access to the ApplicationComponent class for Dagger 2 DI.
* Loaders - methods that allow for either initializing/restarting a loader depending on its current state given its ID, which are all supplied by an IntDef in the loader package. Also allows for notifying a loader of changes given its ID.
* ProgressDialog - convenience methods that allow for easily showing/dismissing a ProgressDialog with given text while maintaining a member variable to ensure no references are leaked and that the dialog is dismissed when the Activity is destroyed.
* Toolbar - convenience methods that set up a Toolbar with a given title with overloads to set an Up button. By default, this button will simply call onBackPressed, however an onUpPressed method is supplied that can be overriden.
* Callbacks for BaseDialogFragment (detailed below) that enforce CallSuper to ensure no superclasses lose their dialog callbacks. 

### BaseFragment

* Contains all the same methods as BaseActivity except for Toolbar ones. Note that, particularly for its ProgressDialog methods, this class assumes that the fragment is placed within a container in an Activity deriving from BaseActivity.

### BaseDialogFragment

* Callbacks - provided for positive, negative, and neutral clicks, in additional to dismiss action. These callbacks are designed to be implemented by the Activity or Fragment that showed the dialog, and this is encouraged by show methods that take an instance of BaseActivity or BaseFragment as an argument as these classes implement the callbacks by default. Note that attempting to show the dialog from any containing Activity or Fragment that does not implement the callbacks will result in an IllegalStateException. Also note that the callbacks switch off the result of the abstract method tag().
* Convenience methods - provides the same methods as BaseActivity and BaseFragment for Loaders and ApplicationComponent.
* Overrides - provides override-able methods to determine text and color of title and buttons within the dialog, as well as for callbacks that allow the subclass to interact with UI elements that are null until the callback of an OnShowListener.

## Loaders

### BaseLoader

* Extends AsyncTaskLoader - all subclasses should perform their loading on the current thread as it is already separated from the main UI thread.
* Overrides - provides override-able methods to implement a custom cache for any given loader, including loading, saving, and determining if a given piece of data loaded from a cache should be considered invalid. These methods allow allow for the use of a custom broadcast receiver that notifies the loader of any changes to its dataset.

### BaseObserver

* Subclass of broadcast receiver designed for a particular type of BaseLoader. Its constructor sets a loader of that generic type as a member variable and registers a list of relevant intent filters. When this receiver is triggered, it notifies its loader that its data has changed and ensures that newly loaded data is written to its cache, regardless of the application's current UI state or if the loader in question has stopped.

### LoaderFactory

* A singleton class to be injected into any instance of LoaderCallbacks. It contains factory methods to produce any given loader via its constructor using only a bundle so as to centralize the process of extracting its arguments as intended.
