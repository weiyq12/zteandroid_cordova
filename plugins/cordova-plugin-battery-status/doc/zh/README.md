<!--
# license: Licensed to the Apache Software Foundation (ASF) under one
#         or more contributor license agreements.  See the NOTICE file
#         distributed with this work for additional information
#         regarding copyright ownership.  The ASF licenses this file
#         to you under the Apache License, Version 2.0 (the
#         "License"); you may not use this file except in compliance
#         with the License.  You may obtain a copy of the License at
#
#           http://www.apache.org/licenses/LICENSE-2.0
#
#         Unless required by applicable law or agreed to in writing,
#         software distributed under the License is distributed on an
#         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#         KIND, either express or implied.  See the License for the
#         specific language governing permissions and limitations
#         under the License.
-->

# cordova-plugin-battery-status

[![Build Status](https://travis-ci.org/apache/cordova-plugin-battery-status.svg)](https://travis-ci.org/apache/cordova-plugin-battery-status)

这个外挂程式提供的旧版本的[电池状态事件 API](http://www.w3.org/TR/2011/WD-battery-status-20110915/)实现的.

它将添加以下三 `window` 事件：

  * batterystatus
  * batterycritical
  * batterylow

## 安装

    cordova plugin add cordova-plugin-battery-status
    

## batterystatus

当电池计量的百分比改变了至少 1%，或如果在插入或拔出该设备会触发此事件。

电池状态处理常式传递一个物件，包含两个属性：

  * **级别**: 电池充电 (0-100) 的百分比。*（人数）*

  * **isPlugged**： 一个布林值，该值指示设备是否插*(布林值)*

应用程式通常应使用 `window.addEventListener` 将附加一个事件拦截器后的 `deviceready` 事件触发。

### 支援的平台

  * 亚马逊火 OS
  * iOS
  * Android 系统
  * 黑莓 10
  * Windows Phone 7 和 8
  * Windows (仅限于 Windows Phone 8.1)
  * Tizen
  * 火狐浏览器作业系统

### 安卓和亚马逊火 OS 怪癖

  * 警告: Android + 火 OS 实现都是贪婪和长时间的使用会流失使用者的电池。 

### Windows Phone 7 和 8 怪癖

Windows Phone 7 并不提供本机 Api 来确定电池计量水准，所以 `level` 是不可用的属性。`isPlugged`参数**支援的。

### Windows 的怪癖

Windows Phone 8.1 不支援`isPlugged`参数。 `水准`参数**支援。

### 示例

    window.addEventListener("batterystatus", onBatteryStatus, false);
    
    function onBatteryStatus(info) {
        // Handle the online event
        console.log("Level: " + info.level + " isPlugged: " + info.isPlugged);
    }
    

## batterycritical

当电池计量的百分比已达到关键电池阈值时，将触发该事件。值是特定于设备。

`batterycritical`处理常式传递一个物件，包含两个属性：

  * **级别**: 电池充电 (0-100) 的百分比。*（人数）*

  * **isPlugged**： 一个布林值，该值指示设备是否插*(布林值)*

应用程式通常应使用 `window.addEventListener` 将一个事件拦截器附加一次 `deviceready` 事件火灾。

### 支援的平台

  * 亚马逊火 OS
  * iOS
  * Android 系统
  * 黑莓 10
  * Tizen
  * 火狐浏览器作业系统
  * Windows (仅限于 Windows Phone 8.1)

### Windows 的怪癖

Windows Phone 8.1 会触发`batterycritical`事件任何堵塞状态，因为它不支援。

### 示例

    window.addEventListener("batterycritical", onBatteryCritical, false);
    
    function onBatteryCritical(info) {
        // Handle the battery critical event
        alert("Battery Level Critical " + info.level + "%\nRecharge Soon!");
    }
    

## batterylow

当电池计量的百分比已达到电池计量低门槛，设备特定值时，将触发该事件。

`batterylow`处理常式传递一个物件，包含两个属性：

  * **级别**: 电池充电 (0-100) 的百分比。*（人数）*

  * **isPlugged**： 一个布林值，该值指示设备是否插*(布林值)*

应用程式通常应使用 `window.addEventListener` 将一个事件拦截器附加一次 `deviceready` 事件火灾。

### 支援的平台

  * 亚马逊火 OS
  * iOS
  * Android 系统
  * 黑莓 10
  * Tizen
  * 火狐浏览器作业系统
  * Windows (仅限于 Windows Phone 8.1)

### Windows 的怪癖

Windows Phone 8.1 会触发`batterylow`事件任何堵塞状态，因为它不支援。

### 示例

    window.addEventListener("batterylow", onBatteryLow, false);
    
    function onBatteryLow(info) {
        // Handle the battery low event
        alert("Battery Level Low " + info.level + "%");
    }