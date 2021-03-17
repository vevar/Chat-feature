/**
* Chat service
* Chat service
*
* The version of the OpenAPI document: 1.0.0
* Contact: alxminyaev@gmail.com
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package dev.alxminyaev.feature.chat.api.models

import dev.alxminyaev.feature.chat.api.models.MessageResponse
import dev.alxminyaev.feature.chat.api.models.UserInfo

/**
 * 
 * @param id 
 * @param name 
 * @param users 
 * @param userInfo 
 * @param lastMessage 
 */
data class ChatResponse (
    val id: kotlin.Long,
    val name: kotlin.String,
    val users: kotlin.Array<kotlin.Long>,
    val userInfo: UserInfo,
    val lastMessage: MessageResponse? = null
) 

