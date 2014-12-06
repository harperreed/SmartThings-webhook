/**
 * SmartThings webhooks
 *
 *  Author: Harper Reed
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
    section("Webhook URL"){
		input "url", "text", title: "Webhook URL", description: "Your webhook URL", required: true
	}

	section("Choose what events you want to trigger"){
		input "accelerationSensor", "capability.accelerationSensor", title: "Acceleration Sensor", required: false, multiple: true
		input "actuator", "capability.actuator", title: "Actuator", required: false, multiple: true
		input "alarm", "capability.alarm", title: "Alarm", required: false, multiple: true
		input "battery", "capability.battery", title: "Battery", required: false, multiple: true
		input "beacon", "capability.beacon", title: "Beacon", required: false, multiple: true
		input "button", "capability.button", title: "Button", required: false, multiple: true
		input "carbonMonoxideDetector", "capability.carbonMonoxideDetector", title: "Carbon Monoxide Detector", required: false, multiple: true
		input "colorControl", "capability.colorControl", title: "Color Control", required: false, multiple: true
		input "configuration", "capability.configuration", title: "Configuration", required: false, multiple: true
		input "contactSensor", "capability.contactSensor", title: "Contact Sensor", required: false, multiple: true
		input "doorControl", "capability.doorControl", title: "Door Control", required: false, multiple: true
		input "energyMeter", "capability.energyMeter", title: "Energy Meter", required: false, multiple: true
		input "illuminanceMeasurement", "capability.illuminanceMeasurement", title: "Illuminance Measurement", required: false, multiple: true
		input "imageCapture", "capability.imageCapture", title: "Image Capture", required: false, multiple: true
		input "indicator", "capability.indicator", title: "Indicator", required: false, multiple: true
		input "locationMode", "capability.locationMode", title: "Location Mode", required: false, multiple: true
		input "lock", "capability.lock", title: "Lock", required: false, multiple: true
		input "lockCodes", "capability.lockCodes", title: "Lock Codes", required: false, multiple: true
		input "mediaController", "capability.mediaController", title: "Media Controller", required: false, multiple: true
		input "momentary", "capability.momentary", title: "Momentary", required: false, multiple: true
		input "motionSensor", "capability.motionSensor", title: "Motion Sensor", required: false, multiple: true
		input "musicPlayer", "capability.musicPlayer", title: "Music Player", required: false, multiple: true
		input "polling", "capability.polling", title: "Polling", required: false, multiple: true
		input "powerMeter", "capability.powerMeter", title: "Power Meter", required: false, multiple: true
		input "presenceSensor", "capability.presenceSensor", title: "Presence Sensor", required: false, multiple: true
		input "refresh", "capability.refresh", title: "Refresh", required: false, multiple: true
		input "relativeHumidityMeasurement", "capability.relativeHumidityMeasurement", title: "Relative Humidity Measurement", required: false, multiple: true
		input "relaySwitch", "capability.relaySwitch", title: "Relay Switch", required: false, multiple: true
		input "sensor", "capability.sensor", title: "Sensor", required: false, multiple: true
		input "signalStrength", "capability.signalStrength", title: "Signal Strength", required: false, multiple: true
		input "sleepSensor", "capability.sleepSensor", title: "Sleep Sensor", required: false, multiple: true
		input "smokeDetector", "capability.smokeDetector", title: "Smoke Detector", required: false, multiple: true
		input "speechRecognition", "capability.speechRecognition", title: "Speech Recognition", required: false, multiple: true
		input "speechSynthesis", "capability.speechSynthesis", title: "Speech Synthesis", required: false, multiple: true
		input "stepSensor", "capability.stepSensor", title: "Step Sensor", required: false, multiple: true
		input "switchv", "capability.switch", title: "Switch", required: false, multiple: true
		input "switchLevel", "capability.switchLevel", title: "Switch Level", required: false, multiple: true
		input "temperatureMeasurement", "capability.temperatureMeasurement", title: "Temperature Measurement", required: false, multiple: true
		input "testCapability", "capability.testCapability", title: "Test Capability", required: false, multiple: true
		input "thermostat", "capability.thermostat", title: "Thermostat", required: false, multiple: true
		input "thermostatCoolingSetpoint", "capability.thermostatCoolingSetpoint", title: "Thermostat Cooling Setpoint", required: false, multiple: true
		input "threeAxis", "capability.threeAxis", title: "Three Axis", required: false, multiple: true
		input "tone", "capability.tone", title: "Tone", required: false, multiple: true
		input "touchSensor", "capability.touchSensor", title: "TouchSensor", required: false, multiple: true
		input "valve", "capability.valve", title: "Valve", required: false, multiple: true
		input "waterSensor", "capability.waterSensor", title: "Water Sensor", required: false, multiple: true

        
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
	subscribe(accelerationSensor, "accelerationSensor", eventHandler)
	subscribe(actuator, "actuator", eventHandler)
	subscribe(alarm, "alarm", eventHandler)
	subscribe(battery, "battery", eventHandler)
	subscribe(beacon, "beacon", eventHandler)
	subscribe(button, "button", eventHandler)
	subscribe(carbonMonoxideDetector, "carbonMonoxideDetector", eventHandler)
	subscribe(colorControl, "colorControl", eventHandler)
	subscribe(configuration, "configuration", eventHandler)
	subscribe(contactSensor, "contactSensor", eventHandler)
	subscribe(doorControl, "doorControl", eventHandler)
	subscribe(energyMeter, "energyMeter", eventHandler)
	subscribe(illuminanceMeasurement, "illuminanceMeasurement", eventHandler)
	subscribe(imageCapture, "imageCapture", eventHandler)
	subscribe(indicator, "indicator", eventHandler)
	subscribe(locationMode, "locationMode", eventHandler)
	subscribe(lock, "lock", eventHandler)
	subscribe(lockCodes, "lockCodes", eventHandler)
	subscribe(mediaController, "mediaController", eventHandler)
	subscribe(momentary, "momentary", eventHandler)
	subscribe(motionSensor, "motionSensor", eventHandler)
	subscribe(musicPlayer, "musicPlayer", eventHandler)
	subscribe(polling, "polling", eventHandler)
	subscribe(powerMeter, "powerMeter", eventHandler)
	subscribe(presenceSensor, "presenceSensor", eventHandler)
	subscribe(refresh, "refresh", eventHandler)
	subscribe(relativeHumidityMeasurement, "relativeHumidityMeasurement", eventHandler)
	subscribe(relaySwitch, "relaySwitch", eventHandler)
	subscribe(sensor, "sensor", eventHandler)
	subscribe(signalStrength, "signalStrength", eventHandler)
	subscribe(sleepSensor, "sleepSensor", eventHandler)
	subscribe(smokeDetector, "smokeDetector", eventHandler)
	subscribe(speechRecognition, "speechRecognition", eventHandler)
	subscribe(speechSynthesis, "speechSynthesis", eventHandler)
	subscribe(stepSensor, "stepSensor", eventHandler)
	subscribe(switchv, "switch", eventHandler)
	subscribe(switchLevel, "switchLevel", eventHandler)
	subscribe(temperatureMeasurement, "temperatureMeasurement", eventHandler)
	subscribe(testCapability, "testCapability", eventHandler)
	subscribe(thermostat, "thermostat", eventHandler)
	subscribe(thermostatCoolingSetpoint, "thermostatCoolingSetpoint", eventHandler)
	subscribe(threeAxis, "threeAxis", eventHandler)
	subscribe(tone, "tone", eventHandler)
	subscribe(touchSensor, "touchSensor", eventHandler)
	subscribe(valve, "valve", eventHandler)
	subscribe(waterSensor, "waterSensor", eventHandler)

}

def eventHandler(evt) {

	def postBody = "[date:'${evt.date}', value:'${evt.value}', source:'${evt.source}', type:'${evt.type}', name:'${evt.displayName}']"

	def params = [
  		uri: settings.url,
  		success: successClosure,
	  	body: postBody 
	]
	httpPost(params)
    
	log.debug(evt.jsonValue)
    /*
	def params = [
  		uri: settings.url,
  		success: successClosure,
	  	body: evt.jsonValue 
	]
	httpPostJson(params)
    */
}

def successClosure = { response ->
  log.debug "Request was successful, $response"
}

