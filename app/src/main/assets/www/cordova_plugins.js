cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "cordova-plugin-ble-central.ble",
      "file": "plugins/cordova-plugin-ble-central/www/ble.js",
      "pluginId": "cordova-plugin-ble-central",
      "clobbers": [
        "ble"
      ]
    },
    {
      "id": "cordova-plugin-geolocation.geolocation",
      "file": "plugins/cordova-plugin-geolocation/www/android/geolocation.js",
      "pluginId": "cordova-plugin-geolocation",
      "clobbers": [
        "navigator.geolocation"
      ]
    },
    {
      "id": "cordova-plugin-geolocation.PositionError",
      "file": "plugins/cordova-plugin-geolocation/www/PositionError.js",
      "pluginId": "cordova-plugin-geolocation",
      "runs": true
    },
    {
      "id": "cordova-plugin-smartgarden.smartgarden",
      "file": "plugins/cordova-plugin-smartgarden/www/smartgarden.js",
      "pluginId": "cordova-plugin-smartgarden",
      "clobbers": [
        "smartgarden"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-ble-central": "1.3.1",
    "cordova-plugin-geolocation": "4.1.0",
    "cordova-plugin-smartgarden": "1.0.0",
    "cordova-plugin-whitelist": "1.3.4"
  };
});