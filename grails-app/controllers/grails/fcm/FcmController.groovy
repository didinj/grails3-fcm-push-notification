package grails.fcm

import grails.plugins.rest.client.RestBuilder

class FcmController {

  def index() { }

  def sendPushNotification() {
    def regid = params.regid
    def title = params.title
    def body = params.body

    def rest = new RestBuilder(connectTimeout:1000, readTimeout:20000)
    def resp = rest.post("https://fcm.googleapis.com/fcm/send") {
      header 'Content-Type', 'application/json'
      header 'Authorization', 'key=AIzaSyCvc8YW_AzY1_wMG12J7qTKXAEHe2gqj8k'
      json {
        notification = {
          title = title
          body = body
          sound = "default"
          click_action = "FCM_PLUGIN_ACTIVITY"
          icon = "fcm_push_icon"
        }
        to = regid
      }
    }

    flash.message = "Notification sent"
    redirect action: "index"
  }
}
