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

package com.asialjim.microapplet.tiktok.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 抖音令牌缓存
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/29, &nbsp;&nbsp; <em>version:1.0</em>
 */
@Component
public class TikTokTokenCache {

    @Cacheable(value = TikTokCache.Name.clientKey, key = "#index")
    public String getClientKey(String index) {
        return StringUtils.EMPTY;
    }

    @CachePut(value = TikTokCache.Name.clientKey, key = "#index")
    public String setClientKey(String index, String clientKey) {
        return clientKey;
    }

    @CacheEvict(value = TikTokCache.Name.clientKey, key = "#index")
    public void cleanClientKey(String index) {
    }
}