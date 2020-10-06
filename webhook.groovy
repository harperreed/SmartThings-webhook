/**
 * SmartThings webhooks
 *
 *  Author: Harper Reed (@harper - harper@nata2.org)
 *  URL: https://github.com/harperreed/SmartThings-webhook
 */
 
/** 
 * Let's define this dude
 *
 */
definition(
    name: "Webhooks",
    author: "Harper Reed (@harper - harper@nata2.org)",
    description: "Send Smartthings events to a webhook",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/kinlane-productions/bw-icons/webhooks.png",
    iconX2Url: "https://s3.amazonaws.com/kinlane-productions/bw-icons/webhooks.png"
)

/** 
 * Collect some preferences
 * The important piece here is the webhook URL. without that we can't do anything
 * 
 * The event selector is where we let the user select which sensor events they want to send to the webhook
 *
 */
preferences {
    /**
     * This should be an accessible URL
     * For debugging, I suggest http://requestb.in/
     */
    section("Webhook URL"){
		input "url", "text", title: "Webhook URL", description: "Your webhook URL", required: true
	}

	/**
     * We will probably need to add other items as smartthings sensors grow
     * 
     * Feel free to add any sensors that we are missing and then submit a pull request
     */
	section("Choose what events you want to trigger"){
		input "accelerationSensor", "capability.accelerationSensor", title: "Acceleration Sensor", required: false, multiple: true
		input "alarm", "capability.alarm", title: "Alarm", required: false, multiple: true
		input "battery", "capability.battery", title: "Battery", required: false, multiple: true
		input "beacon", "capability.beacon", title: "Beacon", required: false, multiple: true
		input "button", "capability.button", title: "Button", required: false, multiple: true
		input "carbonMonoxideDetector", "capability.carbonMonoxideDetector", title: "Carbon Monoxide Detector", required: false, multiple: true
		input "colorControl", "capability.colorControl", title: "Color Control", required: false, multiple: true
		input "contactSensor", "capability.contactSensor", title: "Contact Sensor", required: false, multiple: true
		input "doorControl", "capability.doorControl", title: "Door Control", required: false, multiple: true
		input "energyMeter", "capability.energyMeter", title: "Energy Meter", required: false, multiple: true
		input "illuminanceMeasurement", "capability.illuminanceMeasurement", title: "Illuminance Measurement", required: false, multiple: true
		input "imageCapture", "capability.imageCapture", title: "Image Capture", required: false, multiple: true
		input "indicator", "capability.indicator", title: "Indicator", required: false, multiple: true
		input "locationMode", "capability.locationMode", title: "Location Mode", required: false, multiple: false
		input "lock", "capability.lock", title: "Lock", required: false, multiple: true
		input "mediaController", "capability.mediaController", title: "Media Controller", required: false, multiple: true
		input "motionSensor", "capability.motionSensor", title: "Motion Sensor", required: false, multiple: true
		input "musicPlayer", "capability.musicPlayer", title: "Music Player", required: false, multiple: true
		input "powerMeter", "capability.powerMeter", title: "Power Meter", required: false, multiple: true
		input "presenceSensor", "capability.presenceSensor", title: "Presence Sensor", required: false, multiple: true
		input "relativeHumidityMeasurement", "capability.relativeHumidityMeasurement", title: "Relative Humidity Measurement", required: false, multiple: true
		input "relaySwitch", "capability.relaySwitch", title: "Relay Switch", required: false, multiple: true
		input "sensor", "capability.sensor", title: "Sensor", required: false, multiple: true
		input "signalStrength", "capability.signalStrength", title: "Signal Strength", required: false, multiple: true
		input "sleepSensor", "capability.sleepSensor", title: "Sleep Sensor", required: false, multiple: true
		input "smokeDetector", "capability.smokeDetector", title: "Smoke Detector", required: false, multiple: true
		input "speechRecognition", "capability.speechRecognition", title: "Speech Recognition", required: false, multiple: true
		input "stepSensor", "capability.stepSensor", title: "Step Sensor", required: false, multiple: true
		input "switchv", "capability.switch", title: "Switch", required: false, multiple: true
		input "switchLevel", "capability.switchLevel", title: "Switch Level", required: false, multiple: true
		input "temperatureMeasurement", "capability.temperatureMeasurement", title: "Temperature Measurement", required: false, multiple: true
		input "thermostat", "capability.thermostat", title: "Thermostat", required: false, multiple: true
		input "thermostatCoolingSetpoint", "capability.thermostatCoolingSetpoint", title: "Thermostat Cooling Setpoint", required: false, multiple: true
		input "threeAxis", "capability.threeAxis", title: "Three Axis", required: false, multiple: true
		input "touchSensor", "capability.touchSensor", title: "TouchSensor", required: false, multiple: true
		input "valve", "capability.valve", title: "Valve", required: false, multiple: true
		input "waterSensor", "capability.waterSensor", title: "Water Sensor", required: false, multiple: true
	}
}

/**
 * Installer handler
 * 
 * The key piece here is the subscribeToEvents() function. This triggers the subscription of the events that matter
 */
def installed() {
	log.debug "Installed with settings: ${settings}"
	subscribeToEvents()
    initialize()
}

/**
 * Update handler
 *
 * Basically the same as the installer - but it will unsubscribe everything before you subscribe again
 */
def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	subscribeToEvents()
    initialize()
}

/**
 * Init this dude!!
 */
def initialize() {
	subscribe(app, eventHandler)
}

/**
 * Subscribe to those events
 * 
 * This is the meat of the app. When an event happens that we have subscribed to, we throw that event to the eventHandler function
 * Works pretty well. 
 */
def subscribeToEvents() {
	subscribe(accelerationSensor, "acceleration ", eventHandler)

	subscribe(alarm, "alarm", eventHandler)
	subscribe(battery, "battery", eventHandler)
	subscribe(beacon, "presence", eventHandler)
	subscribe(button, "button", eventHandler)
	subscribe(carbonMonoxideDetector, "carbonMonoxide", eventHandler)
	subscribe(colorControl, "hue", eventHandler)
    subscribe(colorControl, "saturation", eventHandler)

	subscribe(contactSensor, "contact", eventHandler)
	subscribe(doorControl, "door", eventHandler)
	subscribe(energyMeter, "energy", eventHandler)
	subscribe(illuminanceMeasurement, "illuminance", eventHandler)
	subscribe(imageCapture, "image", eventHandler)
	subscribe(indicator, "indicatorStatus", eventHandler)
	subscribe(location, "mode", eventHandler)
	subscribe(lock, "lock", eventHandler)

	subscribe(mediaController, "activities", eventHandler)
   	subscribe(mediaController, "currentActivity", eventHandler)

	subscribe(motionSensor, "motion", eventHandler)
	subscribe(musicPlayer, "status", eventHandler)
	subscribe(musicPlayer, "level", eventHandler)
	subscribe(musicPlayer, "trackDescription", eventHandler)
	subscribe(musicPlayer, "trackData", eventHandler)
	subscribe(musicPlayer, "mute", eventHandler)

	subscribe(powerMeter, "power", eventHandler)
	subscribe(presenceSensor, "presence", eventHandler)

	subscribe(relativeHumidityMeasurement, "humidity", eventHandler)
	subscribe(relaySwitch, "switch", eventHandler)
	subscribe(sensor, "sensor", eventHandler)
	subscribe(signalStrength, "lqi", eventHandler)
	subscribe(signalStrength, "rssi", eventHandler)
	subscribe(sleepSensor, "sleeping", eventHandler)
	subscribe(smokeDetector, "smoke", eventHandler)
	subscribe(speechRecognition, "phraseSpoken", eventHandler)

	subscribe(stepSensor, "goals", eventHandler)
	subscribe(stepSensor, "steps", eventHandler)
	subscribe(switchv, "switch", eventHandler)
	subscribe(switchLevel, "level", eventHandler)
	subscribe(temperatureMeasurement, "temperature", eventHandler)

	subscribe(thermostat, "temperature", eventHandler)
	subscribe(thermostat, "heatingSetpoint", eventHandler)
	subscribe(thermostat, "coolingSetpoint", eventHandler)
	subscribe(thermostat, "thermostatSetpoint", eventHandler)
	subscribe(thermostat, "thermostatMode", eventHandler)
	subscribe(thermostat, "thermostatFanMode", eventHandler)
	subscribe(thermostat, "thermostatOperatingState", eventHandler)
    
	subscribe(thermostatCoolingSetpoint, "coolingSetpoint", eventHandler)
	subscribe(threeAxis, "threeAxis", eventHandler)

	subscribe(touchSensor, "touch", eventHandler)
	subscribe(valve, "contact", eventHandler)
	subscribe(waterSensor, "water", eventHandler)

}

/**
 * EventHandler!!
 * 
 * This is the function that communicates externally. 
 * There are 3 parts:
 *  1) Create the json object
 *  2) Create the request params object (including URL)
 *  3) make the request to webhook URL
 *
 * I am forcing json because it is 2015
 *
 * This works pretty well
 */
def eventHandler(evt) {
    def state_changed = evt.isStateChange()
    /**
     * Json object
     * I tired to include everything
     */
	def json_body = [
            id: evt.deviceId, 
			date: evt.isoDate,
        	value: evt.value, 
            name: evt.name, 
            display_name: evt.displayName, 
            description: evt.descriptionText,
            source: evt.source, 
            state_changed: evt.isStateChange(),
            physical: evt.isPhysical(),
            location_id: evt.locationId,
            hub_id: evt.hubId, 
            smartapp_id: evt.installedSmartAppId
        ] 

	def json_params = [
  		uri: settings.url,
  		success: successClosure,
	  	body: json_body
	]
    
    try {
		httpPostJson(json_params)
	} catch (e) {
    	log.error "http post failed: $e"
	}
}

/**
 * Called when a successful webhook post happens!
 */
def successClosure = { response ->
  log.debug "Request was successful, $response"
}
