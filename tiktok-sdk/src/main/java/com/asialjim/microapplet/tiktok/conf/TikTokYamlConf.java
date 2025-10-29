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

package com.asialjim.microapplet.tiktok.conf;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 抖音配置信息，基于YAML
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/29, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tiktok.app")
public class TikTokYamlConf implements TikTokConf, Serializable {
    @Serial
    private static final long serialVersionUID = 3986664727909496760L;

    private List<TikTokApp> list;

    @Override
    public Optional<TikTokApp> indexOf(String index) {
        if (StringUtils.isBlank(index) || CollectionUtils.isEmpty(list))
            return Optional.empty();

        return list.stream()
                .filter(Objects::nonNull)
                .filter(item -> StringUtils.equalsAny(index, item.getId(), item.getAppid()))
                .findAny();
    }
}