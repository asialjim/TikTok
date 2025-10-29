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

import com.asialjim.microapplet.tiktok.conf.TikTokApp;
import com.asialjim.microapplet.tiktok.repository.TikTokRepository;
import com.douyin.openapi.client.Client;
import com.douyin.openapi.credential.models.Config;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 抖音客户端组件
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/29, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
@RequiredArgsConstructor
public class ClientBean {
    private static final Map<String, Config> CONFIG_MAP = new HashMap<>();
    private static final Map<String, Client> CLIENT_MAP = new HashMap<>();

    private final TikTokRepository tikTokRepository;


    public Optional<Config> configOf(String index) {
        if (StringUtils.isBlank(index))
            return Optional.empty();

        Config config = CONFIG_MAP.get(index);
        if (Objects.nonNull(config))
            return Optional.of(config);

        synchronized (CONFIG_MAP) {
            config = CONFIG_MAP.get(index);
            if (Objects.nonNull(config))
                return Optional.of(config);

            Optional<TikTokApp> tikTokApp = tikTokRepository.indexOf(index);
            if (tikTokApp.isEmpty())
                return Optional.empty();

            TikTokApp app = tikTokApp.get();
            config = new Config().setClientKey(app.getAppid()).setClientSecret(app.getSecret()); // 改成自己的app_id跟secret
            CONFIG_MAP.put(index, config);
            return Optional.of(config);
        }
    }

    public Optional<Client> clientOf(String index) {
        if (StringUtils.isBlank(index))
            return Optional.empty();
        Client client = CLIENT_MAP.get(index);
        if (Objects.nonNull(client))
            return Optional.of(client);

        synchronized (CLIENT_MAP) {
            client = CLIENT_MAP.get(index);
            if (Objects.nonNull(client))
                return Optional.of(client);

            Optional<Config> config = configOf(index);
            if (config.isEmpty())
                return Optional.empty();

            try {
                client = new Client(config.get());
                CLIENT_MAP.put(index, client);
                return Optional.of(client);
            } catch (Exception e) {
                return Optional.empty();
            }
        }
    }}