/*
 * Copyright (c) 2018 amy, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.mewna.catnip.entity.guild.audit;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.entity.Entity;
import io.vertx.core.json.JsonObject;

import javax.annotation.Nonnull;

/**
 * @author SamOphis
 * @since 10/07/18
 */
@SuppressWarnings("WeakerAccess")
public interface OptionalEntryInfo extends Entity {
    
    String infoType();
    
    @Nonnull
    @Override
    default JsonObject toJson() {
        return Entity.super.toJson().put("type", infoType());
    }
    
    static OptionalEntryInfo fromJson(final Catnip catnip, final JsonObject json) {
        final String type = json.getString("type");
        json.remove("type");
        switch(type) {
            case OverrideUpdateInfo.IDENTIFIER:
                return  Entity.fromJson(catnip, OverrideUpdateInfo.class, json);
            case MemberPruneInfo.IDENTIFIER:
                return Entity.fromJson(catnip, MemberPruneInfo.class, json);
            case MessageDeleteInfo.IDENTIFIER:
                return Entity.fromJson(catnip, MessageDeleteInfo.class, json);
            default:
                throw new IllegalArgumentException("This json does not contain a valid OptionalEntryInfo");
        }
    }
    
}
