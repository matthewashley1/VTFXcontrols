# VTFXcontrols

_VTFXcontrols_ is a library for JavaFX that adds easy-to-use functions as well as a customizable ToggleSwitch (SwitchToggle). 

SwitchToggle allows for customizing color, text, as-well-as Transition Effects.

___

### Creating a new SwitchToggle


<p align=“center”>
To use, create an instance of <em>SwitchToggle</em> and define its color, text, and Transition Effect.
<br>
</p>

```java
	SwitchToggle switchToggle = new SwitchToggle("ON", Color.web("#a7ef88"), "OFF", Color.web("#aeb0b2"), TransitionType.BUZZ);
```

<p align=“center”>
Alternatively you can initialize a SwitchToggle with default settings. 
</p>

```java
	SwitchToggle switchToggle = new SwitchToggle();
```



### Different Transition Effects

<p align=“center”>
There are 5 different Transition Effects that can be used, default(NONE), POP, COMPRESS, ROTATE, BUZZ.
</p>

### <em>NONE</em>

![Demo](http://sotd.us/matthewashley/VTFXcontrols/NONE.gif)

### <em>POP</em>

![Demo](http://sotd.us/matthewashley/VTFXcontrols/POP.gif)

### <em>COMPRESS</em>

![Demo](http://sotd.us/matthewashley/VTFXcontrols/COMPRESS.gif)

### <em>ROTATE</em>

![Demo](http://sotd.us/matthewashley/VTFXcontrols/ROTATE.gif)

### <em>BUZZ</em>

![Demo](http://sotd.us/matthewashley/VTFXcontrols/BUZZ.gif)


### Other SwitchToggle functionality

<p align=“center”>
To evaluate the state of the SwitchToggle with a change Listener use <em>switchOnProperty()</em>.
<br>
</p>

```java
	switchToggle.switchOnProperty().addListener((observable, oldValue, newValue) -> {};
```

<p align=“center”>
To return the state of the switchToggle as boolean use <em>isSelected()</em>.
<br>
</p>

```java
	boolean state = switchToggle.isSelected();
```

<p align=“center”>
To set the state of the switchToggle to a desired state use <em>setSelected()</em>.
<br>
</p>

```java
	switchToggle.setSelected(true);
```




