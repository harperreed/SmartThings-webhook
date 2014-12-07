SmartThings-webook
==================

An installable groovy SmartApp that provides event-based webhook functionality for SmartThings. 

##Webhooks

Webhooks are awesome. I choose to send all events to one URL and then have that URL push to other URLs. 

My goals was to be able to make adhoc event based apps (without having to deploy them to the hub directly), to do extensive logging and to be able to do analytics on events that happen in my house. 

If you want to send a specific event to a specific webhook, you can do this a couple ways. 

1. Install an instance of this app per event type (not efficient)
2. Write a middleware that pushes webhooks based on the `name`

If I get bored, I may do #2 in a bit. 


###Post body

On an event, the smartapp will `POST` the following to a URL

	{  
	   "id":"000000000",
	   "date":"2014-12-07T20:00:14.670Z",
	   "value":"73",
	   "name":"temperature",
	   "display_name":"Backdoor",
	   "description":"Backdoor was 73?F",
	   "source":"DEVICE",
	   "state_changed":true,
	   "physical":false,
	   "location_id":"000000000",
	   "hub_id":"000000000"
	}



###Installation

1. Sign into the [SmartThings backend](https://graph.api.smartthings.com/login/auth): 
2. Go to the [SmartApps IDE](https://graph.api.smartthings.com/ide/apps)
3. Create a new **smartapp** (or [click here](https://graph.api.smartthings.com/ide/app/create))
	* Name it something that makes sense
4. Paste in the [webhook groovy code](https://github.com/harperreed/SmartThings-webook/blob/master/webhook.groovy) into the IDE
5. Click save
6. Click publish
7. It should show up in your smartthings app to be configured. You can also configure it on the right hand side of the IDE. 
	* Enter your webhook URL (I like pushing all the events to [loggly](http://loggly.com))
	* Select the devices you want to receive notifications for
8. 
9. Profit



##Inspiration

I spent a bunch of time reading groovy scripts and understanding WTF is going on with the SmartApps paradigm. 

The code from @R-OG really helped ([EventGhost Notification.groovy](https://github.com/R-OG/smartthings/blob/93df325125464f307fa9a496b1e6b00f4b831ef9/EventGhost%20Notification.groovy)) me understand how to subscribe to events and how capabilities work. 

Most of the example code is pretty solid. I couldn't find  any that did exactly what this code does (even though people talked about it in the forums).

##Help out!?

Fork, make a change and then submit a pull request. 

Feel free reach out if you have questions: [@harper](http://twitter.com/harper), [harper@nata2.org](mailto:harper@nata2.org).
