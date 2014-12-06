/**
 * SmartThings webhooks
 *
 *  Author: Harper Reed
 *  inspired by https://github.com/R-OG/smartthings/
 *
 */
definition(
    name: "SmartThings webhooks",
    author: "Harper Reed",
    description: "Send Smartthings events to a webhook",
    category: "My Apps",
    iconUrl: "http://s13.postimg.org/hydblwntv/Event_Ghost.png",
    iconX2Url: "http://s13.postimg.org/hydblwntv/Event_Ghost.png"
)

preferences {
	section("Choose one or more, when..."){
		input "motion", "capability.motionSensor", title: "Motion Here", required: false, multiple: true
        input "Nomotion", "capability.motionSensor", title: "No Motion Here", required: false, multiple: true
		input "contact", "capability.contactSensor", title: "Contact Opens", required: false, multiple: true
		input "contactClosed", "capability.contactSensor", title: "Contact Closes", required: false, multiple: true
		input "acceleration", "capability.accelerationSensor", title: "Acceleration Detected", required: false, multiple: true
		input "Noacceleration", "capability.accelerationSensor", title: "No Acceleration Detected", required: false, multiple: true
        input "mySwitch", "capability.switch", title: "Switch Turned On", required: false, multiple: true
		input "mySwitchOff", "capability.switch", title: "Switch Turned Off", required: false, multiple: true 
        input "arrivalPresence", "capability.presenceSensor", title: "Arrival Of", required: false, multiple: true
		input "departurePresence", "capability.presenceSensor", title: "Departure Of", required: false, multiple: true 
		input "smoke", "capability.smokeDetector", title: "Smoke Detected", required: false, multiple: true
		input "water", "capability.waterSensor", title: "Water Sensor Wet", required: false, multiple: true
        input "locksLocked", "capability.lock", title: "Lock Locked?",  required: false, multiple:true
        input "locksUnlocked", "capability.lock", title: "Lock Unlock?",  required: false, multiple:true
		input "setMode", "mode", title: "Mode?",  required: false, multiple:true
	}

    
    section("Webhook URL"){
		input "url", "text", title: "Webhook URL", description: "Your webhook URL", required: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	subscribeToEvents()
    initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	subscribeToEvents()
    initialize()
}

def initialize() {
	subscribe(app, eventHandler)

}


def subscribeToEvents() {
	subscribe(contact, "contact.open", eventHandler)
	subscribe(contactClosed, "contact.closed", eventHandler)
	subscribe(acceleration, "acceleration.active", eventHandler)
	subscribe(Noacceleration, "acceleration.inactive", eventHandler)
	subscribe(motion, "motion.active", eventHandler)
    subscribe(Nomotion, "motion.inactive", eventHandler)
	subscribe(mySwitch, "switch.on", eventHandler)
	subscribe(mySwitchOff, "switch.off", eventHandler)
	subscribe(arrivalPresence, "presence.present", eventHandler)
	subscribe(departurePresence, "presence.not present", eventHandler)
	subscribe(smoke, "smoke.detected", eventHandler)
	subscribe(smoke, "smoke.tested", eventHandler)
	subscribe(smoke, "carbonMonoxide.detected", eventHandler)
	subscribe(water, "water.wet", eventHandler)
    subscribe(locksLocked, "lock.locked", eventHandler)
    subscribe(locksUnlocked, "lock.unlocked", eventHandler)
    subscribe(location, eventHandler)

}

def eventHandler(evt) {
	//def toReplace = evt.displayName
	//def replaced = toReplace.replaceAll(' ', '%20')
    //def name = replaced
    def value = evt.value
    log.debug(evt)
	def postBody = "[value:'${evt.value}', name:'${evt.displayName}']"
	def params = [
  		uri: settings.url,
  		success: successClosure,
	  	body: postBody
	]
	httpPost(params)
}

def successClosure = { response ->
  log.debug "Request was successful, $response"
}

