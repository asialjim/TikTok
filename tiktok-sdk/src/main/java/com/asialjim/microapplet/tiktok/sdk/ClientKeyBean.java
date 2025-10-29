/*
 *    Copyright 2014-2025 <a href="mailto:asialjim@qq.com">Asial Jim</a>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.asialjim.microapplet.tiktok.sdk;

import com.douyin.openapi.client.Client;
import com.douyin.openapi.client.models.OauthClientTokenRequest;
import com.douyin.openapi.client.models.OauthClientTokenResponse;
import com.douyin.openapi.credential.models.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * CLIENT_KEY 组件
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/29, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
@RequiredArgsConstructor
public class ClientKeyBean {
    private final ClientBean clientBean;


    public Optional<String> clientKey(String index) {
        Optional<Config> configOpt = clientBean.configOf(index);
        if (configOpt.isEmpty())
            return Optional.empty();

        Optional<Client> clientOpt = clientBean.clientOf(index);
        if (clientOpt.isEmpty())
            return Optional.empty();




        Config config = configOpt.get();
        Client client = clientOpt.get();

        OauthClientTokenRequest sdkRequest = new OauthClientTokenRequest();
        sdkRequest.setClientKey(config.getClientKey());
        sdkRequest.setClientSecret(config.getClientSecret());
        sdkRequest.setGrantType("client_credential");
        try {
            OauthClientTokenResponse sdkResponse = client.OauthClientToken(sdkRequest);
            String accessToken = sdkResponse.getData().getAccessToken();
            // TODO 存储
            return Optional.ofNullable(accessToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}