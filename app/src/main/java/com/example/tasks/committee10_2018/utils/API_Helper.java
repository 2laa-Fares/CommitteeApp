/**
 * Copyright 2010-present Mobily.ws.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tasks.committee10_2018.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class API_Helper {

	public static final String MSG_SEND_METHOD_NAME = "msgSend";

	public static JSONObject msgSend(String userName, String password,
                                     String sender, String msg, String numbers, String dateSend,
                                     String timeSend, String deleteKey, String msgId,
                                     String applicationType, String domainName) throws JSONException {

		JSONObject jsonData = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Method", MSG_SEND_METHOD_NAME);
		JSONObject auth = new JSONObject();

		JSONObject jsonParams = new JSONObject();
		jsonParams.put("sender", sender);
		jsonParams.put("msg", msg);
		jsonParams.put("numbers", numbers);
		jsonParams.put("dateSend", dateSend);
		jsonParams.put("timeSend", timeSend);
		jsonParams.put("deleteKey", deleteKey);
		jsonParams.put("msgId", msgId);
		jsonParams.put("applicationType", applicationType);
		jsonParams.put("domainName", domainName);
		jsonObject.put("Params", jsonParams);

		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;
	}

}
