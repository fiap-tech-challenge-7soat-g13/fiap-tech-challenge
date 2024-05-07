/*
 *   This software is Copyright (c) 2023 Grupo Boticário. All rights reserved.
 *
 *   This software is confidential and proprietary to Grupo Boticário.
 *   This software may not be distributed or used outside the company without the express written permission of Grupo Boticário.
 *   Any unauthorized distribution or use is strictly prohibited and may violate applicable laws.
 *   Redistribution and use in source and binary forms, with or without modification, are not permitted under any circumstances.
 *
 *   Team [B]  - Logistics
 */

package com.fiap.challenge.tastefood.core.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Taste Food API", version = "0.0.1-SNAPSHOT", description = "API to manage food products"))
public class OpenApiConfiguration {
}
